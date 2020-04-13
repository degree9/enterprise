(ns degree9.paysafe.card
  (:require [degree9.paysafe.core :as ps]))

;; Card Payments API ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn create-authorization [data]
  (ps/post "/auths" data))

(defn get-authorization [id]
  (ps/get (str "/auths/" id)))

(defn update-authorization [id data]
  (ps/put (str "/auths/" id) data))

(defn void-authorization [id data]
  (ps/post (str "/auths/" id "/voidauths") data))

(defn get-void-authorization [id]
  (ps/get (str "/voidauths/" id)))

(defn settle-authorization [id data]
  (ps/post (str "/auths/" id "/settlements") data))

(defn get-settlement [id]
  (ps/get (str "/settlements/" id)))

(defn cancel-settlement [id data]
  (ps/put (str "/settlements/" id) data))

(defn submit-refund [id data]
  (ps/post (str "/settlements/" id "/refunds") data))

(defn get-refund [id]
  (ps/get (str "/refunds/" id)))

(defn cancel-refund [id data]
  (ps/put (str "/refunds/" id) data))

(defn create-verification [data]
  (ps/post "/verifications" data))

(defn get-verification [id]
  (ps/get (str "/verifications/" id)))

(defn create-originalcredits [data]
  (ps/post "/originalcredits" data))

(defn get-originalcredits [id data]
  (ps/get (str "/originalcredits/" id) data))

(defn cancel-originalcredits [id data]
  (ps/put (str "/standalonecredits/" id) data))

(defn create-standalonecredits [data]
  (ps/post "/standalonecredits" data))

(defn get-standalonecredits [id data]
  (ps/get (str "/standalonecredits/" id) data))

(defn cancel-standalonecredits [id data]
  (ps/put (str "/standalonecredits/" id) data))



;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
