(ns degree9.auth.salesforce
  (:require ["debug" :as dbg]
            [meta.server :as server]
            ["passport-forcedotcom" :as sf]))

(def ^:private debug (dbg "degree9:enterprise:auth:salesforce"))

(defn with-salesforce [app & opts]
  (let [id     (env/get "SALESFORCE_CLIENT_ID")
        secret (env/get "SALESFORCE_CLIENT_SECRET")]
    (debug "Creating Salesforce auth service.")
    (server/with-authentication-oauth2 app
      #js{:name "forcedotcom"
          :Strategy (.Strategy sf)
          :clientId id
          :clientSecret secret
          :scope (:scope opts)})))
