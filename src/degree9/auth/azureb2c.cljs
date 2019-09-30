(ns degree9.auth.azureb2c
  (:require ["debug" :as dbg]
            [meta.server :as server]
            [degree9.env :as env]
            ["passport-azure-ad" :as az]))

(def ^:private debug (dbg "degree9:enterprise:auth:azureb2c"))

(defn- identity-metadata [tenant]
  (str "https://login.microsoftonline.com/" tenant "/v2.0/.well-known/openid-configuration"))

(defn with-azureb2c [app & opts]
  (let [id     (env/get "AAD_CLIENT_ID")
        secret (env/get "AAD_CLIENT_SECRET")
        tenant (env/get "AAD_TENANT")
        scope  (mapv #(str "https://" tenant "/" %) ["openid" "offline_access"])]
    (debug "Creating Azure B2C auth service.")
    (server/with-authentication-oauth2 app
      (clj->js
        {:name "azuread-openidconnect"
         :Strategy az/OIDCStrategy
         :identityMetadata (identity-metadata tenant)
         :clientID id
         :clientSecret secret
         :isB2C true
         :responseType "code id_token"
         :responseMode "query"
         :redirectUrl "http://localhost:8080/auth/azuread-openidconnect/callback"
         :allowHttpForRedirectUrl true
         :policyName "B2C_1_SignIn"
         :scope ["openid" "offline_access"]}))))
