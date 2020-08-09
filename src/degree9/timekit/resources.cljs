(ns degree9.timekit.resources
  (:require [degree9.timekit.core :as tk]
            [degree9.debug :as dbg]))

(dbg/defdebug debug "degree9:timekit:resources")

(defn get-resources [client]
  (.getResources client))

(defn get-resource [client id]
  (.getResource client id))

(defn create-resource [client & args]
  (.createResource client args))

(defn update-resource [client id & args]
  (.updateResource client id args))

(defn resource [& [opts]]
    (let [conf (merge {:key (env/get "TIMEKIT_API_KEY")} opts)
          timekit (tk/configure conf)])
    (debug "" timekit)
    (reify
      Object
      (find [this & [params]]
          (get-resources timekit))
      (get [this id & [params]]
          (get-resource timekit id))
      (create [this data & [params]]
          (create-resource timekit))
      (update [this id data params]
          (update-resource timekit id))))


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn reset-resource-password [client & args]
  (.getResourcePassword client args))

(defn resource-password [& [opts]]
    (let [conf (merge {:key (env/get "TIMEKIT_API_KEY")} opts)
          timekit (tk/configure conf)])
    (debug "" timekit)
    (reify
      Object
      (get [this id & [params]]
          (reset-resource-password timekit))))


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn get-resource-timezone [client email]
  (.getResourceTimezone client email))

(defn resource-timezone [& [opts]]
    (let [conf (merge {:key (env/get "TIMEKIT_API_KEY")} opts)
          timekit (tk/configure conf)])
    (debug "" timekit)
    (reify
      Object
      (get [this id & [params]]
          (get-resource-timezone timekit email))))
