(ns degree9.kubernetes
  (:refer-clojure :exclude [namespace])
  (:require
   ["@kubernetes/client-node" :as k8s]
   [goog.object :as obj]
   [clojure.string :as s]
   [feathers.errors :as error]
   [degree9.env :as env]
   [degree9.debug :as dbg]
   [degree9.kubernetes.custom-resource :as crd]
   [degree9.kubernetes.deployment :as deploy]
   [degree9.kubernetes.ingress :as ing]
   [degree9.kubernetes.namespace :as nspace]
   [degree9.kubernetes.secret :as sec]
   [degree9.kubernetes.service :as svc]))

(dbg/defdebug debug "degree9:enterprise:kubernetes")

;; Kubernetes API ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn kubeconfig
  "Initializing the kubeconfig"
  []
  (debug "Initializing kubeconfig")
  (k8s/KubeConfig.))

(defn load-default
  "Loads the default configuration"
  [config]
  (debug "Loading default configuration")
  (.loadFromDefault config))

(defn mkclient
  "Configures the API client"
  [config api]
  (debug "Configuring API client" api)
  (.makeApiClient config api))

(defn core-api
  "Initializes Kubernetes CoreV1 API."
  [config]
  (mkclient config k8s/CoreV1Api))

(defn apps-api
  "Initializes Kubernetes AppsV1 API."
  [config]
  (mkclient config k8s/appsV1Api))

