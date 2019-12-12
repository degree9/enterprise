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
  PersistentVector
  (-match-pathway [[route handler] pattern]
    (when (-match-pathway route pattern) (reduced handler))))

(defn match-route
  ([router pattern] (match-route router pattern #"/"))
  ([router pattern separator]
   {:pre [(spec/valid? ::pspec/pathway router)]}
   (let [patterns (str/split pattern separator)]
     (reduce (fn [r p] (-match-pathway r p)) router patterns))))
