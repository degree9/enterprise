(ns degree9.kubernetes.service
  (:require [degree9.debug :as dbg]))

(dbg/defdebug debug "degree9:enterprise:kubernetes:service")
;; Kubernetes Service ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn- list-service
  "List all Kubernetes services from a Kubernetes namespace."
  [api namespace]
  (debug "Listing all Kubernetes services from namespace" api namespace)
  (-> api
    (.listNamespacedService namespace)
    (.then k8s-response)
    (.catch k8s-error)))

(defn- create-service
  "Create a Kubernetes service."
  [api namespace data]
  (debug "Creating all Kubernetes services from a Kubernetes namespace" api namespace data)
  (-> api
    (.createNamespacedService namespace data)
    (.then k8s-response)
    (.catch k8s-error)))

(defn- read-service
  "Read a Kubernetes service."
  [api name namespace]
  (debug "Reading all Kubernetes services from a Kubernetes namespace" api name namespace)
  (-> api
    (.readNamespacedService name)
    (.then k8s-response)
    (.catch k8s-error)))

(defn- replace-service
  "Replace a Kubernetes service."
  [api id namespace data]
  (debug "Replacing all Kubernetes services from a Kubernetes namespace" api id namespace data)
  (-> api
    (.replaceNamespacedService id namespace data)
    (.then k8s-response)
    (.catch k8s-error)))

(defn- patch-service
  "Patch a Kubernetes service."
  [api id namespace data]
  (debug "Patching all Kubernetes services from a Kubernetes namespace" api id namespace data)
  (-> api
    (.patchNamespacedService id namespace data)
    (.then k8s-response)
    (.catch k8s-error)))

(defn- delete-service
  "Delete a Kubernetes service."
  [api id namespace]
  (debug "Deleting all Kubernetes services from a Kubernetes namespace" api id namespace)
  (-> api
    (.deleteNamespacedService id namespace)
    (.then k8s-response)
    (.catch k8s-error)))
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
