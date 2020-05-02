(ns degree9.paysafe.platforms
  (:require [degree9.paysafe.core :as ps]))

;Platforms APIs;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;Merchants;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn create-merchant [data]
  (ps/post "/merchants" data))

(defn update-merchant [id data]
  (ps/put (str "/merchants/" id) data))

(defn get-merchant [id]
  (ps/get (str "/merchants/" id)))

(defn merchant [& [opts]]
    (debug "Initializing all Kubernetes services from Kubernetes namespace")
    (reify
      Objects
      (create [this data & [params]]
          (create-merchant data))
      (update [this id data & [params]]
          (update-merchant id data))
      (get [this id & [params]]
          (get-merchant id))))

;Merchant accounts;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn create-merchant-account [id data]
  (ps/post (str "/merchants/" id) data))

(defn update-merchant-account [id data]
  (ps/patch "/accounts/" id) data)

; (defn update-account [id data]
;   (ps/put "/accounts" id ) data)

(defn get-merchant-account [id]
  (ps/get (str "/accounts/" id)))

(defn merchant-account [& [opts]]
    (debug "Initializing all Kubernetes services from Kubernetes namespace")
    (reify
      Objects
      (create [this data & [params]]
          (create-merchant-account id data))
      (update [this data & [params]]
          (update-merchant-account id data))
      (get [this id & [params]]
          (get-merchant-account id))))

;Users;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn create-user [id data]
  (ps/post (str "/accounts/" id "/users") data))

(defn get-user [id data]
  (ps/get (str "/accounts/" id "/users") data))

(defn user [& [opts]]
    (debug "Initializing all Kubernetes services from Kubernetes namespace")
    (reify
      Objects
      (create [this data & [params]]
          (create-user id data))
      (get [this id & [params]]
          (get-user id data))))

;Account addresses ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn create-account-address [id data]
  (ps/post (str "/addresses/" id) data))

(defn update-account-address [id data]
  (ps/put "/addresses/" id) data)

(defn get-account-address [id]
  (ps/get (str "/addesses/" id)))

(defn account-address [& [opts]]
    (debug "Initializing all Kubernetes services from Kubernetes namespace")
    (reify
      Objects
      (create [this data & [params]]
          (create-account-address  id data))
      (update [this id data & [params]]
          (update-account-address id data))
      (get [this id & [params]]
          (get-account-address id))))

;Business owner ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn create-business-owner [id data]
  (ps/post (str "/accounts" id "/businessowners") data))

(defn update-business-owner [id data]
  (ps/patch "/businessowners/" id) data)

; (defn update-business-owner [id data]
;   (ps/put "/businessowners/" id) data)

(defn get-business-owner [id data]
  (ps/get (str "/businessowners/" id) data))

(defn business-owner [& [opts]]
    (debug "Initializing all Kubernetes services from Kubernetes namespace")
    (reify
      Objects
      (create [this data & [params]]
          (create-business-owner id data))
      (update [this id data & [params]]
          (update-business-owner id data))
      (get [this id & [params]]
          (get-business-owner  id data))))

;Business owner address ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn create-owner-address [id data]
  (ps/post (str "/businessowners/" id "/currentaddresses") data))

(defn update-owner-address [id data]
  (ps/patch "/currentaddresses/" id) data)

; (defn update-owner-address [id data]
;   (ps/put "/currentaddresses/" id) data)

(defn get-owner-address [id]
  (ps/get (str "/currentaddresses/" id)))

(defn owner-address [& [opts]]
    (debug "Initializing all Kubernetes services from Kubernetes namespace")
    (reify
      Objects
      (create [this data & [params]]
          (create-owner-address id data))
      (update [this id data & [params]]
          (update-owner-address id data))
      (get [this id & [params]]
          (get-owner-address id))))

(defn create-owner-previous-address [id data]
  (ps/post (str "/businessowners/" id "/previousaddresses") data))

(defn update-owner-previous-address [id data]
  (ps/patch "/previousaddresses/" id) data)

; (defn update-owner-previous-address [id data]
;   (ps/put "/previousaddresses/" id) data)

