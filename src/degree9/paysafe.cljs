(ns degree9.paysafe
  (:require [goog.object :as obj]
            [degree9.debug :as dbg]
            [degree9.env :as env]
            [degree9.paysafe.card :as card]
            [degree9.paysafe.platforms :as platforms]))

(dbg/defdebug debug "degree9:paysafe")

;; Paysafe Authorization Services ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn authorization [& [opts]]
  (let [key (:key opts (env/get "PAYSAFE_API_KEY"))]
    (debug "Initialize authorization service.")
    (reify
      Object
      (get [this id & [params]]
        (debug "Get authorization %s" id)
        (card/get-authorization id))
      (create [this data & [params]]
        (debug "Create authorization with %s" data)
        (card/create-authorization data))
      ; (remove [this id params]
      ;   (card/void-authorization id data))
      (update [this id data & [params]]
        (debug "Update authorization %s with %s" id data)
        (card/update-authorization id data)))))

(defn void-authorization [& [opts]]
  (let [key (:key opts (env/get "PAYSAFE_API_KEY"))]
    (debug "Initialize void authorization service.")
    (reify
      Object
      (get [this id & [params]]
        (card/get-void-authorization id))
      (update [this id data & [params]]
        (card/void-authorization id data)))))
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; Paysafe Settlement Service ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn settlement [& [opts]]
  (let [key (:key opts (env/get "PAYSAFE_API_KEY"))]
    (debug "Initialize settlement service.")
    (reify
      Object
      (get [this id & [params]]
        (card/get-settlement id))
      (remove [this id data]
        (card/cancel-settlement id data)))))
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; Paysafe Refund Service ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn refund [& [opts]]
  (let [key (:key opts (env/get "PAYSAFE_API_KEY"))]
    (debug "Initialize refund service.")
    (reify
      Object
      (create [this data & [param]]
        (let [id (obj/get param "settlement-id")]
          (card/submit-refund id data)))
      (get [this id & [param]]
        (card/get-refund id))
      (update [this id data & [param]]
        (card/cancel-refund id data)))))
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; Paysafe Refund Service ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn verification [& [opts]]
  (let [key (:key opts (env/get "PAYSAFE_API_KEY"))]
    (debug "Initialize verification service.")
    (reify
      Object
      (create [this data]
          (card/create-verification data))
      (get [this id]
          (card/get-verification id)))))
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; Paysafe Merchant Service ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn merchant [& [opts]]
  (let [key (:key opts (env/get "PAYSAFE_API_KEY"))]
    (debug "Initializing merchant service.")
    (reify
      Object
      (create [this data & [params]]
        (platforms/create-merchant data))
      (update [this id data & [params]]
        (platforms/update-merchant id data))
      (get [this id & [params]]
        (platforms/get-merchant id)))))

(defn merchant-account [& [opts]]
  (let [key (:key opts (env/get "PAYSAFE_API_KEY"))]
    (debug "Initializing merchant account service.")
    (reify
      Object
      (create [this data & [params]]
        (let [id (obj/get params "merchant-id")]
          (platforms/create-merchant-account id data)))
      (update [this data & [params]]
        (let [id (obj/get params "merchant-id")]
          (platforms/update-merchant-account id data)))
      (get [this id & [params]]
        (platforms/get-merchant-account id)))))

(defn merchant-account-address [& [opts]]
  (let [key (:key opts (env/get "PAYSAFE_API_KEY"))]
    (debug "Initializing merchant account address service.")
    (reify
      Object
      (create [this data & [params]]
        (let [id (obj/get params "merchant-id")]
          (platforms/create-account-address id data)))
      (update [this id data & [params]]
        (platforms/update-account-address id data))
      (get [this id & [params]]
        (platforms/get-account-address id)))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn business-owner [& [opts]]
  (let [key (:key opts (env/get "PAYSAFE_API_KEY"))]
    (debug "Initializing all Kubernetes services from Kubernetes namespace")
    (reify
      Object
      (create [this data & [params]]
        (let [id (obj/get params "account-id")]
          (platforms/create-business-owner id data)))
      (update [this id data & [params]]
        (platforms/update-business-owner id data))
      (get [this id & [params]]
        (platforms/get-business-owner id)))))


;; Service Monitor ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn monitor [& [opts]]
  (let [key (:key opts (env/get "PAYSAFE_API_KEY"))]
    (debug "Initialize paysafe monitor service.")
    (reify
      Object
      (find [this & [params]]
        (platforms/monitor)))))
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
