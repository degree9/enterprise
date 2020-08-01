(ns degree9.security
 (:require
   [degree9.es6 :as es6]
   [degree9.debug :as dbg]
   [degree9.object :as obj]
   [feathers.application :as fs]
   [feathers.errors :as error]
   [feathers.authentication :as auth]))

(dbg/defdebug debug "degree9:enterprise:security")

; (defn authenticate [& [{:keys [strategies service whitelist] :or {strategies #js["jwt"] service "/authentication"}}]]
;   (fn [{:keys [app params type path] :as context}]
;     (let [{:keys [authentication query authenticated]} params
;           params (obj/dissoc params :provider :authentication)]
;       (debug "Running authentication on %s" path)
;       (if authenticated context
;         (if-not authentication (throw (error/not-authenticated "Not Authenticated."))
;           (let [params (if whitelist (obj/filter #(whitelist %1) params) params)]
;             (debug "Authenticating with" authentication strategies)
;             (-> (.service app service)
;                 (.authenticate authentication params (es6/js-spread strategies)))))))))

(def authenticate auth/authenticate)

(defn secure-services
 "Takes a feathers app and adds a hook to enforce a valid JWT on every endpoint"
 [app]
 (debug "Secure all app endpoints with JWT")
 (fs/hooks app
   (clj->js {:before {:all [(auth/authenticate #js{:service "/authentication" :strategies ["jwt"]})]}})))

(defn with-security [app]
  (debug "Loading server security api")
  (-> app
    (secure-services)))

(defn hook-context->jwt
 "Extracts JWT from hook context. Will only work after secure-services hook."
 [context]
 (some-> (js->clj context :keywordize-keys true)
  :params
  :accessToken))
