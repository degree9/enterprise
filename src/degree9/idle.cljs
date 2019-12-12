(ns degree9.idle
  (:require [javelin.core :as j]
            [degree9.debug :as dbg]))

(dbg/defdebug debug "degree9:enterprise:idle")

;; State Helpers ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(def ^:dynamic *default-events*
  ["click" "keydown" "scroll"
   "mousemove" "mouseenter" "mousewheel"
   "touchstart" "touchmove"])

(defn- time-now
  "Returns the current time in milliseconds."
  []
  (.getTime (js/Date.)))

(defn- time-diff
  "Returns true if there is a time difference greater than `timeout` between
   `timestamp` and the current time."
  [timestamp timeout]
  (> timeout (- (time-now) timestamp)))

(defn- activity!
  "Returns an event handler that updates the users last activity."
  [timestamp activity]
  (fn [_]
    (reset! timestamp (time-now))
    (reset! activity true)))

(defn- when-interval
  "See js/setInterval."
  [ms func]
  (js/setInterval func ms))
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; Internal Idle State ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(def ^:dynamic *activity* (j/cell true))

(def ^:dynamic *last-activity* (j/cell (time-now)))
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; Public Idle State ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(def idle? (j/cell= (not *activity*)))

(def active? (j/cell= *activity*))
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; Public Idle Methods ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn idle!
  "Initialize idle activity tracking."
  [& opts]
  (let [timeout   (:timeout   opts 30000)
        events    (:events    opts *default-events*)
        activity  (:activity  opts *activity*)
        timestamp (:timestamp opts *last-activity*)]
    (doseq [e events]
      (.addEventListener js/window e #(activity! timestamp activity)))
    (when-interval timeout
      #(when (time-diff @timestamp timeout) (reset! activity false)))))
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
