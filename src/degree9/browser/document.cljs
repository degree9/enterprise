(ns degree9.browser.document
  (:require [degree9.browser.window :as bom]))

(defn document []
  (bom/document js/window))
