(ns degree9.kubernetes
  (:refer-clojure :exclude [namespace])
  (:require
   [clojure.string :as s]
   [goog.object :as obj]
   [feathers.errors :as error]
   [degree9.env :as env]
   ["@kubernetes/client-node" :as k8s]
   ["fs" :as fs]
   ["path" :as path]))

;; Kubernetes API ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn kubeconfig []
  (k8s/KubeConfig.))

(defn load-default [config]
  (.loadFromDefault config))

(defn mkclient [config api]
  (.makeApiClient config api))

(defn core-api [config]
  (mkclient config k8s/CoreV1Api))

(defn apps-api [config]
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
  (js->clj k8s :keywordize-keys true))

(defn- k8s-response [res]
  (obj/get res "body"))

(defn k8s-error [err]
  (let [{:keys [message data code]} (k8s->clj (k8s-response err))]
    (.log js/console err)
    (case code
      404 (error/not-found message data)
      409 (error/conflict message data)
      500 (error/general message data)
      (error/general message data))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; Kubernetes Cluster Custom Resource ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn- list-clustercustomresource
  "List a Kubernetes custom resource."
  [api group version plural]
  (-> api
    (.listClusterCustomObject group version plural)
    (.then k8s-response)
    (.catch k8s-error)))

(defn- get-clustercustomresource
  "Get a Kubernetes custom resource."
  [api group version plural id]
  (-> api
    (.getClusterCustomObject group version plural id)
    (.then k8s-response)
    (.catch k8s-error)))

(defn- create-clustercustomresource
  "Create a Kubernetes custom resource."
  [api group version plural body]
  (-> api
    (.createClusterCustomObject group version plural (clj->js body))
    (.then k8s-response)
    (.catch k8s-error)))

(defn- replace-clustercustomresource
  "Replace a Kubernetes custom resource."
  [api group version plural id data]
  (-> api
    (.replaceClusterCustomObject group version plural id (clj->js data))
    (.then k8s-response)
    (.catch k8s-error)))

(defn- patch-clustercustomresource
  "Patch a Kubernetes custom resource."
  [api group version plural id data]
  (-> api
    (.patchClusterCustomObject group version plural id (clj->js data))
    (.then k8s-response)
    (.catch k8s-error)))

(defn- delete-clustercustomresource
  "Delete a Kubernetes custom resource."
  [api group version plural id opts]
  (-> api
    (.deleteClusterCustomObject group version plural id opts)
    (.then k8s-response)
    (.catch k8s-error)))

(defn cluster-custom-resource [& [opts]]
  (let [api        (:api opts)
        kind       (:kind opts)
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
        (list-clustercustomresource api group apiversion plural))
      (get [this id params]
        (get-clustercustomresource api group apiversion plural id))
      (create [this data params]
        (create-clustercustomresource api group apiversion plural data))
      (update [this id data params]
        (replace-clustercustomresource api group apiversion plural id data))
      (patch [this id data params]
        (patch-clustercustomresource api group apiversion plural id data))
      (remove [this id params]
        (delete-clustercustomresource api group apiversion plural id (obj/get params "query"))))))
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; Kubernetes Namespaced Custom Resource ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn- list-namespacedcustomresource
  "List a Kubernetes custom resource."
  [api group version plural]
  (-> api
    (.listNamespacedCustomObject group version plural)
    (.then k8s-response)
    (.catch k8s-error)))

(defn- get-namespacedcustomresource
  "Get a Kubernetes custom resource."
  [api group version plural id]
  (-> api
    (.getNamespacedCustomObject group version plural id)
    (.then k8s-response)
    (.catch k8s-error)))

(defn- create-namespacedcustomresource
  "Create a Kubernetes custom resource."
  [api group version plural body]
  (-> api
    (.createNamespacedCustomObject group version plural (clj->js body))
    (.then k8s-response)
    (.catch k8s-error)))

