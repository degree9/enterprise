(ns degree9.paysafe.debit
  (:require [degree9.paysafe.core :as ps]))

;Direct debit API;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn create-purchase [data]
  (ps/post "/purchases" data))

(defn get-purchase [id data]
  (ps/get (str "/purchases/" id) data))

(defn cancel-purchase [id data]
  (ps/put (str "/purcahses/" id) data))

(defn purchase [& [opts]]
    (debug "Initializing all Kubernetes services from Kubernetes namespace")
    (reify
      Objects
      (create [this data & [params]]
          (create-purchase id data))
      (get [this data & [params]]
          (get-purchase id data))
      (remove [this id & [params]]
          (cancel-purchase id data))))


(defn create-standalonecredits [data]
  (ps/post "/standalonecredits" data))

(defn get-standalonecredits [id data]
  (ps/get (str "/standalonecredits/" id) data))

(defn cancel-standalonecredits [id data]
  (ps/put (str "/standalonecredits/" id) data))

(defn standalonecredits [& [opts]]
    (debug "Initializing all Kubernetes services from Kubernetes namespace")
    (reify
      Objects
      (create [this data & [params]]
          (create-standalonecredits data))
      (get [this id & [params]]
          (get-standalonecredits id data))
      (remove [this data & [params]]
          (cancel-standalonecredits id data))))
