(ns degree9.kubernetes.deployment
  (:require [degree9.debug :as dbg]))

(dbg/defdebug debug "degree9:enterprise:kubernetes:deployment")

;; Kubernetes Deployment ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn- list-deployment
  "List all Kubernetes deployments from a Kubernetes namespace ."
  [api namespace]
  (debug "Listing all Kubernetes deployments from namespace" api namespace)
  (.listNamespacedDeployment api namespace))

(defn- read-deployment
  "Read a Deployment from a Kubernetes namespace."
  [api name namespace]
  (debug "Reading all Kubernetes deployments from namespace" api name namespace)
  (.readNamespacedDeployment api name namespace))

(defn- create-deployment
  "Create a Kubernetes deployment within a Kubernetes namespace."
  [api data namespace]
  (debug "Creating all Kubernetes deployments from namespace" api data namespace)
  (.createNamespacedDeployment api namespace data))

(defn- replace-deployment
  "Replace a Kubernetes deployment."
  [api id namespace data]
  (debug "Replacing all Kubernetes deployments from namespace" api id namespace data)
  (.replaceNamespacedDeployment api id namespace data))

(defn- patch-deployment
  "Patch a Kubernetes deployment."
  [api id namespace data]
  (debug "Patching all Kubernetes deployments from namespace" api id namespace data)
  (.patchNamespacedDeployment api id namespace data))

(defn- delete-deployment
  "Delete a Kubernetes deployment."
  [api id namespace]
  (debug "Deleting all Kubernetes deployments from namespace" api id namespace)
  (.deleteNamespacedDeployment api id namespace))
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
