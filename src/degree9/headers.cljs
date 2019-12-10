(ns degree9.headers
  (:require [goog.object :as obj]
            [degree9.debug :as dbg]))

(def ^:private debug (dbg "degree9:enterprise:headers"))

;; Feathers Headers ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn- req-headers [req]
  (obj/get req "headers"))

(defn- req-feathers [req]
  (obj/get req "feathers"))

(defn- fs-headers! [fs headers]
  (obj/set fs "headers" headers))

(defn- fs-headers [req res next]
  (fs-headers! (req-feathers req) (req-headers req))
  (next))

(defn feathers-headers [app]
  (doto app (.use fs-headers)))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
