(ns degree9.hubspot.contacts
  (:require [degree9.hubspot.core :as hubspot]))


(defn get-id [id & [opts]]
  (.getById id (clj->js opts)))

(defn get-contacts [& [opts]]
  (.getContacts (clj->js opts)))
