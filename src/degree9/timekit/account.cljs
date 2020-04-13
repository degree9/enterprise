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
