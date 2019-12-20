(ns degree9.pathway
  (:require [clojure.spec.alpha :as spec]
            [clojure.string :as str]
            [degree9.debug :as dbg]
            [degree9.pathway.spec :as pspec]))

(dbg/defdebug debug "degree9:enterprise:pathway")

(defprotocol IPathway
  "A protocol for pathway matching."
  (-match-pathway [router pattern] "Returns a handler if pattern matches router."))

(extend-protocol IPathway
  nil
  (-match-pathway [router pattern]
    (debug "nil" router pattern)
    (reduced nil))
  string
  (-match-pathway [router pattern]
    (debug "string" router pattern)
    (= router pattern))
  Keyword
  (-match-pathway [router pattern]
    (debug "Keyword" router pattern)
    (= router (keyword pattern)))
  js/RegExp
  (-match-pathway [router pattern]
    (debug "RegExp" router pattern)
    (re-matches router pattern))
  PersistentHashMap
  (-match-pathway [router pattern]
    (debug "PersistentHashMap" router pattern)
    (reduce-kv (fn [_ r h] (when (-match-pathway r pattern) h)) nil router))
  PersistentArrayMap
  (-match-pathway [router pattern]
    (debug "PersistentArrayMap" router pattern)
    (reduce-kv (fn [_ r h] (when (-match-pathway r pattern) h)) nil router))
  PersistentHashSet
  (-match-pathway [router pattern]
    (debug "PersistentHashSet" router pattern)
    (reduce (fn [_ r] (-match-pathway r pattern)) nil router))
  PersistentVector
  (-match-pathway [router pattern]
    (debug "PersistentVector" router pattern)
    (reduce (fn [_ [r h]] (when (-match-pathway r pattern) h)) nil router)))

(defn match-route
  ([router pattern] (match-route router pattern nil))
  ([router pattern default] (match-route router pattern default #"/"))
  ([router pattern default separator]
   {:pre [(spec/valid? ::pspec/pathway router)]}
   (let [patterns (remove empty? (str/split pattern separator))]
     (debug "MATCH-ROUTE" router patterns)
     (if (empty? patterns) default
       (let [handler (reduce -match-pathway router patterns)]
         (when (or (string? handler) (keyword? handler)) handler))))))