(defn get-owner-previous-address [id]
  (ps/get (str "/previousaddresses/" id)))

(defn owner-previous-address [& [opts]]
    (debug "Initializing all Kubernetes services from Kubernetes namespace")
    (reify
      Objects
      (create [this data & [params]]
          (create-owner-address data))
      (update [this id data & [params]]
          (update-owner-address id data))
      (get [this id & [params]]
          (get-owner-address id))))

(defn merchant-account [& [opts]]
    (debug "Initializing all Kubernetes services from Kubernetes namespace")
    (reify
      Objects
      (create [this data & [params]]
          (create-merchant-account data))
      (update [this id data & [params]]
          (update-merchant-account id data))
      (get [this id & [params]]
          (get-merchant-account id))))

;Canadian Business owner drivers licenses ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn create-drivers-license [id data]
  (ps/post (str "/businessowners/" id "/canadiandrivinglicenses") data))

(defn update-drivers-license [id data]
  (ps/patch "/canadiandrivinglicenses/" id) data)

(defn get-drivers-license [id]
  (ps/get (str "/canadiandrivinglicenses/" id)))

(defn delete-drivers-license [id]
  (ps/delete (str "/canadiandrivinglicenses/" id)))

(defn drivers-license [& [opts]]
    (debug "Initializing all Kubernetes services from Kubernetes namespace")
    (reify
      Objects)
    (create [this data & [params]]
        (create-drivers-license data))
    (update [this id data & [params]]
        (update-drivers-license id data))
    (get [this id & [params]]
        (get-drivers-license id))
    (remove [this id & [params]]
        (delete-drivers-license id)))

;Merchant EFT bank accounts;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn create-eftbankaccount [account data]
  (ps/post (str "/accounts/" id "/eftbankaccounts") data))

(defn update-eftbankaccount [account id data]
  (ps/put "/accounts/" account "/eftbankaccounts" id) data)

(defn get-eftbankaccount [account id]
  (ps/get (str "/accounts/" account "/eftbankaccounts" id)))

(defn delete-eftbankaccount [account id]
  (ps/delete (str "/accounts/" account "/eftbankaccounts" id)))

(defn eftbankaccount [& [opts]]
    (debug "Initializing all Kubernetes services from Kubernetes namespace")
    (reify
      Objects
      (create [this data & [params]]
          (create-eftbankaccount data))
      (update [this id data & [params]]
          (update-eftbankaccount id data))
      (get [this id & [params]]
          (get-eftbankaccount id))
      (remove [this id & [params]]
          (delete-eftbankaccount account id))))


;Merchant ACH bank accounts;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn create-achbankaccount [account data]
  (ps/post (str "/accounts/" id "/achbankaccounts") data))

(defn update-achbankaccount [account id data]
  (ps/put "/accounts/" account "/achbankaccounts" id) data)

(defn get-achbankaccount [account id]
  (ps/get (str "/accounts/" account "/achbankaccounts" id)))

(defn delete-achbankaccount [account id]
  (ps/delete (str "/accounts/" account "/achbankaccounts" id)))

(defn achbankaccount [& [opts]]
    (debug "Initializing all Kubernetes services from Kubernetes namespace")
    (reify
      Objects
      (create [this data & [params]]
          (create-achbankaccount data))
      (update [this id data & [params]]
          (update-achbankaccount id data))
      (get [this id & [params]]
          (get-achbankaccount id))
      (remove [this id & [params]]
          (delete-achbankaccount id))))


;Merchant BACS bank accounts;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn create-bacsbankaccount [account data]
  (ps/post (str "/accounts/" id "/bacsbankaccounts") data))

(defn update-bacsbankaccount [account id data]
  (ps/put "/accounts/" account "/bacsbankaccounts" id) data)

(defn get-bacsbankaccount [account id]
  (ps/get (str "/accounts/" account "/bacsbankaccounts" id)))

(defn delete-bacsbankaccount [account id]
  (ps/delete (str "/accounts/" account "/bacsbankaccounts" id)))

