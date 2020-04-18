(ns degree9.timekit.event
  (:require [degree9.timekit.core :as tk]
            [degree9.debug :as dbg]))

(dbg/defdebug debug "degree9:timekit:event")

(defn get-events [client & args]
  (.getEvents client args))

(defn get-event [client id]
  (.getEvent client id))

(defn create-event [client & args]
  (.createEvent client args))

(defn update-event [client id & args]
  (.updateEvent client id args))

(defn delete-event [client id]
  (.deleteEvent client id))

(defn event [& [opts]]
    (let [conf (merge {:key (env/get "TIMEKIT_API_KEY")} opts)
          timekit (tk/configure conf)])
    (debug "" timekit)
    (reify
      Object
      (find [this & [params]]
          (get-events timekit))
      (get [this id & [params]]
          (get-event timekit id))
      (create [this data & [params]]
          (create-event timekit))
      (update [this id data params]
          (update-event timekit id))
      (remove [this id params]
          (delete-event timekit id))))
