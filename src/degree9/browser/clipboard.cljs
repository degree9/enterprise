(ns degree9.browser.clipboard
  (:require [degree9.browser :as bom]))

(defprotocol IClipboard
  "Interface for interacting with Clipboard API."
  (clipboard [this] "The Clipboard object used to access the system clipboard."))

(extend-protocol IClipboard
  js/Clipboard
  (clipboard [this] (.-clipboard this)))
