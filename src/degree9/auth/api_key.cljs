(ns degree9.auth.api-key
  (:require [degree9.debug :as dbg]
            [degree9.object :as obj]
            [degree9.es6 :as es6]
            ["@feathersjs/authentication" :as auth]))


(dbg/defdebug debug "degree9:enterprise:auth:api-key")

(def ^:private BaseStrategy (obj/get auth "AuthenticationBaseStrategy"))

(es6/defclass APIKeyStrategy BaseStrategy
  (constructor []
    (prn "APIKeyStrategy")
    (super)))
  ; (method authenticate [data params]
  ;   (this-as this
  ;     (let [api-key (obj/get-in this [:configuration :api-key-header])
  ;           result (.findEntity this api-key (obj/dissoc params :provider))]
  ;       (clj->js {:authentication {:strategy (obj/get this :name)}
  ;                 :api-key (.getEntity this result params)})))))

; (set! (.. oauth/OAuthStrategy -prototype -getProfile)
;   (fn [data & args]
;     (debug "getProfile raw data " data)
;     (-> data
;       (obj/get "id_token")
;       (obj/get "payload"))))
;
; (set! (.. oauth/OAuthStrategy -prototype -getEntityData)
;   (fn [data & args]
;     (debug "getEntityData raw data " data)
;     #js{:email (first (obj/get data "emails"))}))
;
; (set! (.. oauth/OAuthStrategy -prototype -getEntityQuery)
;   (fn [data & args]
;     (debug "getEntityQuery raw data " data)
;     #js{:email (first (obj/get data "emails"))}))

(defn with-api-key [app & opts]
  (let [auth (.service app "/authentication")
        svc  (APIKeyStrategy.)]
    (debug "Registering API Key auth strategy.")
    (.register auth "api-key" svc)
    app))
