(ns degree9.kubernetes.ingress
  (:require [degree9.debug :as dbg]))

(dbg/defdebug debug "degree9:enterprise:kubernetes:ingress")

;; Kubernetes Ingress ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn- list-ingress
  "List all Kubernetes ingress from a Kubernetes namespace."
  [api namespace]
  (debug "Listing all Kubernetes ingress from Kubernetes namespace" api namespace)
  (.listNamespacedIngress api namespace))

(defn- create-ingress
  "Create a Kubernetes ingress."
  [api namespace data]
  (debug "Creating all Kubernetes ingress from Kubernetes namespace" api namespace data)
  (.createNamespacedIngress api namespace data))

(defn- read-ingress
  "Read a Kubernetes ingress."
  [api name namespace]
  (debug "Reading all Kubernetes ingress from Kubernetes namespace" api name namespace)
  (.readNamespacedIngress api name))

(defn- replace-ingress
  "Replace a Kubernetes ingress."
  [api id namespace data]
  (debug "Replacing all Kubernetes ingress from Kubernetes namespace" api id namespace data)
  (.replaceNamespacedIngress api id namespace data))

(defn- patch-ingress
  "Patch a Kubernetes ingress."
  [api id namespace data]
  (debug "Patching all Kubernetes ingress from Kubernetes namespace" api id namespace data)
  (.patchNamespacedIngress api id namespace data))

(defn- delete-ingress
  "Delete a Kubernetes ingress."
  [api id namespace]
  (debug "Deleting all Kubernetes ingress from Kubernetes namespace" api id namespace)
  (.deleteNamespacedIngress api id namespace))
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