(defn bacsbankaccount [& [opts]]
    (debug "Initializing all Kubernetes services from Kubernetes namespace")
    (reify
      Objects
      (create [this data & [params]]
          (create-bacsbankaccount data))
      (update [this id data & [params]]
          (update-bacsbankaccount id data))
      (get [this id & [params]]
          (get-bacsbankaccount id))
      (remove [this id & [params]]
          (delete-bacsbankaccount id))))

;Merchant SEPA bank accounts;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn create-sepabankaccount [account data]
  (ps/post (str "/accounts/" id "/sepabankaccounts") data))

(defn update-sepabankaccount [account id data]
  (ps/put "/accounts/" account "/sepabankaccounts" id) data)

(defn get-sepabankaccount [account id]
  (ps/get (str "/accounts/" account "/sepabankaccounts" id)))

(defn delete-sepabankaccount [account id]
  (ps/delete (str "/accounts/" account "/sepabankaccounts" id)))

(defn sepabankaccount [& [opts]]
    (debug "Initializing all Kubernetes services from Kubernetes namespace")
    (reify
      Objects
      (create [this data & [params]]
          (create-sepabankaccount data))
      (update [this id data & [params]]
          (update-sepabankaccount id data))
      (get [this id & [params]]
          (get-sepabankaccount id))
      (remove [this id & [params]]
          (delete-sepabankaccount id))))

;Merchant wire bank accounts;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn create-wirebankaccount [account data]
  (ps/post (str "/accounts/" id "/wirebankaccounts") data))

(defn update-wirebankaccount [account id data]
  (ps/put "/accounts/" account "/wirebankaccounts" id) data)

(defn get-wirebankaccount [account id]
  (ps/get (str "/accounts/" account "/wirebankaccounts" id)))

(defn delete-wirebankaccount [account id]
  (ps/delete (str "/accounts/" account "/wirebankaccounts/" id)))

(defn wirebankaccount [& [opts]]
    (debug "Initializing all Kubernetes services from Kubernetes namespace")
    (reify
      Objects
      (create [this data & [params]]
          (create-wirebankaccount account data))
      (update [this id data & [params]]
          (update-wirebankaccount account id data))
      (get [this id & [params]]
          (get-wirebankaccount id))
      (remove [this id & [params]]
          (delete-wirebankaccount id))))

;Terms and conditions;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn create-termsandconditions [account data]
  (ps/post (str "/accounts/" account "/termsandconditions") data))

(defn termsandconditions [& [opts]]
    (debug "Initializing all Kubernetes services from Kubernetes namespace")
    (reify
      Objects
      (create [this data & [params]]
          (create-termsandconditions account data))))

;Activate a merchant account;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn activate-merchant-account [account data]
  (ps/post "/accounts/" account "/activation") data)

(defn merchant-account [& [opts]]
    (debug "Initializing all Kubernetes services from Kubernetes namespace")
    (reify
      Objects
      (create [this data & [params]]
          (create-achbankaccount account data))))

;Validate a bank account;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn validate-bankaccount [id]
  (ps/post (str "/accounts/" id "/microdeposits")))

(defn bankaccount [& [opts]]
    (debug "Initializing all Kubernetes services from Kubernetes namespace")
    (reify
      Objects
      (create [this data & [params]]
          (create-bankaccount id))))

;Get a bank microdepsit;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn get-bank-microsdeposit [id]
  (ps/get (str "/microdeposits/" id)))

(defn bank-microsdeposit [& [opts]]
    (debug "Initializing all Kubernetes services from Kubernetes namespace")
    (reify
      Objects
      (get [this id & [params]]
          (get-bank-microsdeposit id))))

;Create bank accounts for subaccounts;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn create-achbank-subaccount [id data]
  (ps/post (str "/merchants/" id "/achbankaccounts") data))

(defn update-achbank-subaccount [id data]
  (ps/put (str "/achbankaccounts/" id) data))

(defn get-achbank-subaccount [id]
  (ps/get (str "/achbankaccounts/" id)))

