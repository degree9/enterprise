(ns degree9.timekit.availability
  (:require [degree9.timekit.core :as tk]))


(defn fetch-availability [client & args]
  (.fetchAvailability client args))
