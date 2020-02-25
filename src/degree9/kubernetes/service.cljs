(ns degree9.kubernetes.service
  (:require [degree9.debug :as dbg]))

(dbg/defdebug debug "degree9:enterprise:kubernetes:service")

;; Kubernetes Service ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn- list-service
  "List all Kubernetes services from a Kubernetes namespace."
  [api namespace]
  (debug "Listing all Kubernetes services from namespace" api namespace)
  (.listNamespacedService api namespace))

(defn- create-service
  "Create a Kubernetes service."
  [api namespace data]
  (debug "Creating all Kubernetes services from a Kubernetes namespace" api namespace data)
  (.createNamespacedService api namespace data))

(defn- read-service
  "Read a Kubernetes service."
  [api name namespace]
  (debug "Reading all Kubernetes services from a Kubernetes namespace" api name namespace)
  (.readNamespacedService api name))

(defn- replace-service
  "Replace a Kubernetes service."
  [api id namespace data]
  (debug "Replacing all Kubernetes services from a Kubernetes namespace" api id namespace data)
  (.replaceNamespacedService api id namespace data))

(defn- patch-service
  "Patch a Kubernetes service."
  [api id namespace data]
  (debug "Patching all Kubernetes services from a Kubernetes namespace" api id namespace data)
  (.patchNamespacedService api id namespace data))

(defn- delete-service
  "Delete a Kubernetes service."
  [api id namespace]
  (debug "Deleting all Kubernetes services from a Kubernetes namespace" api id namespace)
  (.deleteNamespacedService api id namespace))
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
