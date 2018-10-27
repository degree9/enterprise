(ns degree9.debug
  (:require ["debug" :as dbg])
  (:require-macros [degree9.debug]))

(defn debug [name]
  (dbg name))

(defn extend [dbg name]
  (.extend dbg name))
