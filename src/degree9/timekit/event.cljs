(ns degree9.timekit.event
  (:require [degree9.timekit.core :as tk]))


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
