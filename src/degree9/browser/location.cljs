(ns degree9.browser.location
  (:require [degree9.browser :as bom]))

(defn location []
  (bom/location js/window))
