(ns degree9.timekit.credntial_endpoints
  (:require [degree9.timekit.core :as tk]))


(defn get-credentials [client]
  (.getCredentials client))

(defn create-credential [client & args]
  (.createCredential client args))

(defn delete-credential [client id]
  (.deleteCredential client id))

(defn credential [& [opts]]
  (let [client (:client opts)]
    (debug "" client)
    (reify
      Object
      (find [this & [params]]
          (get-credentials client))
      (create [this data & [params]]
          (create-credential client))
      (remove [this id params]
          (delete-credential client id)))))
