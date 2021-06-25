(ns degree9.time
  (:refer-clojure :exclude [time])
  (:require ["proposal-temporal" :as t]))

(def temporal t/Temporal)

(def instant t/Temporal.Instant)

(def plaindate t/Temporal.PlainDate)

(def plaindatetime t/Temporal.PlainDateTime)

(defn now []
  (.instant temporal.now))

(defn to-instant [date]
  (.from instant date))

(defn to-plaindate [date]
  (.from plaindate date))

(defn to-plaindatetime [date]
  (.from plaindatetime date))
