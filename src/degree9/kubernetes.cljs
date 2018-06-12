(ns degree9.kubernetes
  (:refer-clojure :exclude [namespace])
  (:require
    [cljs.nodejs :as node]
    [clojure.string :as s]
    [goog.object :as obj]
    [feathers.errors :as error]))

;; Kubernetes API ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(def k8s (node/require "@kubernetes/client-node"))

(def fs (node/require "fs"))

(def path (node/require "path"))

(def ^:private k8s-svc (str "/var/run/secrets/kubernetes.io/serviceaccount"))

(def ^:private k8s-crt (str k8s-svc "/ca.crt"))

(def ^:private k8s-token (str k8s-svc "/token"))

(def ^:private k8s-host js/process.env.KUBERNETES_SERVICE_HOST)

(def ^:private k8s-port js/process.env.KUBERNETES_SERVICE_PORT)

(def ^:private k8s-config (or js/process.env.KUBECONFIG
                            (.join path js/process.env.HOME ".kube" "config")))

(defn- exists [path]
  (.existsSync fs path))

(defn- read-file [path]
  (.readFileSync fs path))

(defn kube-config []
  (let [config (k8s.KubeConfig.)]
    (when (exists k8s-config)
      (.loadFromFile config k8s-config))
    config))

(defn cluster-config [api]
  (.log js/console api k8s-crt k8s-token))

(defn file-config [api]
  (let [config  (kube-config)
        cluster (.getCurrentCluster config)
        server  (obj/get cluster "server")
        k8s-api (api. server)]
    (.setDefaultAuthentication k8s-api config)
    k8s-api))

(defn config [api]
  (cond
    (and k8s-host k8s-port) (cluster-config api)
    (exists k8s-config) (file-config api)
    :else (api "http://localhost:8080")))

(defn watcher
  "A watcher for Kubernetes, implements reconnecting by calling watcher again once stream closes."
  ([path callback]
   (watcher path callback
     #(watcher path callback)))
  ([path callback reconnect-callback]
   (watcher path #js{} callback reconnect-callback))
  ([path opts callback reconnect-callback]
   (.watch (k8s.Watch. (kube-config))
     path
     opts
     callback
     reconnect-callback)))

(defn watch-handler
  "Returns a default watch-handler which prints to console."
  [& [opts]]
  (let [default  (:default  opts #(.log js/console %))
        added    (:added    opts default)
        deleted  (:deleted  opts default)
        modified (:modified opts default)]
    (fn [type obj]
      (case type
        "ADDED"    (added obj)
        "DELETED"  (deleted obj)
        "MODIFIED" (modified obj)
        (default type obj)))))


;(def Config k8s.Config)

(def core-api (config k8s.Core_v1Api))

(def apps-api (config k8s.Apps_v1Api))

(def custom-objects (config k8s.Custom_objectsApi))

;(def co-test (.createClusterCustomObject custom-objects "kate.degree9.io" "v1" "tenants" (clj->js {:kind "Tenant" :apiVersion "kate.degree9.io/v1" :metadata {:name "some-tenant"}})))

;(-> co-test
;  (.then js->clj)
;  (.then prn)
;  (.catch prn))
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; Kubernetes Helpers ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn- k8s->clj
  "Converts Kubernetes response to ClojureScript."
  [k8s]
  (js->clj k8s :keywordize-keys true))

(defn- k8s-response [res]
  (obj/get res "body"))

(defn k8s-error [err]
  (let [{:keys [message data code]} (k8s->clj (k8s-response err))]
    (case code
      404 (error/not-found message data)
      409 (error/conflict message data)
      500 (error/general message data)
      (error/general message data))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; Kubernetes Custom Resource ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn- list-customresource
  "List a Kubernetes custom resource."
  [group version plural]
  (-> custom-objects
    (.listClusterCustomObject group version plural)
    (.then k8s-response)
    (.catch k8s-error)))

(defn- get-customresource
  "Get a Kubernetes custom resource."
  [group version plural id]
  (-> custom-objects
    (.getClusterCustomObject group version plural id)
    (.then k8s-response)
    (.catch k8s-error)))

(defn- create-customresource
  "Create a Kubernetes custom resource."
  [group version plural body]
  (-> custom-objects
    (.createClusterCustomObject group version plural (clj->js body))
    (.then k8s-response)
    (.catch k8s-error)))

(defn- replace-customresource
  "Replace a Kubernetes custom resource."
  [group version plural id data]
  (-> custom-objects
    (.replaceClusterCustomObject group version plural id (clj->js data))
    (.then k8s-response)
    (.catch k8s-error)))

(defn- patch-customresource
  "Patch a Kubernetes custom resource."
  [group version plural id data]
  (-> custom-objects
    (.patchClusterCustomObject group version plural id (clj->js data))
    (.then k8s-response)
    (.catch k8s-error)))

(defn- delete-customresource
  "Delete a Kubernetes custom resource."
  [group version plural id opts]
  (-> custom-objects
    (.deleteClusterCustomObject group version plural id opts)
    (.then k8s-response)
    (.catch k8s-error)))

(defn custom-resource [& [opts]]
  (let [kind       (:kind opts)
        group      (:group opts)
        apiversion (:apiVersion opts "v1")
        plural     (:plural opts (s/lower-case (str kind "s")))]
    (reify
      Object
      (setup [this app]
        (obj/set this "kind" kind)
        (obj/set this "group" group)
        (obj/set this "apiVersion" apiversion)
        (obj/set this "plural" plural))
      (find [this params]
        (list-customresource group apiversion plural))
      (get [this id params]
        (get-customresource group apiversion plural id))
      (create [this data params]
        (create-customresource group apiversion plural data))
      (update [this id data params]
        (replace-customresource group apiversion plural id data))
      (patch [this id data params]
        (patch-customresource group apiversion plural id data))
      (remove [this id params]
        (delete-customresource group apiversion plural id (obj/get params "query"))))))
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; Kubernetes Namespace ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn- create-namespace
  "Create a Kubernetes namespace."
  [data]
  (-> core-api
    (.createNamespace data)
    (.then k8s-response)
    (.catch k8s-error)))

(defn- read-namespace
  "Read a Kubernetes namespace."
  [name]
  (-> core-api
    (.readNamespace name)
    (.then k8s-response)
    (.catch k8s-error)))

(defn namespace [& [opts]]
  (let []
    (reify
      Object
      ;(find [this params]
      ;  ())
      (get [this id & [params]]
        (read-namespace id))
      (create [this data & [params]]
        (create-namespace data)))))
      ;(remove [this id params]
      ;  ()))))
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; Kubernetes Secrets ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn- list-secret
  "List all Kubernetes secrets from a Kubernetes namespace ."
  [namespace]
  (-> core-api
    (.listNamespacedSecret namespace)
    (.then k8s-response)
    (.catch k8s-error)))

