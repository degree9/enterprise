(ns degree9.timekit.auth_endpoints
  (:require [degree9.timekit.core :as tk]
            [degree9.env :as env]
            [degree9.debug :as dbg]))


(dbg/defdebug debug "degree9:timekit:auth_endpoints")

(defn auth [client & args]
  (.auth client args))

(defn auth [& [opts]]
  (let [conf (merge {:key (env/get "TIMEKIT_API_KEY")} opts)
        timekit (tk/configure conf)]
    (debug "" timekit)
    (reify
      Object
      (get [this id & params]
          (auth timekit)))))
