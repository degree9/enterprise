(ns degree9.pathway
  (:require [clojure.spec.alpha :as spec]
            [degree9.string :as str]
            [degree9.debug :as dbg]
            [degree9.pathway.spec :as pspec]))

(dbg/defdebug debug "degree9:enterprise:pathway")



(defprotocol IPathway
  "A protocol for pathway matching."

  (compare-route   [route  pattern] "Returns true if route matches pattern.")
  (compare-pattern [router pattern] "Returns handler or nested router if router contains pattern.")

  (-match-pathway [router pattern] "Returns a handler if pattern matches router."))



(extend-protocol IPathway
  nil
  (-match-pathway [router pattern]
    (debug "nil" router pattern)
    (if (= router pattern) true
      (reduced nil)))
  string
  (-match-pathway [router pattern]
    (debug "string" router pattern)
    (prn router pattern (= router pattern))
    (= router pattern))
  Keyword
  (compare-pattern [route pattern] (reduced route))
  (-match-pathway [router pattern]
    (debug "Keyword" (clj->js router) (clj->js pattern))
    (= router (keyword pattern)))
  js/RegExp
  (-match-pathway [router pattern]
    (debug "RegExp" (clj->js router) (clj->js pattern))
    (re-matches router pattern))
  PersistentHashSet
  (-match-pathway [router pattern]
    (debug "PersistentHashSet" (clj->js router) (clj->js pattern))
    (reduce (fn [_ r] (-match-pathway r pattern)) nil router))
  PersistentVector
  (-match-pathway [router pattern]
    (debug "PersistentVector" (clj->js router) (clj->js pattern))
    (reduce (fn [i [r h]] (if (-match-pathway r pattern) h i)) nil router))
  PersistentHashMap
  (-match-pathway [router pattern]
    (debug "PersistentHashMap" (clj->js router) (clj->js pattern))
    (reduce-kv (fn [i r h] (if (-match-pathway r pattern) h i)) nil router))
  PersistentArrayMap
  (match-pattern [router pattern]
    (let [result (get router pattern)])
    (reduce-kv (fn [m r h] (or (compare-route r h pattern) m)) nil router))

  (-match-pathway [router pattern]
    (debug "PersistentArrayMap" (clj->js router) (clj->js pattern))
    (reduce-kv (fn [i r h] (if (-match-pathway r pattern) (reduced h) i)) nil router)))



(defn match-route
  ([router path] (match-route router path nil))
  ([router path default] (match-route router path default #"/"))
  ([router path default separator]
   {:pre [(spec/valid? ::pspec/pathway router)]}
   (let [patterns (remove empty? (str/split path separator))]
     (debug "MATCH-ROUTE" (clj->js router) (clj->js patterns))
     (if (empty? patterns) default
       (let [handler (reduce prn router patterns)]
         (prn handler)
         (if (or (string? handler) (keyword? handler)) handler
           (when-let [default (get handler nil)]
             default)))))))
