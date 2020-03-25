(ns degree9.kubernetes.ingress
  (:require [degree9.debug :as dbg]
            [degree9.kubernetes.core :as k8s]))

(dbg/defdebug debug "degree9:enterprise:kubernetes:ingress")

;; Kubernetes Ingress ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn- list-ingress
  "List all Kubernetes ingress from a Kubernetes namespace."
  [api namespace]
  (debug "Listing all Kubernetes ingress from Kubernetes namespace" api namespace)
  (-> (.listNamespacedIngress api namespace)
    (.then k8s/k8s-response)
    (.catch k8s/k8s-error)))

(defn- create-ingress
  "Create a Kubernetes ingress."
  [api namespace data]
  (debug "Creating all Kubernetes ingress from Kubernetes namespace" api namespace data)
  (-> (.createNamespacedIngress api namespace data)
    (.then k8s/k8s-response)
    (.catch k8s/k8s-error)))

(defn- read-ingress
  "Read a Kubernetes ingress."
  [api name namespace]
  (debug "Reading all Kubernetes ingress from Kubernetes namespace" api name namespace)
  (-> (.readNamespacedIngress api name)
    (.then k8s/k8s-response)
    (.catch k8s/k8s-error)))

(defn- replace-ingress
  "Replace a Kubernetes ingress."
  [api id namespace data]
  (debug "Replacing all Kubernetes ingress from Kubernetes namespace" api id namespace data)
  (-> (.replaceNamespacedIngress api id namespace data)
    (.then k8s/k8s-response)
    (.catch k8s/k8s-error)))

(defn- patch-ingress
  "Patch a Kubernetes ingress."
  [api id namespace data]
  (debug "Patching all Kubernetes ingress from Kubernetes namespace" api id namespace data)
  (-> (.patchNamespacedIngress api id namespace data)
    (.then k8s/k8s-response)
    (.catch k8s/k8s-error)))

(defn- delete-ingress
  "Delete a Kubernetes ingress."
  [api id namespace]
  (debug "Deleting all Kubernetes ingress from Kubernetes namespace" api id namespace)
  (-> (.deleteNamespacedIngress api id namespace)
    (.then k8s/k8s-response)
    (.catch k8s/k8s-error)))

(defn ingress [& [opts]]
  (let [api (:api opts)]
    (debug "Initializing all Kubernetes ingress from Kubernetes namespace" api)
    (reify
      Object
      (find [this params]
        (let [namespace (get-in (js->clj params) ["query" "namespace"])]
          (list-ingress api namespace)))
      (get [this id & [params]]
        (let [namespace (get-in (js->clj params) ["query" "namespace"])]
          (read-ingress api id namespace)))
      (create [this data & [params]]
        (let [namespace (get-in (js->clj params) ["query" "namespace"])]
          (create-ingress api namespace data)))
      (update [this id data params]
        (let [namespace (get-in (js->clj params) ["query" "namespace"])]
          (replace-ingress api id namespace data)))
      (patch [this id data params]
        (let [namespace (get-in (js->clj params) ["query" "namespace"])]
          (patch-ingress api id namespace data)))
      (remove [this id params]
        (let [namespace (get-in (js->clj params) ["query" "namespace"])]
          (delete-ingress api id namespace))))))
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
