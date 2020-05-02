(ns degree9.fullcalendar.plugins
  (:refer-clojure :exclude [list])
  (:require [goog.object :as obj]
            ["@fullcalendar/daygrid" :as fcal-daygrid]
            ["@fullcalendar/timegrid" :as fcal-timegrid]
            ["@fullcalendar/list" :as fcal-list]))

(def daygrid fcal-daygrid)

(def timegrid fcal-timegrid)

(def list fcal-list)

(prn daygrid)

(prn timegrid)

(prn list)
