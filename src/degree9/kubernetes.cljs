(ns degree9.kubernetes
  (:refer-clojure :exclude [namespace])
  (:require
   ["@kubernetes/client-node" :as k8s]
   [goog.object :as obj]
   [clojure.string :as s]
   [feathers.errors :as error]
   [degree9.env :as env]
   [degree9.debug :as dbg]))

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
  (mkclient config k8s/AppsV1Api))

(defn net-api
  "Initializes Kubernetes NetworkingV1 API."
  [config]
  (mkclient config k8s/NetworkingV1beta1Api))
  
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
