(ns degree9.timekit.apps
  (:require [degree9.timekit.core :as tk]))


(defn get-app [client]
  (.getApp client))

(defn app [& [opts]]
  (let [client (:client opts)]
    (debug "" client)
    (reify
      Object
      (get [this id params]
          (get-app client)))))
