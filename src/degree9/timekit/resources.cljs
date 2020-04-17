(ns degree9.timekit.resources
  (:require [degree9.timekit.core :as tk]))


(defn get-resources [client]
  (.getResources client))

(defn get-resource [client id]
  (.getResource client id))

(defn create-resource [client & args]
  (.createResource client args))

(defn update-resource [client id & args]
  (.updateResource client id args))

(defn resource [& [opts]]
  (let [client (:client opts)]
    (debug "" client)
    (reify
      Object
      (find [this & [params]]
          (get-resources client))
      (get [this id & [params]]
          (get-resource client id))
      (create [this data & [params]]
          (create-resource client))
      (update [this id data params]
          (update-resource client id)))))


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn reset-resource-password [client & args]
  (.getResourcePassword client args))

(defn resource-password [& [opts]]
  (let [client (:client opts)]
    (debug "" client)
    (reify
      Object
      (get [this id & [params]]
          (reset-resource-password client)))))


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn get-resource-timezone [client email]
  (.getResourceTimezone client email))

(defn resource-timezone [& [opts]]
  (let [client (:client opts)]
    (debug "" client)
    (reify
      Object
      (get [this id & [params]]
          (get-resource-timezone client email)))))
