(ns degree9.object
  (:refer-clojure :exclude [set get get-in filter remove merge dissoc])
  (:require [goog.object :as obj]))

;; JS Object Public Functions ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn get [m k & [default]]
  (obj/get m (name k) default))

(defn get-in [m ks & [default]]
  (let [getValueByKeys (partial obj/getValueByKeys m)]
    (or (apply getValueByKeys (map name ks)) default)))

(defn set [m k v]
  (doto m (obj/set (name k) v)))

(defn set-in [m [k & ks] v]
  (set m k (if (empty? ks) v (set-in (get m k #js{}) ks v))))

(defn filter [pred m]
  (obj/filter m pred))

(defn remove [pred m]
  (obj/filter m (comp pred not)))

(defn merge [& objs]
  (clj->js (reduce clojure.core/merge (map js->clj objs))))

(defn dissoc [m & ks]
  (reduce (fn [i k] (doto i (obj/remove (name k)))) m ks))
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; JS Object Protocols ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(extend-type object
  ILookup
  (-lookup
   ([o k] (get o k))
   ([o k default] (get o k default))))
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
