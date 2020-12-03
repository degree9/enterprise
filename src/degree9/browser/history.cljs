(ns degree9.browser.history
  (:require [degree9.events :as events]
            [degree9.browser.window :as bom]))


(defprotocol IHistory
  "Interface for interacting with HTML5 History API."
  (back [this] "Move backward through history.")
  (forward [this] "Move forward through history.")
  (go [this delta] "Move to specific page in history.")
  (pushState [this state title] [this state title url] "Pushes state onto the history stack.")
  (replaceState [this state title] [this state title url] "Updates the current state on the history stack."))

(extend-protocol IHistory
  js/History
  (back [this] (.back this))
  (forward [this] (.forward this))
  (go [this delta] (.go this delta))
  (pushState
    ([this state title] (.pushState this state title))
    ([this state title url] (.pushState this state title url)))
  (replaceState
    ([this state title] (.replaceState this state title))
    ([this state title url] (.replaceState this state title url))))

(defn history []
  (bom/history js/window))

(defn push-state! [state title & [url]]
  (pushState (history) (clj->js state) title url))

(defn replace-state! [state title & [url]]
  (replaceState (history) (clj->js state) title url))

(defn popstate! [state]
  (bom/dispatch! (events/popstate state)))
