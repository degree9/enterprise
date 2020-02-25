(ns degree9.kubernetes.custom-resource
  (:require [degree9.debug :as dbg]))

(dbg/defdebug debug "degree9:enterprise:kubernetes:custom-resource")
;; Kubernetes Cluster Custom Resource ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn- list-clustercustomresource
  "List a Kubernetes custom resource."
  [api group version plural]
  (debug "Listing kubernetes custom resource" api group version plural)
  (.listClusterCustomObject api group version plural))

(defn- get-clustercustomresource
  "Get a Kubernetes custom resource."
  [api group version plural id]
  (debug "Getting kubernetes custom resource" api group version plural id)
  (.getClusterCustomObject api group version plural id))

(defn- create-clustercustomresource
  "Create a Kubernetes custom resource."
  [api group version plural body]
  (debug "Creating kubernetes custom resource" api group version plural body)
  (.createClusterCustomObject api group version plural (clj->js body)))

(defn- replace-clustercustomresource
  "Replace a Kubernetes custom resource."
  [api group version plural id data]
  (debug "Replacing kubernetes custom resource" api group version plural id data)
  (.replaceClusterCustomObject api group version plural id (clj->js data)))

(defn- patch-clustercustomresource
  "Patch a Kubernetes custom resource."
  [api group version plural id data]
  (debug "Patching kubernetes custom resource" api group version plural id data)
  (.patchClusterCustomObject api group version plural id (clj->js data)))

(defn- delete-clustercustomresource
  "Delete a Kubernetes custom resource."
  [api group version plural id opts]
  (debug "Deleting kubernetes custom resource" api group version plural id opts)
  (.deleteClusterCustomObject api group version plural id opts))
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; Kubernetes Namespaced Custom Resource ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn- list-namespacedcustomresource
  "List a Kubernetes custom resource."
  [api group version plural]
  (debug "Listing custom resource" api group version plural)
  (.listNamespacedCustomObject api group version plural))

(defn- get-namespacedcustomresource
  "Get a Kubernetes custom resource."
  [api group version plural id]
  (debug "Getting kubernetes custom resource" api group version plural id)
  (.getNamespacedCustomObject api group version plural id))

(defn- create-namespacedcustomresource
  "Create a Kubernetes custom resource."
  [api group version plural body]
  (debug "Creating kubernetes custom resource" api group version plural body)
  (.createNamespacedCustomObject api group version plural (clj->js body)))

(defn- replace-namespacedcustomresource
  "Replace a Kubernetes custom resource."
  [api group version plural id data]
  (debug "Replacing kubernetes custom resource" api group version plural id data)
  (.replaceNamespacedCustomObject api group version plural id (clj->js data)))

(defn- patch-namespacedcustomresource
  "Patch a Kubernetes custom resource."
  [api group version plural id data]
  (debug "Creating kubernetes namespace" api group version plural id data)
  (.patchNamespacedCustomObject api group version plural id (clj->js data)))

(defn- delete-namespacedcustomresource
  "Delete a Kubernetes custom resource."
  [api group version plural id opts]
  (debug "Deleting kubernetes namespace" api group version plural id opts)
  (.deleteNamespacedCustomObject api group version plural id opts))
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
