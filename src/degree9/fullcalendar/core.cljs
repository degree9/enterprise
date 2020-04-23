(ns degree9.fullcalendar.core
  (:require [hoplon.core :as h]
            [goog.object :as obj]
            ["@fullcalendar/core" :as fullcal]))
          
(defn mkcal [el & [opts]]
  (let [calendar (obj/get fullcal "Calendar")]
    (calendar. el (clj->js opts))))

(h/defelem calendar [attr kids]
  (let [cal (h/div)
        plugins (:plugins attr #js["dayGrid"]);plugins/daygrid plugins/timegrid plugins/list])
        fcal (mkcal cal {:plugins plugins})]
    (.render fcal)
    cal))

(defn render
  "Will initially render a calendar, or if it is already rendered, will rerender it"
  [cal]
  (.render cal))

(defn destroy
  "Restores the container element to the state before FullCalendar"
  [cal]
  (.destroy cal))

(defn batch-rendering
  "A way to group operations that cause rerenders"
  [cal]
  (.batchRendering cal))

(defn get-option
  "A way to group operations that cause rerenders"
  [cal & [opts]]
  (.getOption cal opts))

(defn set-option
  "A way to group operations that cause rerenders"
  [cal & [opts]]
  (.setOption cal opts))

(defn goto-date
  "Moves the calendar to an arbitraty date"
  [cal date]
  (.gotoDate cal date))
