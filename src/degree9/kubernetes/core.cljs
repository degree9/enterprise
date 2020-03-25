(ns degree9.kubernetes.core
  (:require
   ["@kubernetes/client-node" :as k8s]
   [goog.object :as obj]
   [clojure.string :as s]
   [feathers.errors :as error]
   [degree9.env :as env]
   [degree9.debug :as dbg]))

(dbg/defdebug debug "degree9:enterprise:kubernetes:core")

;; Kubernetes Helpers ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn- k8s->clj
  "Converts Kubernetes response to ClojureScript."
  [k8s]
  (debug "Converting kubernetes response to cljs" k8s)
  (js->clj k8s :keywordize-keys true))

(defn- k8s-response [res]
  (obj/get res "body"))

(defn k8s-error
  "Converts Kubernetes error response to FeathersJS error object."
  [err]
  (let [{:keys [message data code]} (k8s->clj (k8s-response err))]
    (throw
      (case code
        404 (error/not-found message data)
        409 (error/conflict message data)
        500 (error/general-error message data)
        (error/general-error message data)))))
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
