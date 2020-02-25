(ns degree9.kubernetes.namespace
  (:refer-clojure :exclude [namespace])
  (:require [degree9.debug :as dbg]))

(dbg/defdebug debug "degree9:enterprise:kubernetes:namespace")

;; Kubernetes Namespace ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn- list-namespace
  "List a Kubernetes namespace."
  [api]
  (debug "Listing kubernetes namespace" api)
  (-> api
    (.listNamespace)
    (.then k8s-response)
    (.catch k8s-error)))

(defn- create-namespace
  "Create a Kubernetes namespace."
  [api data]
  (debug "Creating kubernetes namespace" api data)
  (-> api
    (.createNamespace data)
    (.then k8s-response)
    (.catch k8s-error)))

(defn- read-namespace
  "Read a Kubernetes namespace."
  [api name]
  (debug "Reading kubernetes namespace" api name)
  (-> api
    (.readNamespace name)
    (.then k8s-response)
    (.catch k8s-error)))

(defn- delete-namespace
  "Delete a Kubernetes namespace."
  [api id]
  (debug "Deleting kubernetes namespace" api id)
  (-> api
    (.deleteNamespace id)
    (.then k8s-response)
    (.catch k8s-error)))

(defn- replace-namespace
  "Replace a Kubernetes namespace."
  [api id data]
  (debug "Replacing kubernetes namespace" api id data)
  (-> api
    (.replaceNamespace id data)
    (.then k8s-response)
    (.catch k8s-error)))

(defn- patch-namespace
  "Patch a Kubernetes namespace."
  [api id data]
  (debug "Patching kubernetes namespace" api id data)
  (-> api
    (.patchNamespace id data)
    (.then k8s-response)
    (.catch k8s-error)))
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
