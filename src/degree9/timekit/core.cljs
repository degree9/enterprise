(ns degree9.timekit.core
  (:require [degree9.debug :as dbg]
            [goog.object :as obj]
            [degree9.env :as env]))





(let [conf  (merge {:key (env/get "HELLOSIGN_API_KEY")} opts)])
