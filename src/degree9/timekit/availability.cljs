(ns degree9.timekit.availability
  (:require [degree9.timekit.core :as tk]))


(defn fetch-availability [client & args]
  (.fetchAvailability client args))

(defn fetch-availability [& [opts]]
  (let [client (:client opts)]
    (debug "" client)
    (reify
      Object
      (find [this & [params]]
          (get-bookings client)))))
