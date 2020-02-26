(ns degree9.kubernetes.service
  (:require [degree9.debug :as dbg]
            [degree9.kubernetes.core :as k8s]))

(dbg/defdebug debug "degree9:enterprise:kubernetes:service")

;; Kubernetes Service ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn- list-service
  "List all Kubernetes services from a Kubernetes namespace."
  [api namespace]
  (debug "Listing all Kubernetes services from namespace" api namespace)
  (-> (.listNamespacedService api namespace)
    (.then k8s/k8s-response)
    (.catch k8s/k8s-error)))


(defn- create-service
  "Create a Kubernetes service."
  [api namespace data]
  (debug "Creating all Kubernetes services from a Kubernetes namespace" api namespace data)
  (-> (.createNamespacedService api namespace data)
    (.then k8s/k8s-response)
    (.catch k8s/k8s-error)))

(defn- read-service
  "Read a Kubernetes service."
  [api name namespace]
  (debug "Reading all Kubernetes services from a Kubernetes namespace" api name namespace)
  (-> (.readNamespacedService api name)
    (.then k8s/k8s-response)
    (.catch k8s/k8s-error)))

(defn- replace-service
  "Replace a Kubernetes service."
  [api id namespace data]
  (debug "Replacing all Kubernetes services from a Kubernetes namespace" api id namespace data)
  (-> (.replaceNamespacedService api id namespace data)
    (.then k8s/k8s-response)
    (.catch k8s/k8s-error)))

(defn- patch-service
  "Patch a Kubernetes service."
  [api id namespace data]
  (debug "Patching all Kubernetes services from a Kubernetes namespace" api id namespace data)
  (-> (.patchNamespacedService api id namespace data)
    (.then k8s/k8s-response)
    (.catch k8s/k8s-error)))

(defn- delete-service
  "Delete a Kubernetes service."
  [api id namespace]
  (debug "Deleting all Kubernetes services from a Kubernetes namespace" api id namespace)
  (-> (.deleteNamespacedService api id namespace)
    (.then k8s/k8s-response)
    (.catch k8s/k8s-error)))

(defn service [& [opts]]
  (let [api (:api opts)]
    (debug "Initializing all Kubernetes services from Kubernetes namespace" api)
    (reify
      Object
      (find [this params]
        (let [namespace (get-in (js->clj params) ["query" "namespace"])]
          (service/list-service api namespace)))
      (get [this id & [params]]
        (let [namespace (get-in (js->clj params) ["query" "namespace"])]
          (service/read-service api id namespace)))
      (create [this data & [params]]
        (let [namespace (get-in (js->clj params) ["query" "namespace"])]
          (service/create-service api namespace data)))
      (update [this id data params]
        (let [namespace (get-in (js->clj params) ["query" "namespace"])]
          (service/replace-service api id namespace data)))
      (patch [this id data params]
        (let [namespace (get-in (js->clj params) ["query" "namespace"])]
          (service/patch-service api id namespace data)))
      (remove [this id params]
        (let [namespace (get-in (js->clj params) ["query" "namespace"])]
          (service/delete-service api id namespace))))))
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