(defn achbank-subaccount [& [opts]]
    (debug "Initializing all Kubernetes services from Kubernetes namespace")
    (reify
      Objects
      (create [this data & [params]]
          (create-achbank-subaccount id data))
      (update [this id data & [params]]
          (update-achbank-subaccount id data))
      (get [this id & [params]]
          (get-achbank-subaccount id))))


(defn create-eftbank-subaccount [id data]
  (ps/post (str "/merchants/" id "/eftbankaccounts") data))

(defn update-eftbank-subaccount [id data]
  (ps/put (str "/eftbankaccounts/" id) data))

(defn get-eftbank-subaccount [id]
  (ps/get (str "/eftbankaccounts/" id)))

(defn eftbank-subaccount [& [opts]]
    (debug "Initializing all Kubernetes services from Kubernetes namespace")
    (reify
      Objects
      (create [this data & [params]]
          (create-eftbank-subaccount id data))
      (update [this id data & [params]]
          (update-eftbank-subaccount id data))
      (get [this id & [params]]
          (get-eftbank-subaccount id))))

(defn create-subaccount [id data]
  (ps/post (str "/accounts/" id) "/subaccounts" data))

(defn update-subaccount [id data]
  (ps/patch (str "/subaccounts/" id) data))

(defn get-subaccount [id]
  (ps/get (str "/subaccounts/" id)))

(defn subaccount [& [opts]]
    (debug "Initializing all Kubernetes services from Kubernetes namespace")
    (reify
      Objects
      (create [this id & [params]]
          (create-subaccount data))
      (update [this data & [params]]
          (update-subaccount id data))
      (get [this data & [params]]
          (get-subaccount id))))

(defn merchant-subaccounts [id]
  (ps/get (str "/accounts/" id "/subaccounts")))

(defn disable-merchant-subaccount [id]
  (ps/patch (str "accounts" id "/subaccounts/")))

(defn merchant-subaccount [& [opts]]
    (debug "Initializing all Kubernetes services from Kubernetes namespace")
    (reify
      Objects
      (get [this id & [params]]
          (merchant-subaccount id))
      (remove [this id & [params]]
          (disabled-merchant-subaccount id))))

;Balance transfers;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn create-credit-account [account data]
  (ps/post (str "/accounts/" account) "/credits" data))

(defn credit-account [& [opts]]
    (debug "Initializing all Kubernetes services from Kubernetes namespace")
    (reify
      Objects
      (create [this id & [params]]
          (create-credit-account data))
      (get [this data & [params]]
          (get-credit-account id))))



(defn get-credit-transfer [account id]
  (ps/get (str "/accounts/" account "credits" id)))

(defn credit-transfer [& [opts]]
    (debug "Initializing all Kubernetes services from Kubernetes namespace")
    (reify
      Objects
      (get [this data & [params]]
          (get-credit-transfer account id))))



(defn get-credit-transferbymerchant [account ref]
  (ps/get (str "/accounts/" account "/credits?merchantRefNum=" ref)))

(defn credit-transferbymerchant [& [opts]]
    (debug "Initializing all Kubernetes services from Kubernetes namespace")
    (reify
      Objects
      (get [this data & [params]]
          (get-credit-transferbymerchant id))))



(defn create-debit-account [account data]
  (ps/post (str "/accounts/" account "/debits" data)))

(defn debit-account [& [opts]]
    (debug "Initializing all Kubernetes services from Kubernetes namespace")
    (reify
      Objects
      (create [this id & [params]]
          (create-debit-account account data))))



(defn get-debit-transfer [account id]
  (ps/get (str "/accounts/" account "/debits" id)))

(defn debit-transfer [& [opts]]
    (debug "Initializing all Kubernetes services from Kubernetes namespace")
    (reify
      Objects
      (get [this data & [params]]
          (get-debit-transfer account id))))



(defn get-debit-transferbymerchant [account ref]
  (ps/get (str "/accounts/" account "/debits?merchantRefNum=" ref)))

(defn debit-transferbymerchant [& [opts]]
    (debug "Initializing all Kubernetes services from Kubernetes namespace")
    (reify
      Objects
      (get [this data & [params]]
          (get-debit-transferbymerchant account ref))))
