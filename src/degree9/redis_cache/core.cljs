(ns degree9.redis_cache.core
  (:require
   ["@kubernetes/client-node" :as redis]
   [goog.object :as obj]
   [clojure.string :as s]
   [feathers.errors :as error]
   [degree9.env :as env]
   [degree9.debug :as dbg]))

(dbg/defdebug debug "degree9:enterprise:redis_cache:core")

;; Cache for redis Helpers ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn- redis->clj
  "Converts Kubernetes response to ClojureScript."
  [redis]
  (debug "Converting kubernetes response to cljs" redis)
  (js->clj redis :keywordize-keys true))

(defn- redis-response [res]
  (obj/get res "body"))

(defn redis-error
  "Converts Kubernetes error response to FeathersJS error object."
  [err]
  (let [{:keys [message data code]} (k8s->clj (k8s-response err))]
    (throw
      (case code
        404 (error/not-found message data)
        409 (error/conflict message data)
        500 (error/general-error message data)
        (error/general-error message data)))))
