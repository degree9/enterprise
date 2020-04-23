(ns degree9.timekit.credntial_endpoints
  (:require [degree9.timekit.core :as tk]))


(defn get-credentials [client]
  (.getCredentials client))

(defn create-credential [client & args]
  (.createCredential client args))

(defn delete-credential [client id]
  (.deleteCredential client id))  
