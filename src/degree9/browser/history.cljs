(ns degree9.browser.history
  (:require [degree9.browser :as bom]
            [degree9.events :as events]))

(defprotocol IHistory
  "Interface for interacting with HTML5 History API."
  (back [this] "Returns name of nth key.")
  (forward [this] "Return keys value or null.")
  (go [this delta] "Adds key to storage or updates it's value.")
  (push-state [this state title] [this state title url] "Removes keys from storage if it exists.")
  (replace-state [this state title] [this state title url] "Clears all keys from storage."))

(extend-protocol IHistory
  js/History
  (back [this] (.back this))
  (forward [this] (.forward this))
  (go [this delta] (.go this delta))
  (push-state [this state title & [url]] (.pushState this state title url))
  (replace-state [this state title & [url]] (.replaceState this state title url)))

(defn push-state! [state title & [url]]
  (push-state (bom/get-history) (clj->js state) title url))

(defn replace-state! [state title & [url]]
  (replace-state (bom/get-history) (clj->js state) title url))

(defn popstate! [state]
  (events/dispatch! (bom/get-window) (events/popstate state)))
