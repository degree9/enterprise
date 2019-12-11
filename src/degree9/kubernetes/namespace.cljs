(ns degree9.kubernetes.namespace
  (:refer-clojure :exclude [namespace])
  (:require [degree9.debug :as dbg]))

(def ^:private debug (dbg "degree9:enterprise:kubernetes:namespace"))

;; Kubernetes Namespace ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn- list-namespace
  "List a Kubernetes namespace."
  [api]
  (debug "List kubernetes namespace" api)
  (-> api
    (.listNamespace)
    (.then k8s-response)
    (.catch k8s-error)))

(defn- create-namespace
  "Create a Kubernetes namespace."
  [api data]
  (debug "create kubernetes namespace" api data)
  (-> api
    (.createNamespace data)
    (.then k8s-response)
    (.catch k8s-error)))

(defn- read-namespace
  "Read a Kubernetes namespace."
  [api name]
  (debug "Read kubernetes namespace" api name)
  (-> api
    (.readNamespace name)
    (.then k8s-response)
    (.catch k8s-error)))

(defn- delete-namespace
  "Delete a Kubernetes namespace."
  [api id]
  (debug "Delete kubernetes namespace" api id)
  (-> api
    (.deleteNamespace id)
    (.then k8s-response)
    (.catch k8s-error)))

(defn- replace-namespace
  "Replace a Kubernetes namespace."
  [api id data]
  (debug "Replace kubernetes namespace" api id data)
  (-> api
    (.replaceNamespace id data)
    (.then k8s-response)
    (.catch k8s-error)))

(defn- patch-namespace
  "Patch a Kubernetes namespace."
  [api id data]
  (debug "Patch kubernetes namespace" api id data)
  (-> api
    (.patchNamespace id data)
    (.then k8s-response)
    (.catch k8s-error)))

(defn namespace [& [opts]]
  (let [api (:api opts)])
  (debug "Initialize kubernetes namespace" api id
    (reify
      Object
      (find [this params]
        (list-namespace api))
      (get [this id params]
        (read-namespace api id))
      (create [this data params]
        (create-namespace api data))
      (update [this id data params]
        (replace-namespace api id data))
      (patch [this id data params]
        (patch-namespace api id data))
      (remove [this id params]
        (delete-namespace api id)))))
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
