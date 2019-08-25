(ns degree9.idle
  (:require [javelin.core :as j]))

;; State Helpers ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn- active-events []
  ["click" "keydown" "scroll"
   "mousemove" "mouseenter" "mousewheel"
   "touchstart" "touchmove"
   "visibilitychange" "msvisibilitychange" "webkitvisibilitychange"])

(defn- time-now []
  (.getTime (js/Date.)))

(defn- time-diff [initial timeout]
  (let [now (time-now)]
    (> timeout (- now initial))))

(defn- activity! [timestamp activity]
  (fn [_]
    (let [now (time-now)]
      (reset! timestamp now)
      (reset! activity true))))

(defn when-interval [ms func]
  (js/setInterval func ms))
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; Internal Idle State ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(def ^:private *active* (j/cell true))

(def ^:private *last-activity* (j/cell (time-now)))
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; Public Idle State ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(def idle? (j/cell= (not *active*)))

(def active? (j/cell= *active*))
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; Public Idle Methods ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn idle! [& opts]
  (let [timeout   (:timeout   opts 30000)
        events    (:events    opts (active-events))
        activity  (:activity  opts *active*)
        timestamp (:timestamp opts *last-activity*)]
    (doseq [e events]
      (.addEventListener js/window e #(activity! timestamp activity)))
    (when-interval timeout
      #(when (time-diff @timestamp timeout) (reset! activity false)))))
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
