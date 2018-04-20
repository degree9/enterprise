(ns degree9.kubernetes
  (:refer-clojure :exclude [namespace])
  (:require
    [cljs.nodejs :as node]
    [goog.object :as obj]
    [feathers.errors :as error]))

;; Kubernetes API ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(def k8s (node/require "@kubernetes/client-node"))

(def path (node/require "path"))

(def fs (node/require   "fs"))

(def svc-root  (str "/var/run/secrets/kubernetes.io/serviceaccount"))
(def svc-ca    (str svc-root "/ca.crt"))
(def svc-token (str svc-root "/token"))

(def k8s-config
  (or js/process.env.KUBECONFIG (.join path js/process.env.HOME ".kube" "config")))

(defn file-exists? [path]
  (.existsSync fs path))

(defn mkconfig []
  (k8s.KubeConfig.))

(defn config-file [path]
  (let [kconfig (mkconfig)]
    (.loadFromFile kconfig path)
    kconfig))


;(defn get-k8sconfig
;  (let [kenv js/process.env.KUBECONFIG
;        kconfig (.join path js/process.env.HOME ".kube" "config")
;    (cond
;      (file-exists? kenv) kenv
;      (file-exists? kconfig) kconfig))

;(defn kubernetes [& [api config]]
;  (if-let [svc-token (and (file-exists? svc-token) svc-token)]
      ;(file-exists? kconfig)
      ;(let [kconf   (config-file kconfig)
      ;      kserver (goog.object/get kconf "server")
      ;      k8s-api (k8s.Core_v1Api. kserver)
      ;  (.setDefaultAuthentication k8s-api kconf)
      ;  k8s-api
;      (file-exists? (:token svc-account)) (.log js/console "K8S Api Should Load service account token."))))



;(.log js/console "K8S" (kubernetes))


(def Config k8s.Config)

(def apps-api (k8s.Apps_v1Api.))

(def core-api (k8s.Config.defaultClient))
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; Kubernetes Helpers ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn- k8s->clj
  "Converts Kubernetes response to ClojureScript."
  [k8s]
  (js->clj k8s :keywordize-keys true))

(defn- k8s-response [res]
  (clj->js (:body (k8s->clj res))))

(defn- k8s-error
  "Format a Kubernetes error as a [message details] pair."
  [err]
  (let [{:keys [body]} (k8s->clj err)
        message (:message body)
        details (:details body)]
    [message (clj->js details)]))

(defn- not-found
  "Emits a NotFound error from a Kubernetes error response."
  [err]
  (let [[message details] (k8s-error err)]
    (error/not-found message details)))

(defn- already-exists
  "Emits a Conflict error from a Kubernetes error response."
  [err]
  (let [[message details] (k8s-error err)]
    (error/conflict message details)))

(defn- create-namespace
  "Create a Kubernetes namespace."
  [data]
  (-> core-api
    (.createNamespace data)
    (.then k8s-response)
    (.catch already-exists)))

(defn- read-namespace
  "Read a Kubernetes namespace."
  [name]
  (-> core-api
    (.readNamespace name)
    (.then k8s-response)
    (.catch not-found)))

(defn- read-deployment
  "List Deployments from a Kubernetes namespace."
  [name namespace]
  (-> apps-api
    (.readNamespacedDeployment name namespace)
    (.then k8s-response)
    (.catch prn)))
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; Kubernetes Services ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
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

(defn deployment [& [opts]]
  (let []
    (reify
      Object
      ;(find [this params]
      ;  ())
      (get [this id & [{:keys [] :as params}]]
        (.log js/console id params)
        (read-deployment id nil)))))
      ;(create [this data & [params]]
      ;  (create-namespace data))))
      ;(remove [this id params]
      ;  ()))))
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
