(ns degree9.kubernetes.secret
  (:require [degree9.debug :as dbg]))

(dbg/defdebug debug "degree9:enterprise:kubernetes:secret")

;; Kubernetes Secrets ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn- list-secret
  "List all Kubernetes secrets from a Kubernetes namespace ."
  [api namespace]
  (debug "Listing kubernetes secrets from namespace" api namespace)
  (.listNamespacedSecret api namespace))

(defn- read-secret
  "Read a Secret from a Kubernetes namespace."
  [api name namespace]
  (debug "Reading kubernetes secret from namespace" api name namespace)
  (.readNamespacedSecret api name namespace))

(defn- create-secret
  "Create a Kubernetes secret within a Kubernetes namespace."
  [api data namespace]
  (debug "Creating kubernetes secret from namespace" api data namespace)
  (.createNamespacedSecret api namespace data))

(defn- replace-secret
  "Replace a Kubernetes secret."
  [api id namespace data]
  (debug "Replacing kubernetes secret from namespace" api id namespace data)
  (.replaceNamespacedSecret api id namespace data))

(defn- patch-secret
  "Patch a Kubernetes secret."
  [api id namespace data]
  (debug "Patching kubernetes secret from namespace" api id namespace data)
  (.patchNamespacedSecret api id namespace data))

(defn- delete-secret
  "Delete a Kubernetes secret."
  [api id namespace]
  (debug "Deleting kubernetes secret namespace" api id namespace)
  (.deleteNamespacedSecret api id namespace))
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
