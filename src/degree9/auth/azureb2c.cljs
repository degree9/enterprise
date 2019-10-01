(ns degree9.auth.azureb2c
  (:require [degree9.debug :as dbg]
            [goog.object :as obj]
            [meta.server :as server]
            [degree9.env :as env]
            [degree9.auth.oauth :as oauth]
            ["@feathersjs/authentication-oauth/strategy" :as fos]))

(def ^:private debug (dbg/debug "degree9:enterprise:auth:azureb2c"))

(defprotocol OAuthStrategy
  (getRedirect [this data] "")
  (authenticate [this data params] ""))

(defn azureb2c [& opts]
  (let [oauth (oauth/oauth)]
    (specify fos/OAuthStrategy
      OAuthStrategy
    ;   (entityId [this]
    ;     (let [id (obj/get oauth "entityId")]
    ;       (id this)))
    ;   (getEntityQuery [this profile params]
    ;     (let [query (obj/get oauth "getEntityQuery")]
    ;       (debug "Authentication entity query profile:" profile)
    ;       (debug "Authentication entity query params:" params)
    ;       (query this profile params)))
    ;   (getEntityData [this profile entity params]
    ;     (let [data (obj/get oauth "getEntityData")]
    ;       (debug "Authentication entity data profile:" profile)
    ;       (debug "Authentication entity data entity:" entity)
    ;       (debug "Authentication entity data params:" params)
    ;       (data this profile entity params)))
    ;   (getProfile [this data params]
    ;     (let [profile (obj/get oauth "getProfile")]
    ;       (debug "Authentication profile data:" data)
    ;       (debug "Authentication profile params:" params)
    ;       (profile this data params)))
      (getRedirect [_ data]
        (let [redirect (obj/get oauth "getRedirect")]
          (debug "Authenticate Redirect:" redirect)
          (debug "Authentication redirect data:" data)
          (.getRedirect oauth data)))
    ;   (getCurrentEntity [this params]
    ;     (let [current (obj/get oauth "getCurrentEntity")]
    ;       (debug "Authentication current entity params:" params)
    ;       (current this params)))
    ;   (findEntity [this profile params]
    ;     (let [find (obj/get oauth "findEntity")]
    ;       (debug "Authentication find entity profile:" profile)
    ;       (debug "Authentication find entity params:" params)
    ;       (find this profile params)))
    ;   (createEntity [this profile params]
    ;     (let [create (obj/get oauth "createEntity")]
    ;       (debug "Authentication create entity profile:" profile)
    ;       (debug "Authentication create entity params:" params)
    ;       (create this profile params)))
    ;   (updateEntity [this entity profile params]
    ;     (let [update (obj/get oauth "updateEntity")]
    ;       (debug "Authentication update entity:" entity)
    ;       (debug "Authentication update entity profile:" profile)
    ;       (debug "Authentication update entity params:" params)
    ;       (update this entity profile params)))
      (authenticate [_ data params]
        (let [data (obj/get data "error" data)
              authenticate (obj/get oauth "authenticate")]
          (debug "Authenticate:" authenticate)
          (debug "Authentication data:" data)
          (debug "Authentication params:" params)
          (.authenticate oauth data))))))

(defn with-azureb2c [app & opts]
  (let [auth (.service app "/authentication")
        svc  (azureb2c)]
    (debug "Registering Azure B2C auth strategy.")
    (.register auth "azureb2c" svc)
    app))
