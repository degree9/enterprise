(ns degree9.timekit.availability
  (:require [degree9.timekit.core :as tk]
            [degree9.debug :as dbg]))

(dbg/defdebug debug "degree9:timekit:availability")


(defn fetch-availability [client & args]
  (.fetchAvailability client args))

(defn fetch-availability [& [opts]]
  (let [conf (merge {:key (env/get "TIMEKIT_API_KEY")} opts)
        timekit (tk/configure conf)]
    (debug "" timekit)
    (reify
      Object
      (find [this & [params]]
          (get-bookings timekit)))))
