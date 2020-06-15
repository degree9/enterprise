(ns degree9.error
  (:require [goog.string :as gstr]
            [goog.string.format]))

(defn error [msg & vars]
  (let [format (partial gstr/format msg)]
    (js/Error. (apply format vars))))
