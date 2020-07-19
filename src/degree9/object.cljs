(ns degree9.object
  (:refer-clojure :exclude [set get get-in filter remove merge dissoc reduce-kv])
  (:require [goog.object :as obj]))

;; JS Object Public Functions ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn get [o k & [default]]
  (obj/get o (name k) default))

(defn get-in [o ks & [default]]
  (let [getValueByKeys (partial obj/getValueByKeys o)]
    (or (apply getValueByKeys (map name ks)) default)))

(defn set [o k v]
  (doto o (obj/set (name k) (clj->js v))))

(defn set-in [o [k & ks] v]
  (set o k (if (empty? ks) (clj->js v) (set-in (get o k #js{}) ks v))))

(defn filter [pred o]
  (obj/filter o pred))

(defn remove [pred o]
  (obj/filter o (comp pred not)))

(defn merge [& objs]
  (clj->js (reduce clojure.core/merge (map js->clj objs))))

(defn dissoc [o & ks]
  (reduce (fn [i k] (doto i (obj/remove (name k)))) o ks))

(defn reduce-kv [f i o]
  (clj->js (reduce-kv f i (js->clj o))))
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; JS Object Protocols ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(extend-type object
  ILookup
  (-lookup
   ([o k] (get o k))
   ([o k default] (get o k default))))
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
