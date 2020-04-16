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

(defn settle-authorization [id data]
  (ps/post (str "/auths/" id "/settlements") data))

(defn authorization [& [opts]]
  (let [api (:api opts)]
    (debug "" api)
    (reify
      Object
      (get [this id & [params]]
          (get-authorization id data))
      (create [this data & [params]]
          (create-authorization id data))
      (remove [this id params]
          (void-authorization id data))
      (update [this id & [params]]
          (update-authorization id data))
      (find [this data & [params]]
          (create-authorization id data))
      (patch [this id params]
          (cancel-authorization id data)))))

;Get Void Authentication

(defn get-void-authorization [id]
  (ps/get (str "/voidauths/" id)))

(defn void-authorization [& [opts]]
    (debug "" api)
    (reify
      Object
      (get [this id]
          (get-void-authorization id))))


;Settlements;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn get-settlement [id]
  (ps/get (str "/settlements/" id)))

(defn cancel-settlement [id data]
  (ps/put (str "/settlements/" id) data))

(defn settlement [& [opts]]
    (debug "" api)
    (reify
      Object
      (get [this id]
          (get-settlement id data))
      (remove [this id data]
          (cancel-settlement id data))))

;Debit refunds;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn submit-refund [id data]
  (ps/post (str "/settlements/" id "/refunds") data))

(defn get-refund [id]
  (ps/get (str "/refunds/" id)))

(defn cancel-refund [id data]
  (ps/put (str "/refunds/" id) data))

(defn refund [& [opts]]
    (debug "" api)
    (reify
      Object
      (create [this id data]
          (submit-refund id data))
      (get [this id]
          (get-refund id))
      (remove [this id data]
          (cancel-refund id data))))

;Verification;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn create-verification [data]
  (ps/post "/verifications" data))

(defn get-verification [id]
  (ps/get (str "/verifications/" id)))

(defn verification [& [opts]]
    (debug "" api)
    (reify
      Object
      (create [this data]
          (create-verification data))
      (get [this id]
          (get-verification id))))

;Original credits;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn create-originalcredits [data]
  (ps/post "/originalcredits" data))

(defn get-originalcredits [id data]
  (ps/get (str "/originalcredits/" id) data))

(defn cancel-originalcredits [id data]
  (ps/put (str "/originalcredits/" id) data))

(defn originalcredits [& [opts]]
    (debug "" api)
    (reify
      Object
      (create [this data]
          (create-originalcredits data))
      (get [this id data]
          (get-originalcredits id data))
      (remove [this id data]
          (cancel-originalcredits id data))))

; Standalone credits;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn create-standalonecredits [data]
  (ps/post "/standalonecredits" data))

(defn get-standalonecredits [id data]
  (ps/get (str "/standalonecredits/" id) data))

(defn cancel-standalonecredits [id data]
  (ps/put (str "/standalonecredits/" id) data))

(defn standalonecredits [& [opts]]
    (debug "" api)
    (reify
      Object
      (create [this data]
          (create-standalonecredits data))
      (get [this id data]
          (get-standalonecredits id data))
      (remove [this id data]
          (cancel-standalonecredits id data))))


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
