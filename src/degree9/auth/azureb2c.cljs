(ns degree9.auth.azureb2c
  (:require [degree9.debug :as dbg]
            [goog.object :as obj]
            [meta.server :as server]
            [degree9.env :as env]
            [degree9.es6 :as es6]
            [degree9.auth.oauth :as oauth]))
            ;["./azureb2c.js" :as AzureB2CStrategy]))

(def ^:private debug (dbg/debug "degree9:enterprise:auth:azureb2c"))

(set! (.. oauth/OAuthStrategy -prototype -getProfile)
  (fn [data & args]
    (debug "raw profile data: " data)
    (-> data
      (obj/get "id_token")
      (obj/get "payload"))))

(defn with-azureb2c [app & opts]
  (let [auth (.service app "/authentication")
        svc  (oauth/OAuthStrategy.)]
    (debug "Registering Azure B2C auth strategy.")
    (.register auth "azureb2c" svc)
    app))
