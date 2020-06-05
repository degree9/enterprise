(ns degree9.object
  (:refer-clojure :exclude [set get get-in filter remove])
  (:require [goog.object :as obj]))

;; JS Object Public Functions ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn get [m k & [default]]
  (obj/get m (name k) default))

(defn get-in [m ks & [default]]
  (let [getValueByKeys (partial obj/getValueByKeys m)]
    (or (apply getValueByKeys (map name ks)) default)))

(defn set [m k v]
  (obj/set m (name k) v))

(defn set-in [m [k & ks] v]
  (when-let [m (get m k)]
    (if (empty? ks) (set m k v) (set-in m ks v))))

(defn filter [pred m]
  (obj/filter m pred))

(defn remove [pred m]
  (obj/filter m (comp pred not)))
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; JS Object Protocols ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(extend-type object
  ILookup
  (-lookup
   ([o k] (get o k))
   ([o k default] (get o k default))))
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
