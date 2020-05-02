(ns degree9.timekit.apps
  (:require [degree9.timekit.core :as tk]
            [degree9.env :as env]
            [degree9.debug :as dbg]))

(dbg/defdebug debug "degree9:timekit:apps")




(defn get-app [client]
  (.getApp client))

(defn app [& [opts]]
  (let [conf (merge {:key (env/get "TIMEKIT_API_KEY")} opts)
        timekit (tk/configure conf)]
    (debug "" timekit)
    (reify
      Object
      (get [this id params]
          (get-app timekit)))))
