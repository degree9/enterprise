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

(defn reset-resource-password [client & args]
  (.getResourcePassword client args))

(defn get-resource-timezone [client email]
  (.getResourceTimezone client email))