(defn- replace-namespacedcustomresource
  "Replace a Kubernetes custom resource."
  [api group version plural id data]
  (-> api
    (.replaceNamespacedCustomObject group version plural id (clj->js data))
    (.then k8s-response)
    (.catch k8s-error)))

(defn- patch-namespacedcustomresource
  "Patch a Kubernetes custom resource."
  [api group version plural id data]
  (-> api
    (.patchNamespacedCustomObject group version plural id (clj->js data))
    (.then k8s-response)
    (.catch k8s-error)))

(defn- delete-namespacedcustomresource
  "Delete a Kubernetes custom resource."
  [api group version plural id opts]
  (-> api
    (.deleteNamespacedCustomObject group version plural id opts)
    (.then k8s-response)
    (.catch k8s-error)))

(defn namespaced-custom-resource [& [opts]]
  (let [api        (:api opts)
        kind       (:kind opts)
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
        (list-namespacedcustomresource api group apiversion plural))
      (get [this id params]
        (get-namespacedcustomresource api group apiversion plural id))
      (create [this data params]
        (create-namespacedcustomresource api group apiversion plural data))
      (update [this id data params]
        (replace-namespacedcustomresource api group apiversion plural id data))
      (patch [this id data params]
        (patch-namespacedcustomresource api group apiversion plural id data))
      (remove [this id params]
        (delete-namespacedcustomresource api group apiversion plural id (obj/get params "query"))))))
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; Kubernetes Namespace ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn- list-namespace
  "Create a Kubernetes namespace."
  [api]
  (-> api
    (.listNamespace)
    (.then k8s-response)
    (.catch k8s-error)))

(defn- create-namespace
  "Create a Kubernetes namespace."
  [api data]
  (-> api
    (.createNamespace data)
    (.then k8s-response)
    (.catch k8s-error)))

(defn- read-namespace
  "Read a Kubernetes namespace."
  [api name]
  (-> api
    (.readNamespace name)
    (.then k8s-response)
    (.catch k8s-error)))

(defn- delete-namespace
  "Create a Kubernetes namespace."
  [api id]
  (-> api
    (.deleteNamespace id)
    (.then k8s-response)
    (.catch k8s-error)))

(defn- replace-namespace
  "Create a Kubernetes namespace."
  [api id data]
  (-> api
    (.replaceNamespace id data)
    (.then k8s-response)
    (.catch k8s-error)))

(defn- patch-namespace
  "Create a Kubernetes namespace."
  [api id data]
  (-> api
    (.patchNamespace id data)
    (.then k8s-response)
    (.catch k8s-error)))

(defn namespace [& [opts]]
  (let [api (:api opts)]
    (reify
      Object
      (find [this params]
        (list-namespace api))
      (get [this id params]
        (read-namespace api id))
      (create [this data params]
        (create-namespace api data))
      (update [this id data params]
        (replace-namespace api id data))
      (patch [this id data params]
        (patch-namespace api id data))
      (remove [this id params]
        (delete-namespace api id)))))
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; Kubernetes Secrets ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn- list-secret
  "List all Kubernetes secrets from a Kubernetes namespace ."
  [api namespace]
  (-> api
    (.listNamespacedSecret namespace)
    (.then k8s-response)
    (.catch k8s-error)))

(defn- read-secret
  "Read a Secret from a Kubernetes namespace."
  [api name namespace]
  (-> api
    (.readNamespacedSecret name namespace)
    (.then k8s-response)
    (.catch k8s-error)))

(defn- create-secret
  "Create a Kubernetes secret within a Kubernetes namespace."
  [api data namespace]
  (-> api
    (.createNamespacedSecret namespace data)
    (.then k8s-response)
    (.catch k8s-error)))

(defn- replace-secret
  "Replace a Kubernetes secret."
  [api id namespace data]
  (-> api
    (.replaceNamespacedSecret id namespace data)
    (.then k8s-response)
    (.catch k8s-error)))

(defn- patch-secret
  "Patch a Kubernetes secret."
  [api id namespace data]
  (-> api
    (.patchNamespacedSecret id namespace data)
    (.then k8s-response)
    (.catch k8s-error)))

