(ns degree9.date
  (:require [degree9.string :as dstr]))

;; In the future this should be replaced with js/Temporal as the internals

(defprotocol Date
  "A Date protocol."
  (year    [this] "Get year of the date object.")
  (month   [this] "Get month of the date object.")
  (day     [this] "Get day of the date object."))

(extend-protocol Date
  js/Date
  (year
    ([this] (.getFullYear this)))
  (month
    ([this] (-> (.getMonth this)
                (inc)
                (dstr/pad-start 2 "0"))))
  (day
    ([this] (-> (.getDate this)
                (dstr/pad-start 2 "0")))))

(defn today []
  (js/Date.))
