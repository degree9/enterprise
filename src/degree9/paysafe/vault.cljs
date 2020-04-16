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

(defn profile [& [opts]]
    (debug "")
    (reify
      Object
      (create [this data & [params]]
          (create-profile data))
      (get [this id & [params]]
          (get-profile id))
      (update [this id data & [params]]
          (update-profile id data))
      (remove [this id & [params]]
          (delete-profile id))))

(defn create-address [profile data]
  (ps/post (str "/profiles/" profile "/addresses") data))

(defn get-address [profile id]
  (ps/get (str "/profiles/" profile "/addresses/" id)))

(defn update-address [profile id data]
  (ps/put (str "/profiles/" profile "/addresses/" id) data))

(defn delete-address [profile id]
  (ps/delete (str "/profiles/" profile "/addresses/" id)))

(defn address [& [opts]]
    (debug "")
    (reify
      Object
      (create [this data & [params]]
          (create-address profile data))
      (get [this id data & [params]]
          (get-address profile id))
      (update [this id data & [params]]
          (update-address profile id data))
      (remove [this id data & [params]]
          (delete-address profile id))))

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

(defn achbankaccount [& [opts]]
    (debug "")
    (reify
      Object
      (create [this data & [params]]
          (create-achbankaccount profile data))
      (get [this id data & [params]]
          (get-achbankaccount profile id))
      (update [this id data & [params]]
          (update-achbankaccount profile id data))
      (remove [this id data & [params]]
          (cancel-achbankaccount profile id data))))

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

(defn bacsbankaccount [& [opts]]
    (debug "")
    (reify
      Object
      (create [this data & [params]]
          (create-bacsbankaccount profile data))
      (get [this id & [params]]
          (get-bacsbankaccount profile id))
      (update [this id data & [params]]
          (update-bacsbankaccount profile id data))
      (remove [this id & [params]]
          (cancel-bacsbankaccount profile id data))))

(defn create-eftbankaccount [profile data]
  (ps/post (str "/profiles/" profile "/eftbankaccounts") data))

(defn get-eftbankaccount [profile id]
  (ps/get (str "/profiles/" profile "/eftbankaccounts/" id)))

(defn update-eftbankaccount [profile id data]
  (ps/put (str "/profiles/" profile "/eftbankaccounts/" id) data))

(defn delete-eftbankaccount [profile id]
  (ps/delete (str "/profiles/" profile "/eftbankaccounts/" id)))

(defn eftbankaccount [& [opts]]
    (debug "")
    (reify
      Object
      (create [this data & [params]]
          (create-eftbankaccount profile data))
      (get [this id & [params]]
          (get-eftbankaccount profile id))
      (update [this id data & [params]]
          (update-eftbankaccount profile id data))
      (remove [this id & [params]]
          (cancel-eftbankaccount profile id))))

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

(defn sepabankaccount [& [opts]]
    (debug "")
    (reify
      Object
      (create [this data & [params]]
          (create-sepabankaccount  profile data))
      (get [this id data & [params]]
          (get-sepabankaccount profile id))
      (update [this id data & [params]]
          (update-sepabankaccount profile id data))
      (remove [this id data & [params]]
          (cancel-sepabankaccount profile id))))



(defn create-applepaysingleusetoken [data]
  (ps/post (str "/applepaysingleusetokens") data))

(defn applepaysingleusetoken [& [opts]]
    (debug "")
    (reify
      Object
      (create [this data & [params]]
          (create-applepaysingleusetoken data))))




(defn create-googlepaysingleusetokens [data]
  (ps/post (str "/googlepaysingleusetokens") data))

(defn googlepaysingleusetokens [& [opts]]
    (debug "")
    (reify
      Object
      (create [this data & [params]]
          (create-googlepaysingleusetokens data))))


(defn create-singleusetokens [data]
  (ps/post (str "/singleusetokens") data))

(defn singleusetokens [& [opts]]
    (debug "")
    (reify
      Object
      (create [this data & [params]]
          (create-singleusetokens data))))


(defn create-achsingleusetokens [data]
  (ps/post (str "/achsingleusetokens") data))

(defn achsingleusetokens [& [opts]]
    (debug "")
    (reify
      Object
      (create [this data & [params]]
          (create-achsingleusetokens data))))



(defn create-eftsingleusetokens [data]
  (ps/post (str "/eftsingleusetokens") data))

(defn eftsingleusetokens [& [opts]]
    (debug "")
    (reify
      Object
      (create [this data & [params]]
          (create-achsingleusetokens data))))



;Mandate;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn get-mandate [profile id]
  (ps/get (str "/profiles/" profile "/mandates/" id)))

(defn update-mandate [profile id data]
  (ps/put (str "/profiles/" profile "/mandates/" id) data))

(defn delete-mandate [profile id]
  (ps/delete (str "/profiles/" profile "/mandates/" id)))

(defn mandate [& [opts]]
    (debug "")
    (reify
      Object
      (get [this id & [params]]
          (get-mandate profile id))
      (update [this id data & [params]]
          (update-mandate profile id data))
      (remove [this id & [params]]
          (cancel-mandate profile id))))
