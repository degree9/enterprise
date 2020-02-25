(ns degree9.kubernetes.secret
  (:require [degree9.debug :as dbg]))

(dbg/defdebug debug "degree9:enterprise:kubernetes:secret")

;; Kubernetes Secrets ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn- list-secret
  "List all Kubernetes secrets from a Kubernetes namespace ."
  [api namespace]
  (debug "Listing kubernetes secrets from namespace" api namespace)
  (-> api
    (.listNamespacedSecret namespace)
    (.then k8s-response)
    (.catch k8s-error)))

(defn- read-secret
  "Read a Secret from a Kubernetes namespace."
  [api name namespace]
  (debug "Reading kubernetes secret from namespace" api name namespace)
  (-> api
    (.readNamespacedSecret name namespace)
    (.then k8s-response)
    (.catch k8s-error)))

(defn- create-secret
  "Create a Kubernetes secret within a Kubernetes namespace."
  [api data namespace]
  (debug "Creating kubernetes secret from namespace" api data namespace)
  (-> api
    (.createNamespacedSecret namespace data)
    (.then k8s-response)
    (.catch k8s-error)))

(defn- replace-secret
  "Replace a Kubernetes secret."
  [api id namespace data]
  (debug "Replacing kubernetes secret from namespace" api id namespace data)
  (-> api
    (.replaceNamespacedSecret id namespace data)
    (.then k8s-response)
    (.catch k8s-error)))

(defn- patch-secret
  "Patch a Kubernetes secret."
  [api id namespace data]
  (debug "Patching kubernetes secret from namespace" api id namespace data)
  (-> api
    (.patchNamespacedSecret id namespace data)
    (.then k8s-response)
    (.catch k8s-error)))

(defn- delete-secret
  "Delete a Kubernetes secret."
  [api id namespace]
  (debug "Deleting kubernetes secret namespace" api id namespace)
  (-> api
    (.deleteNamespacedSecret id namespace)
    (.then k8s-response)
    (.catch k8s-error)))
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
