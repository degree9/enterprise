(ns degree9.paysafe.platforms
  (:require [degree9.paysafe.core :as ps]))

;Platforms APIs;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;Merchants;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn create-merchant [data]
  (ps/post "/merchants" data))

(defn update-merchant [id data]
  (ps/put (str "/merchants/" id) data))

;Merchant accounts;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn get-merchant [id]
  (ps/get (str "/merchants/" id)))

(defn create-merchant-account [id data]
  (ps/post (str "/merchants/" id) data))

(defn update-merchant-account [id data]
  (ps/patch "/accounts/" id) data)

; (defn update-account [id data]
;   (ps/put "/accounts" id ) data)

(defn get-merchant-account [id]
  (ps/get (str "/accounts/" id)))

;Users;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn create-user [id data]
  (ps/post (str "/accounts/" id "/users") data))

(defn get-user [id data]
  (ps/get (str "/accounts/" id "/users") data))

;Account addresses ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn create-account-address [id data]
  (ps/post (str "/addresses/" id) data))

(defn update-account-address [id data]
  (ps/put "/addresses/" id) data)

(defn get-account-address [id]
  (ps/get (str "/addesses/" id)))

;Business owner ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn create-business-owner [id data]
  (ps/post (str "/accounts" id "/businessowners") data))

(defn update-business-owner [id data]
  (ps/patch "/businessowners/" id) data)

; (defn update-business-owner [id data]
;   (ps/put "/businessowners/" id) data)

(defn get-business-owner [id data]
  (ps/get (str "/businessowners/" id) data))

;Business owner address ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn create-owner-address [id data]
  (ps/post (str "/businessowners/" id "/currentaddresses") data))

(defn update-owner-address [id data]
  (ps/patch "/currentaddresses/" id) data)

; (defn update-owner-address [id data]
;   (ps/put "/currentaddresses/" id) data)

(defn get-owner-address [id]
  (ps/get (str "/currentaddresses/" id)))

(defn create-owner-previous-address [id data]
  (ps/post (str "/businessowners/" id "/previousaddresses") data))

(defn update-owner-previous-address [id data]
  (ps/patch "/previousaddresses/" id) data)

; (defn update-owner-previous-address [id data]
;   (ps/put "/previousaddresses/" id) data)

(defn get-owner-previous-address [id]
  (ps/get (str "/previousaddresses/" id)))

;Canadian Business owner drivers licenses ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn create-drivers-license [id data]
  (ps/post (str "/businessowners/" id "/canadiandrivinglicenses") data))

(defn update-drivers-license [id data]
  (ps/patch "/canadiandrivinglicenses/" id) data)

(defn get-drivers-license [id]
  (ps/get (str "/canadiandrivinglicenses/" id)))

(defn delete-drivers-license [id]
  (ps/delete (str "/canadiandrivinglicenses/" id)))

;Merchant EFT bank accounts;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn create-eftbankaccount [account data]
  (ps/post (str "/accounts/" id "/eftbankaccounts") data))

(defn update-eftbankaccount [account id data]
  (ps/put "/accounts/" account "/eftbankaccounts" id) data)

(defn get-eftbankaccount [account id]
  (ps/get (str "/accounts/" account "/eftbankaccounts" id)))

(defn delete-eftbankaccount [account id]
  (ps/delete (str "/accounts/" account "/eftbankaccounts" id)))

;Merchant ACH bank accounts;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn create-eftbankaccount [account data]
  (ps/post (str "/accounts/" id "/achbankaccounts") data))

(defn update-eftbankaccount [account id data]
  (ps/put "/accounts/" account "/achbankaccounts" id) data)

(defn get-eftbankaccount [account id]
  (ps/get (str "/accounts/" account "/achbankaccounts" id)))

(defn delete-eftbankaccount [account id]
  (ps/delete (str "/accounts/" account "/achbankaccounts" id)))

;Merchant BACS bank accounts;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn create-bacsbankaccount [account data]
  (ps/post (str "/accounts/" id "/bacsbankaccounts") data))

