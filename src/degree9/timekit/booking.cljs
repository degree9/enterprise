(ns degree9.timekit.booking
  (:require [degree9.timekit.core :as tk]))


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
  (let [client (:client opts)]
    (debug "" client)
    (reify
      Object
      (find [this & [params]]
          (get-bookings client))
      (get [this id & [params]]
          (get-booking client id))
      (create [this data & [params]]
          (create-booking client data))
      (update [this id data params]
          (update-booking client id action data))
      (remove [this id params]
          (delete-booking client id)))))


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn create-booking-bulk [client & args]
  (.createBookingsBulk client args))

(defn update-booking-bulk [client & args]
  (.updateBookingsBulk client args))

(defn booking-bulk [& [opts]]
  (let [client (:client opts)]
    (debug "" client)
    (reify
      Object
      (create [this data & [params]]
          (create-booking-bulk api data))
      (update [this id data params]
          (update-booking-bulk client data)))))


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn get-group-bookings [client]
  (.getGroupBookings client))

(defn get-group-booking [client id]
  (.getGroupBooking client id))

(defn group-booking [& [opts]]
  (let [client (:client opts)]
    (debug "" client)
    (reify
      Object
      (find [this [params]]
          (create-group-booking client))
      (get [this id params]
          (update-group-booking client id)))))
