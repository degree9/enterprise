(ns degree9.hubspot.companies
  (:require [degree9.hubspot.core :as hubspot]))


(defn get-id [id]
  (.byId id))

(defn get-contacts [id]
  (.byId id))
