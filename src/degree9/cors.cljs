(ns degree9.cors
  (:require ["cors" :as cors]
            [meta.server :as svr]
            [degree9.debug :as dbg]))

(dbg/defdebug debug "degree9:enterprise:cors")

(defn cors-service [app path cors service & [hooks]]
  (let [app (.use app path cors service)]
    (doto (.service app path)
      (.hooks (clj->js hooks)))
    app))

(defn cors-all [app path service & [hooks]]
  (cors-service app path (cors) service hooks))

(defn with-cors [app & [opts]]
  (.use app (cors (clj->js opts))))
