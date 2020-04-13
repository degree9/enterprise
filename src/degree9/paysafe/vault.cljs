(ns degree9.paysafe.vault
  (:require [degree9.paysafe.core :as ps]))

;; Vault API ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn create-profile [data]
  (ps/post "/profiles" data))

(defn get-profile [id]
  (ps/get (str "/profiles/" id)))

(defn update-profile [id data]
  (ps/put (str "/profiles/" id) data))

(defn delete-profile [id]
  (ps/delete (str "/profiles/" id)))

(defn create-address [profile data]
  (ps/post (str "/profiles/" profile "/addresses") data))

(defn get-address [profile id]
  (ps/get (str "/profiles/" profile "/addresses/" id)))

(defn update-address [profile id data]
  (ps/put (str "/profiles/" profile "/addresses/" id) data))

(defn delete-address [profile id]
  (ps/delete (str "/profiles/" profile "/addresses/" id)))

(defn create-card [profile data]
  (ps/post (str "/profiles/" profile "/cards") data))

(defn get-card [profile id]
  (ps/get (str "/profiles/" profile "/cards/" id)))

(defn update-card [profile id data]
  (ps/put (str "/profiles/" profile "/cards/" id) data))

(defn delete-card [profile id]
  (ps/delete (str "/profiles/" profile "/cards/" id)))

(defn create-achbankaccount [profile data]
  (ps/post (str "/profiles/" profile "/achbankaccounts") data))

(defn get-achbankaccount [profile id]
  (ps/get (str "/profiles/" profile "/achbankaccounts/" id)))

(defn update-achbankaccount [profile id data]
  (ps/put (str "/profiles/" profile "/achbankaccounts/" id) data))

(defn delete-achbankaccount [profile id]
  (ps/delete (str "/profiles/" profile "/achbankaccounts/" id)))

(defn create-bacsbankaccount [profile data]
  (ps/post (str "/profiles/" profile "/bacsbankaccounts") data))

(defn get-bacsbankaccount [profile id]
  (ps/get (str "/profiles/" profile "/bacsbankaccounts/" id)))

(defn update-bacsbankaccount [profile id data]
  (ps/put (str "/profiles/" profile "/bacsbankaccounts/" id) data))

(defn delete-bacsbankaccount [profile id]
  (ps/delete (str "/profiles/" profile "/bacsbankaccounts/" id)))

(defn create-bacsbankaccount-mandate [profile id data]
  (ps/post (str "/profiles/" profile "/bacsbankaccounts/" id "/mandates") data))

(defn create-eftbankaccount [profile data]
  (ps/post (str "/profiles/" profile "/eftbankaccounts") data))

(defn get-eftbankaccount [profile id]
  (ps/get (str "/profiles/" profile "/eftbankaccounts/" id)))

(defn update-eftbankaccount [profile id data]
  (ps/put (str "/profiles/" profile "/eftbankaccounts/" id) data))

(defn delete-eftbankaccount [profile id]
  (ps/delete (str "/profiles/" profile "/eftbankaccounts/" id)))

(defn create-sepabankaccount [profile data]
  (ps/post (str "/profiles/" profile "/sepabankaccounts") data))

(defn get-sepabankaccount [profile id]
  (ps/get (str "/profiles/" profile "/sepabankaccounts/" id)))

(defn update-sepabankaccount [profile id data]
  (ps/put (str "/profiles/" profile "/sepabankaccounts/" id) data))

(defn delete-sepabankaccount [profile id]
  (ps/delete (str "/profiles/" profile "/sepabankaccounts/" id)))

(defn create-sepabankaccount-mandate [profile id data]
  (ps/post (str "/profiles/" profile "/sepabankaccounts/" id "/mandates") data))

(defn create-applepaysingleusetoken [data]
  (ps/post (str "/applepaysingleusetokens") data))

(defn create-googlepaysingleusetokens [data]
  (ps/post (str "/googlepaysingleusetokens") data))

(defn create-singleusetokens [data]
  (ps/post (str "/singleusetokens") data))

(defn create-achsingleusetokens [data]
  (ps/post (str "/achsingleusetokens") data))

(defn create-eftsingleusetokens [data]
  (ps/post (str "/eftsingleusetokens") data))

(defn get-mandate [profile id]
  (ps/get (str "/profiles/" profile "/mandates/" id)))

(defn update-mandate [profile id data]
  (ps/put (str "/profiles/" profile "/mandates/" id) data))

(defn delete-mandate [profile id]
  (ps/delete (str "/profiles/" profile "/mandates/" id)))
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
