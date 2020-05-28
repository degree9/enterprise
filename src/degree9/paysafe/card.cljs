(ns degree9.paysafe.card
  (:require [degree9.paysafe.core :as ps]))

;; Card Payments API ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; Authorization

(defn create-authorization [data]
  (ps/post "/auths" data))

(defn get-authorization [id]
  (ps/get (str "/auths/" id)))

(defn update-authorization [id data]
  (ps/put (str "/auths/" id) data))

(defn void-authorization [id data]
  (ps/post (str "/auths/" id "/voidauths") data))

(defn settle-authorization [id data]
  (ps/post (str "/auths/" id "/settlements") data))

;; Void Authorization

(defn get-void-authorization [id]
  (ps/get (str "/voidauths/" id)))

;; Settlements

(defn get-settlement [id]
  (ps/get (str "/settlements/" id)))

(defn cancel-settlement [id data]
  (ps/put (str "/settlements/" id) data))

;; Refunds

(defn submit-refund [id data]
  (ps/post (str "/settlements/" id "/refunds") data))

(defn get-refund [id]
  (ps/get (str "/refunds/" id)))

(defn cancel-refund [id data]
  (ps/put (str "/refunds/" id) data))

;; Verification

(defn create-verification [data]
  (ps/post "/verifications" data))

(defn get-verification [id]
  (ps/get (str "/verifications/" id)))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
