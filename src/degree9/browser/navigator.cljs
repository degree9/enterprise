(ns degree9.browser.navigator
  (:require [degree9.browser :as bom]))

(defprotocol INavigator
  "Interface for interacting with Window Navigator (Browser User Agent)."
  (clipboard [this] "The Clipboard object used to access the system clipboard."))

(extend-protocol INavigator
  js/Navigator
  (clipboard [this] (.-clipboard this)))