(defn- delete-secret
  "Delete a Kubernetes secret."
  [api id namespace]
  (-> api
    (.deleteNamespacedSecret id namespace)
    (.then k8s-response)
    (.catch k8s-error)))

(defn secret [& [opts]]
  (let [api (:api opts)]
    (reify
      Object
      (find [this params]
        (let [namespace (get-in (js->clj params) ["query" "namespace"])]
          (list-secret api namespace)))
      (get [this id params]
        (let [namespace (get-in (js->clj params) ["query" "namespace"])]
          (read-secret api id namespace)))
      (create [this data & [params]]
        (let [namespace (get-in (js->clj params) ["query" "namespace"])]
          (create-secret api data namespace)))
      (update [this id data params]
        (let [namespace (get-in (js->clj params) ["query" "namespace"])]
          (replace-secret api id namespace data)))
      (patch [this id data params]
        (let [namespace (get-in (js->clj params) ["query" "namespace"])]
          (patch-secret api id namespace data)))
      (remove [this id params]
        (let [namespace (get-in (js->clj params) ["query" "namespace"])]
          (delete-secret api id namespace))))))
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; Kubernetes Deployment ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn- list-deployment
  "List all Kubernetes deployments from a Kubernetes namespace ."
  [api namespace]
  (-> api
    (.listNamespacedDeployment namespace)
    (.then k8s-response)
    (.catch k8s-error)))

(defn- read-deployment
  "Read a Deployment from a Kubernetes namespace."
  [api name namespace]
  (-> api
    (.readNamespacedDeployment name namespace)
    (.then k8s-response)
    (.catch k8s-error)))

(defn- create-deployment
  "Create a Kubernetes deployment within a Kubernetes namespace."
  [api data namespace]
  (-> api
    (.createNamespacedDeployment namespace data)
    (.then k8s-response)
    (.catch k8s-error)))

(defn- replace-deployment
  "Replace a Kubernetes deployment."
  [api id namespace data]
  (-> api
    (.replaceNamespacedDeployment id namespace data)
    (.then k8s-response)
    (.catch k8s-error)))

(defn- patch-deployment
  "Patch a Kubernetes deployment."
  [api id namespace data]
  (-> api
    (.patchNamespacedDeployment id namespace data)
    (.then k8s-response)
    (.catch k8s-error)))

(defn- delete-deployment
  "Delete a Kubernetes deployment."
  [api id namespace]
  (-> api
    (.deleteNamespacedDeployment id namespace)
    (.then k8s-response)
    (.catch k8s-error)))

