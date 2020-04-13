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
