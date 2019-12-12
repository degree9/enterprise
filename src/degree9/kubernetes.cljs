(ns degree9.kubernetes
  (:refer-clojure :exclude [namespace])
  (:require
   ["@kubernetes/client-node" :as k8s]
   [goog.object :as obj]
   [feathers.errors :as error]
   [degree9.env :as env]
   [degree9.debug :as dbg]
   [degree9.kubernetes.custom-resource :as custom-resource]
   [degree9.kubernetes.deployment :as deployment]
   [degree9.kubernetes.ingress :as ingress]
   [degree9.kubernetes.namespace :as namespace]
   [degree9.kubernetes.secret :as secret]
   [degree9.kubernetes.service :as service]))

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
  (debug "Converts kubernetes response to cljs" k8s)
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
      500 (error/general message data)
      (error/general message data))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(def cluster-custom-resource custom-resource/cluster-custom-resource)

(def namespaced-custom-resource custom-resource/namespaced-custom-resource)

(def deployment deployment/deployment)

(def ingress ingress/ingress)

(def namespace namespace/namespace)

(def secret secret/secret)

(def service service/service)