; (defn watcher
;   "A watcher for Kubernetes, implements reconnecting by calling watcher again once stream closes."
;   ([path callback]
;    (watcher path callback
;      #(watcher path callback)))
;   ([path callback reconnect-callback]
;    (watcher path #js{} callback reconnect-callback))
;   ([path opts callback reconnect-callback]
;    (.watch (k8s/Watch. (kube-config))
;      path
;      opts
;      callback
;      reconnect-callback)))
;
; (defn watch-handler
;   "Returns a default watch-handler which prints to console."
;   [& [opts]]
;   (let [default  (:default  opts #(.log js/console %))
;         added    (:added    opts default)
;         deleted  (:deleted  opts default)
;         modified (:modified opts default)]
;     (fn [type obj]
;       (case type
;         "ADDED"    (added obj)
;         "DELETED"  (deleted obj)
;         "MODIFIED" (modified obj)
;         (default type obj)))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; Kubernetes Helpers ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn- k8s->clj
  "Converts Kubernetes response to ClojureScript."
  [k8s]
  (debug "Converting kubernetes response to cljs" k8s)
  (js->clj k8s :keywordize-keys true))

(defn- k8s-response [res]
  (obj/get res "body"))

(defn k8s-error
  "Converts Kubernetes error response to FeathersJS error object."
  [err]
  (let [{:keys [message data code]} (k8s->clj (k8s-response err))]
    (case code
      404 (error/not-found message data)
      409 (error/conflict message data)
      500 (error/general-error message data)
      (error/general-error message data))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn cluster-custom-resource [& [opts]]
  (let [api        (:api opts)
        kind       (:kind opts)
        group      (:group opts)
        apiversion (:apiVersion opts "v1")
        plural     (:plural opts (s/lower-case (str kind "s")))]
    (debug "Initializing cluster custom resource:" api kind group apiversion plural)
    (reify
      Object
      (setup [this app]
        (obj/set this "kind" kind)
        (obj/set this "group" group)
        (obj/set this "apiVersion" apiversion)
        (obj/set this "plural" plural))
      (find [this params]
        (-> (crd/list-clustercustomresource api group apiversion plural)
          (.then k8s-response)
          (.catch k8s-error)))
      (get [this id params]
        (-> (crd/get-clustercustomresource api group apiversion plural id)
          (.then k8s-response)
          (.catch k8s-error)))
      (create [this data params]
        (-> (crd/create-clustercustomresource api group apiversion plural data)
          (.then k8s-response)
          (.catch k8s-error)))
      (update [this id data params]
        (-> (crd/replace-clustercustomresource api group apiversion plural id data)
          (.then k8s-response)
          (.catch k8s-error)))
      (patch [this id data params]
        (-> (crd/patch-clustercustomresource api group apiversion plural id data)
          (.then k8s-response)
          (.catch k8s-error)))
      (remove [this id params]
        (-> (crd/delete-clustercustomresource api group apiversion plural id (obj/get params "query"))
          (.then k8s-response)
          (.catch k8s-error))))))

(defn namespaced-custom-resource [& [opts]]
  (let [api        (:api opts)
        kind       (:kind opts)
        group      (:group opts)
        apiversion (:apiVersion opts "v1")
        plural     (:plural opts (s/lower-case (str kind "s")))]
    (debug "Initializing kubernetes custom resource" api kind group apiversion plural)
    (reify
      Object
      (setup [this app]
        (obj/set this "kind" kind)
        (obj/set this "group" group)
        (obj/set this "apiVersion" apiversion)
        (obj/set this "plural" plural))
      (find [this params]
        (-> (crd/list-namespacedcustomresource api group apiversion plural)
          (.then k8s-response)
          (.catch k8s-error)))
      (get [this id params]
        (-> (crd/get-namespacedcustomresource api group apiversion plural id)
          (.then k8s-response)
          (.catch k8s-error)))
      (create [this data params]
        (-> (crd/create-namespacedcustomresource api group apiversion plural data)
          (.then k8s-response)
          (.catch k8s-error)))
      (update [this id data params]
        (-> (crd/replace-namespacedcustomresource api group apiversion plural id data)
          (.then k8s-response)
          (.catch k8s-error)))
      (patch [this id data params]
        (-> (crd/patch-namespacedcustomresource api group apiversion plural id data)
          (.then k8s-response)
          (.catch k8s-error)))
      (remove [this id params]
        (-> (crd/delete-namespacedcustomresource api group apiversion plural id (obj/get params "query"))
          (.then k8s-response)
          (.catch k8s-error))))))

(defn deployment [& [opts]]
  (let [api (:api opts)])
  (debug "Initializing all Kubernetes deployments from namespace" api
    (reify
      Object
      (find [this params]
        (let [namespace (get-in (js->clj params) ["query" "namespace"])]
          (-> (deploy/list-deployment api namespace)
            (.then k8s-response)
            (.catch k8s-error))))
      (get [this id params]
        (let [namespace (get-in (js->clj params) ["query" "namespace"])]
          (-> (deploy/read-deployment api id namespace)
            (.then k8s-response)
            (.catch k8s-error))))
      (create [this data & [params]]
        (let [namespace (get-in (js->clj params) ["query" "namespace"])]
          (-> (deploy/create-deployment api data namespace)
            (.then k8s-response)
            (.catch k8s-error))))
      (update [this id data params]
        (let [namespace (get-in (js->clj params) ["query" "namespace"])]
          (-> (deploy/replace-deployment api id namespace data)
            (.then k8s-response)
            (.catch k8s-error))))
      (patch [this id data params]
        (let [namespace (get-in (js->clj params) ["query" "namespace"])]
          (-> (deploy/patch-deployment api id namespace data)
            (.then k8s-response)
            (.catch k8s-error))))
      (remove [this id params]
        (let [namespace (get-in (js->clj params) ["query" "namespace"])]
          (-> (deploy/delete-deployment api id namespace)
            (.then k8s-response)
            (.catch k8s-error)))))))

(defn ingress [& [opts]]
  (let [api (:api opts)])
  (debug "Initializing all Kubernetes ingress from Kubernetes namespace" api
    (reify
      Object
      (find [this params]
        (let [namespace (get-in (js->clj params) ["query" "namespace"])]
          (-> (ing/list-ingress api namespace)
            (.then k8s-response)
            (.catch k8s-error))))
      (get [this id & [params]]
        (let [namespace (get-in (js->clj params) ["query" "namespace"])]
          (-> (ing/read-ingress api id namespace)
            (.then k8s-response)
            (.catch k8s-error))))
      (create [this data & [params]]
        (let [namespace (get-in (js->clj params) ["query" "namespace"])]
          (-> (ing/create-ingress api namespace data)
            (.then k8s-response)
            (.catch k8s-error))))
      (update [this id data params]
        (let [namespace (get-in (js->clj params) ["query" "namespace"])]
          (-> (ing/replace-ingress api id namespace data)
            (.then k8s-response)
            (.catch k8s-error))))
      (patch [this id data params]
        (let [namespace (get-in (js->clj params) ["query" "namespace"])]
          (-> (ing/patch-ingress api id namespace data)
            (.then k8s-response)
            (.catch k8s-error))))
      (remove [this id params]
        (let [namespace (get-in (js->clj params) ["query" "namespace"])]
          (-> (ing/delete-ingress api id namespace)
            (.then k8s-response)
            (.catch k8s-error)))))))

(defn namespace [& [opts]]
  (let [api (:api opts)])
  (debug "Initializing kubernetes namespace" api id
    (reify
      Object
      (find [this params]
        (-> (nspace/list-namespace api)
          (.then k8s-response)
          (.catch k8s-error)))
      (get [this id params]
        (-> (nspace/read-namespace api id)
          (.then k8s-response)
          (.catch k8s-error)))
      (create [this data params]
        (-> (nspace/create-namespace api data)
          (.then k8s-response)
          (.catch k8s-error)))
      (update [this id data params]
        (-> (nspace/replace-namespace api id data)
          (.then k8s-response)
          (.catch k8s-error)))
      (patch [this id data params]
        (-> (nspace/patch-namespace api id data)
          (.then k8s-response)
          (.catch k8s-error)))
      (remove [this id params]
        (-> (nspace/delete-namespace api id)
          (.then k8s-response)
          (.catch k8s-error))))))

(defn secret [& [opts]]
  (let [api (:api opts)])
  (debug "Initializing kubernetes secret from namespace" api
    (reify
      Object
      (find [this params]
        (let [namespace (get-in (js->clj params) ["query" "namespace"])]
          (-> (sec/list-secret api namespace)
            (.then k8s-response)
            (.catch k8s-error))))
      (get [this id params]
        (let [namespace (get-in (js->clj params) ["query" "namespace"])]
          (-> (sec/read-secret api id namespace)
            (.then k8s-response)
            (.catch k8s-error))))
      (create [this data & [params]]
        (let [namespace (get-in (js->clj params) ["query" "namespace"])]
          (-> (sec/create-secret api data namespace)
            (.then k8s-response)
            (.catch k8s-error))))
      (update [this id data params]
        (let [namespace (get-in (js->clj params) ["query" "namespace"])]
          (-> (sec/replace-secret api id namespace data)
            (.then k8s-response)
            (.catch k8s-error))))
      (patch [this id data params]
        (let [namespace (get-in (js->clj params) ["query" "namespace"])]
          (-> (sec/patch-secret api id namespace data)
            (.then k8s-response)
            (.catch k8s-error))))
      (remove [this id params]
        (let [namespace (get-in (js->clj params) ["query" "namespace"])]
          (-> (sec/delete-secret api id namespace)
            (.then k8s-response)
            (.catch k8s-error)))))))

(defn service [& [opts]]
  (let [api (:api opts)])
  (debug "Initializing all Kubernetes services from Kubernetes namespace" api
    (reify
      Object
      (find [this params]
        (let [namespace (get-in (js->clj params) ["query" "namespace"])]
          (-> (svc/list-service api namespace)
            (.then k8s-response)
            (.catch k8s-error))))
      (get [this id & [params]]
        (let [namespace (get-in (js->clj params) ["query" "namespace"])]
          (-> (svc/read-service api id namespace)
            (.then k8s-response)
            (.catch k8s-error))))
      (create [this data & [params]]
        (let [namespace (get-in (js->clj params) ["query" "namespace"])]
          (-> (svc/create-service api namespace data)
            (.then k8s-response)
            (.catch k8s-error))))
      (update [this id data params]
        (let [namespace (get-in (js->clj params) ["query" "namespace"])]
          (-> (svc/replace-service api id namespace data)
            (.then k8s-response)
            (.catch k8s-error))))
      (patch [this id data params]
        (let [namespace (get-in (js->clj params) ["query" "namespace"])]
          (-> (svc/patch-service api id namespace data)
            (.then k8s-response)
            (.catch k8s-error))))
      (remove [this id params]
        (let [namespace (get-in (js->clj params) ["query" "namespace"])]
          (-> (svc/delete-service api id namespace)
            (.then k8s-response)
            (.catch k8s-error)))))))
