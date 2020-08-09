(ns degree9.auth.azureb2c
  (:require [degree9.debug :as dbg]
            [degree9.object :as obj]
            [degree9.env :as env]
            [degree9.error :as err]
            [degree9.auth.oauth :as oauth]))

(def ^:private debug (dbg/debug "degree9:enterprise:auth:azureb2c"))

(set! (.. oauth/OAuthStrategy -prototype -getProfile)
  (fn [data & args]
    (debug "getProfile raw data " data)
    (if-let [error (obj/get data :error)]
      (throw (err/error (obj/get error :error_description)))
      (obj/get-in data [:id_token :payload]))))

(set! (.. oauth/OAuthStrategy -prototype -getEntityData)
  (fn [data & args]
    (debug "getEntityData raw data " data)
    #js{:email (first (obj/get data "emails"))}))

(set! (.. oauth/OAuthStrategy -prototype -getEntityQuery)
  (fn [data & args]
    (debug "getEntityQuery raw data " data)
    #js{:email (first (obj/get data "emails"))}))

(defn with-azureb2c [app & opts]
  (let [auth (.service app "/authentication")
        svc  (oauth/OAuthStrategy.)]
    (debug "Registering Azure B2C auth strategy.")
    (.register auth "azureb2c" svc)
    app))
