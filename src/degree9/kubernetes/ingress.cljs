(ns degree9.kubernetes.ingress
  (:require [degree9.debug :as dbg]))

(def ^:private debug (dbg "degree9:enterprise:kubernetes:ingress"))
;; Kubernetes Ingress ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn- list-ingress
  "List all Kubernetes ingress from a Kubernetes namespace."
  [api namespace]
  (debug "List all Kubernetes ingress from Kubernetes namespace" api namespace)
  (-> api
    (.listNamespacedIngress namespace)
    (.then k8s-response)
    (.catch k8s-error)))

(defn- create-ingress
  "Create a Kubernetes ingress."
  [api namespace data]
  (debug "Create all Kubernetes ingress from Kubernetes namespace" api namespace data)
  (-> api
    (.createNamespacedIngress namespace data)
    (.then k8s-response)
    (.catch k8s-error)))

(defn- read-ingress
  "Read a Kubernetes ingress."
  [api name namespace]
  (debug "Read all Kubernetes ingress from Kubernetes namespace" api name namespace)
  (-> api
    (.readNamespacedIngress name)
    (.then k8s-response)
    (.catch k8s-error)))

(defn- replace-ingress
  "Replace a Kubernetes ingress."
  [api id namespace data]
  (debug "Replace all Kubernetes ingress from Kubernetes namespace" api id namespace data)
  (-> api
    (.replaceNamespacedIngress id namespace data)
    (.then k8s-response)
    (.catch k8s-error)))

(defn- patch-ingress
  "Patch a Kubernetes ingress."
  [api id namespace data]
  (debug "Patch all Kubernetes ingress from Kubernetes namespace" api id namespace data)
  (-> api
    (.patchNamespacedIngress id namespace data)
    (.then k8s-response)
    (.catch k8s-error)))

(defn- delete-ingress
  "Delete a Kubernetes ingress."
  [api id namespace]
  (debug "Delete all Kubernetes ingress from Kubernetes namespace" api id namespace)
  (-> api
    (.deleteNamespacedIngress id namespace)
    (.then k8s-response)
    (.catch k8s-error)))

(defn ingress [& [opts]]
  (let [api (:api opts)])
  (debug "Initialize all Kubernetes ingress from Kubernetes namespace" api
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