(defn- read-secret
  "Read a Secret from a Kubernetes namespace."
  [name namespace]
  (-> core-api
    (.readNamespacedSecret name namespace)
    (.then k8s-response)
    (.catch k8s-error)))

(defn- create-secret
  "Create a Kubernetes secret within a Kubernetes namespace."
  [data namespace]
  (-> core-api
    (.createNamespacedSecret namespace data)
    (.then k8s-response)
    (.catch k8s-error)))

(defn secret [& [opts]]
  (let []
    (reify
      Object
      (find [this params]
        (let [namespace (get-in (js->clj params) ["query" "namespace"])]
          (list-secret namespace)))
      (get [this id params]
        (let [namespace (get-in (js->clj params) ["query" "namespace"])]
          (read-secret id namespace)))
      (create [this data & [params]]
        (let [namespace (get-in (js->clj params) ["query" "namespace"])]
          (create-secret data namespace))))))
      ;(remove [this id params]
      ;  ()))))
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; Kubernetes Deployment ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn- list-deployment
  "List all Kubernetes deployments from a Kubernetes namespace ."
  [namespace]
  (-> apps-api
    (.listNamespacedDeployment namespace)
    (.then k8s-response)
    (.catch k8s-error)))

(defn- read-deployment
  "Read a Deployment from a Kubernetes namespace."
  [name namespace]
  (-> apps-api
    (.readNamespacedDeployment name namespace)
    (.then k8s-response)
    (.catch k8s-error)))

(defn- create-deployment
  "Create a Kubernetes deployment within a Kubernetes namespace."
  [data namespace]
  (-> apps-api
    (.createNamespacedDeployment namespace data)
    (.then k8s-response)
    (.catch k8s-error)))

(defn deployment [& [opts]]
  (let []
    (reify
      Object
      (find [this params]
        (let [namespace (get-in (js->clj params) ["query" "namespace"])]
          (list-deployment namespace)))
      (get [this id params]
        (let [namespace (get-in (js->clj params) ["query" "namespace"])]
          (read-deployment id namespace)))
      (create [this data & [params]]
        (let [namespace (get-in (js->clj params) ["query" "namespace"])]
          (create-deployment data namespace))))))
      ;(remove [this id params]
      ;  ()))))
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
