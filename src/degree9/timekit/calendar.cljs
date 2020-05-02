(ns degree9.timekit.calendar
  (:require [degree9.timekit.core :as tk]
            [degree9.debug :as dbg]))

(dbg/defdebug debug "degree9:timekit:calendar")

(defn get-calendars [client]
  (.getCalendars client))

(defn get-calendar [client id]
  (.getCalendar client id))

(defn create-calendar [client & args]
  (.createCalendar client args))

(defn update-calendar [client id & args]
  (.updateCalendar client id args))

(defn delete-calendar [client id]
  (.deleteCalendar client id))

(defn calendar [& [opts]]
    (let [conf (merge {:key (env/get "TIMEKIT_API_KEY")} opts)
          timekit (tk/configure conf)])
    (debug "" timekit)
    (reify
      Object
      (find [this & [params]]
          (get-calendars timekit))
      (get [this id & [params]]
          (get-calendar timekit id))
      (create [this data & [params]]
          (create-calendar timekit))
      (update [this id data params]
          (update-calendar timekit id))
      (remove [this id params]
          (delete-calendar timekit id))))
