(ns degree9.kubernetes.service
  (:require [degree9.debug :as dbg]))

(def ^:private debug (dbg "degree9:enterprise:kubernetes:service"))
;; Kubernetes Service ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn- list-service
  "List all Kubernetes services from a Kubernetes namespace."
  [api namespace]
  (debug "List all Kubernetes services from namespace" api namespace)
  (-> api
    (.listNamespacedService namespace)
    (.then k8s-response)
    (.catch k8s-error)))

(defn- create-service
  "Create a Kubernetes service."
  [api namespace data]
  (debug "Create all Kubernetes services from a Kubernetes namespace" api namespace data)
  (-> api
    (.createNamespacedService namespace data)
    (.then k8s-response)
    (.catch k8s-error)))

(defn- read-service
  "Read a Kubernetes service."
  [api name namespace]
  (debug "Read all Kubernetes services from a Kubernetes namespace" api name namespace)
  (-> api
    (.readNamespacedService name)
    (.then k8s-response)
    (.catch k8s-error)))

(defn- replace-service
  "Replace a Kubernetes service."
  [api id namespace data]
  (debug "Replace all Kubernetes services from a Kubernetes namespace" api id namespace data)
  (-> api
    (.replaceNamespacedService id namespace data)
    (.then k8s-response)
    (.catch k8s-error)))

(defn- patch-service
  "Patch a Kubernetes service."
  [api id namespace data]
  (debug "Patch all Kubernetes services from a Kubernetes namespace" api id namespace data)
  (-> api
    (.patchNamespacedService id namespace data)
    (.then k8s-response)
    (.catch k8s-error)))

(defn- delete-service
  "Delete a Kubernetes service."
  [api id namespace]
  (debug "Delete all Kubernetes services from a Kubernetes namespace" api id namespace)
  (-> api
    (.deleteNamespacedService id namespace)
    (.then k8s-response)
    (.catch k8s-error)))

(defn service [& [opts]]
  (let [api (:api opts)])
  (debug "Initialize all Kubernetes services from Kubernetes namespace" api
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
