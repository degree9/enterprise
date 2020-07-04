(ns degree9.auth.api-key
  (:require [degree9.debug :as dbg]
            [degree9.object :as obj]
            [degree9.es6 :as es6]
            [clojure.string :as cstr]
            ["@feathersjs/authentication" :as auth]))


(dbg/defdebug debug "degree9:enterprise:auth:api-key")

(def ^:private BaseStrategy (obj/get auth "AuthenticationBaseStrategy"))

(es6/defclass APIKeyStrategy BaseStrategy
  (constructor [app]
    (debug "Initialize APIKeyStrategy")
    (es6/super app))
  (configuration []
    (let [sconf (.-configuration (es6/super))]
      (debug "Configure APIKeyStrategy with %s." sconf)
      (this-as this
        (let [aconf   (obj/get-in this [:authentication :configuration])
              entity  (obj/get aconf :entity)
              service (obj/get aconf :service)]
          (-> {:entity entity :service service :header "x-api-key" :schemes ["API"]}
              (obj/merge sconf))))))
  (findEntity [token params]
    (this-as this
      (let [conf  (.configuration this)
            field (obj/get conf :tokenField)]
        (if-not token
          (throw (js/Error. "Not Authenticated."))
          (let [service (obj/get conf :service)
                query   (obj/merge params {field token "limit$" 1})]
            (debug "Finding entity from %s with query %s" service query)
            (.log js/console (obj/get this :app))
            (-> (obj/get this :app)
                (.service service)
                (.find query)
                (.then first)))))))
  (authenticate [data params]
    (debug "Authenticate api-key using %s with %s" data params)
    (this-as this
      (let [conf (.configuration this)
            entity (obj/get conf :entity)]
        (when-let [token (obj/get data :api-key)]
          (let [result (.findEntity this token (obj/dissoc params :provider))]
            (.then result prn))))))
            ; (clj->js {:authentication {:strategy (obj/get this :name)}
            ;           :api-key (.getEntity this result params)}))))))
  (parse [req]
    (this-as this
      (let [conf     (.configuration this)
            strategy (obj/get this :name)
            header   (obj/get conf :header)
            schemes  (obj/get conf :schemes)]
        (debug "Checking for %s in request header %s" schemes header)
        (when-let [contents (obj/get-in req [:headers header])]
          (debug "Request header contains %s" contents)
          (let [[scheme value] (cstr/split contents #" " 2)]
            (debug "Checking for %s in %s" scheme schemes)
            (when (some #{scheme} schemes)
              #js{:strategy strategy
                  "api-key" value})))))))


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
        svc  (APIKeyStrategy. app)]
    (debug "Registering API Key auth strategy.")
    (.register auth "api-key" svc)
    app))