(defn update-bacsbankaccount [account id data]
  (ps/put "/accounts/" account "/bacsbankaccounts" id) data)

(defn get-bacsbankaccount [account id]
  (ps/get (str "/accounts/" account "/bacsbankaccounts" id)))

(defn delete-bacsbankaccount [account id]
  (ps/delete (str "/accounts/" account "/bacsbankaccounts" id)))

;Merchant SEPA bank accounts;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn create-sepabankaccount [account data]
  (ps/post (str "/accounts/" id "/sepabankaccounts") data))

(defn update-sepabankaccount [account id data]
  (ps/put "/accounts/" account "/sepabankaccounts" id) data)

(defn get-sepabankaccount [account id]
  (ps/get (str "/accounts/" account "/sepabankaccounts" id)))

(defn delete-sepabankaccount [account id]
  (ps/delete (str "/accounts/" account "/sepabankaccounts" id)))

;Merchant wire bank accounts;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn create-wirebankaccount [account data]
  (ps/post (str "/accounts/" id "/wirebankaccounts") data))

(defn update-wirebankaccount [account id data]
  (ps/put "/accounts/" account "/wirebankaccounts" id) data)

(defn get-wirebankaccount [account id]
  (ps/get (str "/accounts/" account "/wirebankaccounts" id)))

(defn delete-wirebankaccount [account id]
  (ps/delete (str "/accounts/" account "/wirebankaccounts/" id)))

;Terms and conditions;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn create-termsandconditions [account data]
  (ps/post (str "/accounts/" account "/termsandconditions") data))

;Activate a merchant account;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn activate-merchant-account [account data]
  (ps/post "/accounts/" account "/activation") data)

;Validate a bank account;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn validate-bankaccount [id]
  (ps/post (str "/accounts/" id "/microdeposits")))

;Get a bank microdepsit;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn get-bank-microsdeposit [id]
  (ps/get (str "/microdeposits/" id)))

;Validate a bank account;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn validate-bankaccount [id]
  (ps/post (str "/microdepsit/" id "/validate")))

;Creat bank accounts for subaccounts;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn create-achbank-subaccount [id data]
  (ps/post (str "/merchants/" id "/achbankaccounts") data))

(defn update-achbank-subaccount [id data]
  (ps/put (str "/achbankaccounts/" id) data))

(defn get-achbank-subaccount [id]
  (ps/get (str "/achbankaccounts/" id)))

(defn create-eftbank-subaccount [id data]
  (ps/post (str "/merchants/" id "/eftbankaccounts") data))

(defn update-eftbank-subaccount [id data]
  (ps/put (str "/eftbankaccounts/" id) data))

(defn get-eftbank-subaccount [id]
  (ps/get (str "/eftbankaccounts/" id)))

(defn create-subaccount [id data]
  (ps/post (str "/accounts/" id) "/subaccounts" data))

(defn update-subaccount [id data]
  (ps/patch (str "/subaccounts/" id) data))

(defn get-subaccount [id]
  (ps/get (str "/subaccounts/" id)))

(defn merchant-subaccounts [id]
  (ps/get (str "/accounts/" id "/subaccounts")))

(defn disable-merchant-subaccount [id]
  (ps/patch (str "accounts" id "/subaccounts/")))

;Balance transfers;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn create-credit-account [account data]
  (ps/post (str "/accounts/" account) "/credits" data))

(defn get-credit-transfer [account id]
  (ps/get (str "/accounts/" account "credits" id)))

(defn get-credit-transfer-by-merchant [account ref]
  (ps/get (str "/accounts/" account "/credits?merchantRefNum=" ref)))

(defn create-debit-account [account data]
  (ps/post (str "/accounts/" account "/debits" data)))

(defn get-debit-transfer [account id]
  (ps/get (str "/accounts/" account "/debits" id)))

(defn get-debit-transfer-by-merchant [account ref]
  (ps/get (str "/accounts/" account "/debits?merchantRefNum=" ref)))
