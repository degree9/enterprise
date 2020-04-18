(ns degree9.timekit.booking
  (:require [degree9.timekit.core :as tk]
            [degree9.debug :as dbg]))

(dbg/defdebug debug "degree9:timekit:booking")


(defn get-bookings [client]
  (.getbookings client))

(defn get-booking [client id]
  (.getbooking client id))

(defn create-booking [client & args]
  (.createBooking client args))

(defn update-booking [client id action & args]
  (.updateBooking client id action args))

(defn delete-booking [client id]
  (.deleteBooking client id))

(defn booking [& [opts]]
  (let [conf (merge {:key (env/get "TIMEKIT_API_KEY")} opts)
        timekit (tk/configure conf)]
    (debug "" timekit)
    (reify
      Object
      (find [this & [params]]
          (get-bookings timekit))
      (get [this id & [params]]
          (get-booking timekit id))
      (create [this data & [params]]
          (create-booking timekit data))
      (update [this id data params]
          (update-booking timekit id action data))
      (remove [this id params]
          (delete-booking timekit id)))))


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn create-booking-bulk [client & args]
  (.createBookingsBulk client args))

(defn update-booking-bulk [client & args]
  (.updateBookingsBulk client args))

(defn booking-bulk [& [opts]]
    (let [conf (merge {:key (env/get "TIMEKIT_API_KEY")} opts)
          timekit (tk/configure conf)])
    (debug "" timekit)
    (reify
      Object
      (create [this data & [params]]
          (create-booking-bulk api data))
      (update [this id data params]
          (update-booking-bulk timekit data))))


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn get-group-bookings [client]
  (.getGroupBookings client))

(defn get-group-booking [client id]
  (.getGroupBooking client id))

(defn group-booking [& [opts]]
    (let [conf (merge {:key (env/get "TIMEKIT_API_KEY")} opts)
          timekit (tk/configure conf)])
    (debug "" timekit)
    (reify
      Object
      (find [this [params]]
          (create-group-booking timekit))
      (get [this id params]
          (update-group-booking timekit id))))
