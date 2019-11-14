(ns degree9.auth.azureb2c
  (:require [degree9.debug :as dbg]
            [goog.object :as obj]
            [meta.server :as server]
            [degree9.env :as env]
            [degree9.auth.oauth :as oauth]))

(def ^:private debug (dbg/debug "degree9:enterprise:auth:azureb2c"))

(set! (.. oauth/OAuthStrategy -prototype -getProfile)
  (fn [data & args]
    (debug "getProfile raw data " data)
    (-> data
      (obj/get "id_token")
      (obj/get "payload"))))

(set! (.. oauth/OAuthStrategy -prototype -getEntityData)
  (fn [data & args]
    (debug "getEntityData raw data " data)
    #js{:azureb2cId (obj/get data "sub")
        :email (first (obj/get data "emails"))}))

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
