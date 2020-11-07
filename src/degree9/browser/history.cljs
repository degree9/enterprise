(ns degree9.browser.history
  (:require [degree9.browser :as bom]
            [degree9.events :as events]))

(defprotocol IHistory
  "Interface for interacting with HTML5 History API."
  (back [this] "Move backward through history.")
  (forward [this] "Move forward through history.")
  (go [this delta] "Move to specific page in history.")
  (push-state [this state title] [this state title url] "Pushes state onto the history stack.")
  (replace-state [this state title] [this state title url] "Updates the current state on the history stack."))

(extend-protocol IHistory
  js/History
  (back [this] (.back this))
  (forward [this] (.forward this))
  (go [this delta] (.go this delta))
  (push-state
    ([this state title] (.pushState this state title))
    ([this state title url] (.pushState this state title url)))
  (replace-state
    ([this state title] (.replaceState this state title))
    ([this state title url] (.replaceState this state title url))))

(defn push-state! [state title & [url]]
  (push-state (bom/get-history) (clj->js state) title url))

(defn replace-state! [state title & [url]]
  (replace-state (bom/get-history) (clj->js state) title url))

(defn popstate! [state]
  (events/dispatch! (bom/get-window) (events/popstate state)))
