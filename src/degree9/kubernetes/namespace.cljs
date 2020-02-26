(ns degree9.kubernetes.namespace
  (:refer-clojure :exclude [namespace])
  (:require [degree9.debug :as dbg]))

(dbg/defdebug debug "degree9:enterprise:kubernetes:namespace")

;; Kubernetes Namespace ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn- list-namespace
  "List a Kubernetes namespace."
  [api]
  (debug "Listing kubernetes namespace" api)
  (-> (.listNamespace api)
    (.then k8s-response)
    (.catch k8s-error)))

(defn- create-namespace
  "Create a Kubernetes namespace."
  [api data]
  (debug "Creating kubernetes namespace" api data)
  (-> (.createNamespace api data)
    (.then k8s-response)
    (.catch k8s-error)))

(defn- read-namespace
  "Read a Kubernetes namespace."
  [api name]
  (debug "Reading kubernetes namespace" api name)
  (-> (.readNamespace api name)
    (.then k8s-response)
    (.catch k8s-error)))

(defn- delete-namespace
  "Delete a Kubernetes namespace."
  [api id]
  (debug "Deleting kubernetes namespace" api id)
  (-> (.deleteNamespace api id)
    (.then k8s-response)
    (.catch k8s-error)))

(defn- replace-namespace
  "Replace a Kubernetes namespace."
  [api id data]
  (debug "Replacing kubernetes namespace" api id data)
  (-> (.replaceNamespace api id data)
      (.then k8s-response)
      (.catch k8s-error)))

(defn- patch-namespace
  "Patch a Kubernetes namespace."
  [api id data]
  (debug "Patching kubernetes namespace" api id data)
  (-> (.patchNamespace api id data)
    (.then k8s-response)
    (.catch k8s-error)))

(defn namespace [& [opts]]
  (let [api (:api opts)])
  (debug "Initializing kubernetes namespace" api id
    (reify
      Object
      (find [this params]
        (namespace/list-namespace api))
      (get [this id params]
        (namespace/read-namespace api id))
      (create [this data params]
        (namespace/create-namespace api data))
      (update [this id data params]
        (namespace/replace-namespace api id data))
      (patch [this id data params]
        (namespace/patch-namespace api id data))
      (remove [this id params]
        (namespace/delete-namespace api id)))))
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
