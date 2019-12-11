(ns degree9.kubernetes.custom-resource
  (:require [degree9.debug :as dbg]))

(def ^:private debug (dbg "degree9:enterprise:kubernetes:custom-resource"))
;; Kubernetes Cluster Custom Resource ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn- list-clustercustomresource
  "List a Kubernetes custom resource."
  [api group version plural]
  (debug "Listing kubernetes custom resource" api group version plural)
  (-> api
    (.listClusterCustomObject group version plural)
    (.then k8s-response)
    (.catch k8s-error)))

(defn- get-clustercustomresource
  "Get a Kubernetes custom resource."
  [api group version plural id]
  (debug "Getting kubernetes custom resource" api group version plural id)
  (-> api
    (.getClusterCustomObject group version plural id)
    (.then k8s-response)
    (.catch k8s-error)))

(defn- create-clustercustomresource
  "Create a Kubernetes custom resource."
  [api group version plural body]
  (debug "Creating kubernetes custom resource" api group version plural body)
  (-> api
    (.createClusterCustomObject group version plural (clj->js body))
    (.then k8s-response)
    (.catch k8s-error)))

(defn- replace-clustercustomresource
  "Replace a Kubernetes custom resource."
  [api group version plural id data]
  (debug "Replacing kubernetes custom resource" api group version plural id data)
  (-> api
    (.replaceClusterCustomObject group version plural id (clj->js data))
    (.then k8s-response)
    (.catch k8s-error)))

(defn- patch-clustercustomresource
  "Patch a Kubernetes custom resource."
  [api group version plural id data]
  (debug "Patching kubernetes custom resource" api group version plural id data)
  (-> api
    (.patchClusterCustomObject group version plural id (clj->js data))
    (.then k8s-response)
    (.catch k8s-error)))

(defn- delete-clustercustomresource
  "Delete a Kubernetes custom resource."
  [api group version plural id opts]
  (debug "Deleting kubernetes custom resource" api group version plural id opts)
  (-> api
    (.deleteClusterCustomObject group version plural id opts)
    (.then k8s-response)
    (.catch k8s-error)))

(defn cluster-custom-resource [& [opts]]
  (let [api        (:api opts)
        kind       (:kind opts)
        group      (:group opts)
        apiversion (:apiVersion opts "v1")
        plural     (:plural opts (s/lower-case (str kind "s")))]
    (debug "Initializing cluster custom resource:" api kind group apiversion plural)
    (reify
      Object
      (setup [this app]
        (obj/set this "kind" kind)
        (obj/set this "group" group)
        (obj/set this "apiVersion" apiversion)
        (obj/set this "plural" plural))
      (find [this params]
        (list-clustercustomresource api group apiversion plural))
      (get [this id params]
        (get-clustercustomresource api group apiversion plural id))
      (create [this data params]
        (create-clustercustomresource api group apiversion plural data))
      (update [this id data params]
        (replace-clustercustomresource api group apiversion plural id data))
      (patch [this id data params]
        (patch-clustercustomresource api group apiversion plural id data))
      (remove [this id params]
        (delete-clustercustomresource api group apiversion plural id (obj/get params "query"))))))
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; Kubernetes Namespaced Custom Resource ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn- list-namespacedcustomresource
  "List a Kubernetes custom resource."
  [api group version plural]
  (debug "Listing custom resource" api group version plural)
  (-> api
    (.listNamespacedCustomObject group version plural)
    (.then k8s-response)
    (.catch k8s-error)))

(defn- get-namespacedcustomresource
  "Get a Kubernetes custom resource."
  [api group version plural id]
  (debug "Getting kubernetes custom resource" api group version plural id)
  (-> api
    (.getNamespacedCustomObject group version plural id)
    (.then k8s-response)
    (.catch k8s-error)))

(defn- create-namespacedcustomresource
  "Create a Kubernetes custom resource."
  [api group version plural body]
  (debug "Creating kubernetes custom resource" api group version plural body)
  (-> api
    (.createNamespacedCustomObject group version plural (clj->js body))
    (.then k8s-response)
    (.catch k8s-error)))

(defn- replace-namespacedcustomresource
  "Replace a Kubernetes custom resource."
  [api group version plural id data]
  (debug "Replacing kubernetes custom resource" api group version plural id data)
  (-> api
    (.replaceNamespacedCustomObject group version plural id (clj->js data))
    (.then k8s-response)
    (.catch k8s-error)))

(defn- patch-namespacedcustomresource
  "Patch a Kubernetes custom resource."
  [api group version plural id data]
  (debug "Creating kubernetes namespace" api group version plural id data)
  (-> api
    (.patchNamespacedCustomObject group version plural id (clj->js data))
    (.then k8s-response)
    (.catch k8s-error)))

(defn- delete-namespacedcustomresource
  "Delete a Kubernetes custom resource."
  [api group version plural id opts]
  (debug "Deleting kubernetes namespace" api group version plural id opts)
  (-> api
    (.deleteNamespacedCustomObject group version plural id opts)
    (.then k8s-response)
    (.catch k8s-error)))

(defn namespaced-custom-resource [& [opts]]
  (let [api        (:api opts)
        kind       (:kind opts)
        group      (:group opts)
        apiversion (:apiVersion opts "v1")
        plural     (:plural opts (s/lower-case (str kind "s")))]
    (debug "Initializing kubernetes custom resource" api kind group api version plural)
    (reify
      Object
      (setup [this app]
        (obj/set this "kind" kind)
        (obj/set this "group" group)
        (obj/set this "apiVersion" apiversion)
        (obj/set this "plural" plural))
      (find [this params]
        (list-namespacedcustomresource api group apiversion plural))
      (get [this id params]
        (get-namespacedcustomresource api group apiversion plural id))
      (create [this data params]
        (create-namespacedcustomresource api group apiversion plural data))
      (update [this id data params]
        (replace-namespacedcustomresource api group apiversion plural id data))
      (patch [this id data params]
        (patch-namespacedcustomresource api group apiversion plural id data))
      (remove [this id params]
        (delete-namespacedcustomresource api group apiversion plural id (obj/get params "query"))))))
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
