(ns degree9.timekit.account
  (:require [degree9.timekit.core :as tk]))


(defn get-accounts [client]
  (.getAccounts client))

(defn account-google-signup [client callback]
  (.accountGoogleSignup client callback))

(defn account-google-sync [client]
  (.accountGoogleSync client))

(defn account-microsoft-signup [client callback]
  (.accountMicrosoftSignup client callback))

(defn account-microsoft-sync [client]
  (.accountMicrosoftSync client))

(defn account [& [opts]]
  (let [client (:client opts)]
    (debug "" client)
    (reify
      Object
      (find [this & [params]]
          (get-accounts client))
      (find [this & [params]]
          (account-google-signup client callback))
      (find [this & [params]]
          (account-google-sync client))
      (find [this & [params]]
          (account-microsoft-signup client callback))
      (find [this & [params]]
          (account-microsoft-sync client)))))
