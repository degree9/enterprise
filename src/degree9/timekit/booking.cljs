(ns degree9.timekit.booking
  (:require [degree9.timekit.core :as tk]))


(defn get-bookings [client]
  (.getbookings client))

(defn get-booking [client id]
  (.getbooking client id))

(defn create-booking [client & args]
  (.createBooking client args))

(defn create-booking-bulk [client & args]
  (.createBookingsBulk client args))

(defn update-booking [client id action & args]
  (.updateBooking client id action args))

(defn update-booking [client & args]
  (.updateBookingsBulk client args))

(defn delete-booking [client id]
  (.deleteBooking client id))

(defn get-group-bookings [client]
  (.getGroupBookings client))

(defn get-group-booking [client id]
  (.getGroupBooking client id))
