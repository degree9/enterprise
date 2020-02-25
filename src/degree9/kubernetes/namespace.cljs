(ns degree9.kubernetes.namespace
  (:refer-clojure :exclude [namespace])
  (:require [degree9.debug :as dbg]))

(dbg/defdebug debug "degree9:enterprise:kubernetes:namespace")

;; Kubernetes Namespace ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn- list-namespace
  "List a Kubernetes namespace."
  [api]
  (debug "Listing kubernetes namespace" api)
  (.listNamespace api))

(defn- create-namespace
  "Create a Kubernetes namespace."
  [api data]
  (debug "Creating kubernetes namespace" api data)
  (.createNamespace api data))

(defn- read-namespace
  "Read a Kubernetes namespace."
  [api name]
  (debug "Reading kubernetes namespace" api name)
  (.readNamespace api name))

(defn- delete-namespace
  "Delete a Kubernetes namespace."
  [api id]
  (debug "Deleting kubernetes namespace" api id)
  (.deleteNamespace api id))

(defn- replace-namespace
  "Replace a Kubernetes namespace."
  [api id data]
  (debug "Replacing kubernetes namespace" api id data)
  (.replaceNamespace api id data))

(defn- patch-namespace
  "Patch a Kubernetes namespace."
  [api id data]
  (debug "Patching kubernetes namespace" api id data)
  (.patchNamespace api id data))
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
