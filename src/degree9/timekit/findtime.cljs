(ns degree9.timekit.findtime
  (:require [degree9.timekit.core :as tk]))



(defn find-time [client & args]
  (.findTime client args))

(defn find-time-bulk [client & args]
  (.findTimeBulk client args))

(defn find-time-team [client & args]
  (.findTimeTeam client args))
