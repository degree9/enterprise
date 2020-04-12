(ns degree9.paysafe.debit
  (:require [degree9.paysafe.core :as ps]))

;Direct debit API;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn create-purchases [data]
  (ps/post "/purchases" data))

(defn get-purchases [id data]
  (ps/get (str "/purchases" id) data))

(defn cancel-purchase [id data]
  (ps/put (str "/purcahses/" id) data))

(defn create-standalonecredits [data]
  (ps/post "/standalonecredits" data))

(defn get-standalonecredits [id data]
  (ps/get (str "/standalonecredits/" id) data))

(defn cancel-standalonecredits [id data]
  (ps/put (str "/standalonecredits/" id) data))
