(ns degree9.browser.location
  (:require [degree9.browser.window :as bom]))

(defn location []
  (bom/location js/window))
