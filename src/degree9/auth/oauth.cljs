(ns degree9.auth.oauth
  (:require [degree9.debug :as dbg]
            [goog.object :as obj]
            ["@feathersjs/authentication-oauth" :as auth]))


(def ^:private debug (dbg/debug "degree9:enterprise:auth:azureb2c"))

(defn oauth []
  (let [strategy (obj/get auth "OAuthStrategy")]
    (strategy.)))
