(ns degree9.timekit.credential_endpoints
  (:require [degree9.timekit.core :as tk]
            [degree9.debug :as dbg]))

(dbg/defdebug debug "degree9:timekit:credential_endpoints")


(defn get-credentials [client]
  (.getCredentials client))

(defn create-credential [client & args]
  (.createCredential client args))

(defn delete-credential [client id]
  (.deleteCredential client id))

(defn credential [& [opts]]
    (let [conf (merge {:key (env/get "TIMEKIT_API_KEY")} opts)
          timekit (tk/configure conf)])
    (debug "" timekit)
    (reify
      Object
      (find [this & [params]]
          (get-credentials timekit))
      (create [this data & [params]]
          (create-credential timekit))
      (remove [this id params]
          (delete-credential timekit id))))