(defn deployment [& [opts]]
  (let [api (:api opts)]
    (reify
      Object
      (find [this params]
        (let [namespace (get-in (js->clj params) ["query" "namespace"])]
          (list-deployment api namespace)))
      (get [this id params]
        (let [namespace (get-in (js->clj params) ["query" "namespace"])]
          (read-deployment api id namespace)))
      (create [this data & [params]]
        (let [namespace (get-in (js->clj params) ["query" "namespace"])]
          (create-deployment api data namespace)))
      (update [this id data params]
        (let [namespace (get-in (js->clj params) ["query" "namespace"])]
          (replace-deployment api id namespace data)))
      (patch [this id data params]
        (let [namespace (get-in (js->clj params) ["query" "namespace"])]
          (patch-deployment api id namespace data)))
      (remove [this id params]
        (let [namespace (get-in (js->clj params) ["query" "namespace"])]
          (delete-deployment api id namespace))))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; Kubernetes Service ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn- list-service
  "List all Kubernetes services from a Kubernetes namespace."
  [api namespace]
  (-> api
    (.listNamespacedService namespace)
    (.then k8s-response)
    (.catch k8s-error)))

(defn- create-service
  "Create a Kubernetes service."
  [api namespace data]
  (-> api
    (.createNamespacedService namespace data)
    (.then k8s-response)
    (.catch k8s-error)))

(defn- read-service
  "Read a Kubernetes service."
  [api name namespace]
  (-> api
    (.readNamespacedService name)
    (.then k8s-response)
    (.catch k8s-error)))

(defn- replace-service
  "Replace a Kubernetes service."
  [api id namespace data]
  (-> api
    (.replaceNamespacedService id namespace data)
    (.then k8s-response)
    (.catch k8s-error)))

(defn- patch-service
  "Patch a Kubernetes service."
  [api id namespace data]
  (-> api
    (.patchNamespacedService id namespace data)
    (.then k8s-response)
    (.catch k8s-error)))

(defn- delete-service
  "Delete a Kubernetes service."
  [api id namespace]
  (-> api
    (.deleteNamespacedService id namespace)
    (.then k8s-response)
    (.catch k8s-error)))

(defn service [& [opts]]
  (let [api (:api opts)]
    (reify
      Object
      (find [this params]
        (let [namespace (get-in (js->clj params) ["query" "namespace"])]
          (list-service api namespace)))
      (get [this id & [params]]
        (let [namespace (get-in (js->clj params) ["query" "namespace"])]
          (read-service api id namespace)))
      (create [this data & [params]]
        (let [namespace (get-in (js->clj params) ["query" "namespace"])]
          (create-service api namespace data)))
      (update [this id data params]
        (let [namespace (get-in (js->clj params) ["query" "namespace"])]
          (replace-service api id namespace data)))
      (patch [this id data params]
        (let [namespace (get-in (js->clj params) ["query" "namespace"])]
          (patch-service api id namespace data)))
      (remove [this id params]
        (let [namespace (get-in (js->clj params) ["query" "namespace"])]
          (delete-service api id namespace))))))
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; Kubernetes Ingress ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn- list-ingress
  "List all Kubernetes ingress from a Kubernetes namespace."
  [api namespace]
  (-> api
    (.listNamespacedIngress namespace)
    (.then k8s-response)
    (.catch k8s-error)))

(defn- create-ingress
  "Create a Kubernetes ingress."
  [api namespace data]
  (-> api
    (.createNamespacedIngress namespace data)
    (.then k8s-response)
    (.catch k8s-error)))

(defn- read-ingress
  "Read a Kubernetes ingress."
  [api name namespace]
  (-> api
    (.readNamespacedIngress name)
    (.then k8s-response)
    (.catch k8s-error)))

(defn- replace-ingress
  "Replace a Kubernetes ingress."
  [api id namespace data]
  (-> api
    (.replaceNamespacedIngress id namespace data)
    (.then k8s-response)
    (.catch k8s-error)))

(defn- patch-ingress
  "Patch a Kubernetes ingress."
  [api id namespace data]
  (-> api
    (.patchNamespacedIngress id namespace data)
    (.then k8s-response)
    (.catch k8s-error)))

(defn- delete-ingress
  "Delete a Kubernetes ingress."
  [api id namespace]
  (-> api
    (.deleteNamespacedIngress id namespace)
    (.then k8s-response)
    (.catch k8s-error)))

(defn ingress [& [opts]]
  (let [api (:api opts)]
    (reify
      Object
      (find [this params]
        (let [namespace (get-in (js->clj params) ["query" "namespace"])]
          (list-ingress api namespace)))
      (get [this id & [params]]
        (let [namespace (get-in (js->clj params) ["query" "namespace"])]
          (read-ingress api id namespace)))
      (create [this data & [params]]
        (let [namespace (get-in (js->clj params) ["query" "namespace"])]
          (create-ingress api namespace data)))
      (update [this id data params]
        (let [namespace (get-in (js->clj params) ["query" "namespace"])]
          (replace-ingress api id namespace data)))
      (patch [this id data params]
        (let [namespace (get-in (js->clj params) ["query" "namespace"])]
          (patch-ingress api id namespace data)))
      (remove [this id params]
        (let [namespace (get-in (js->clj params) ["query" "namespace"])]
          (delete-ingress api id namespace))))))
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
