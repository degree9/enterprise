(ns degree9.timekit.calendar
  (:require [degree9.timekit.core :as tk]))


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
  (let [client (:client opts)]
    (debug "" client)
    (reify
      Object
      (find [this & [params]]
          (get-calendars client))
      (get [this id & [params]]
          (get-calendar client id))
      (create [this data & [params]]
          (create-calendar client))
      (update [this id data params]
          (update-calendar client id))
      (remove [this id params]
          (delete-calendar client id)))))
