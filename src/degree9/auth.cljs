(ns degree9.auth
  (:require
   [javelin.core :as j]
   [goog.object :as obj]
   [meta.client :as client]
   [meta.promise :as prom])
  (:require-macros degree9.auth))

;; Authentication Cells ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn- user-cell []
  (let [ucell (j/cell nil)]
    (j/cell= ucell #(reset! ucell (js->clj % :keywordize-keys true)))))

(def user (user-cell))
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; Helper Functions ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn- verify-jwt
 "Given the app and a jwt, returns a promise that returns a decoded payload if valid"
 [app jwt]
 {:pre [(string? jwt)]}
 (let [passport (obj/get app "passport")]
  ; https://www.npmjs.com/package/feathers-authentication-client
  (.verifyJWT (obj/get app "passport") jwt)))

(defn- verify-auth-response
 "Parses the auth response, which is a JWT, and returns a promise of the payload"
 [app res]
 (verify-jwt app (get (js->clj res) "accessToken")))

(defn- decodePayload [users payload]
 (let [uid (:userId (js->clj payload :keywordize-keys true))
       user (client/get users uid)]
  user))

(defn- setUser [app user]
 (let [udat (if (array? user) (first user) user)]
  (obj/set app "user" udat)
  udat))

(defn- handle-auth
 ([app users auth] (handle-auth user app users auth))
 ([user-cell app users auth]
  (-> auth
   (prom/then (partial verify-auth-response app))
   (prom/then (partial decodePayload users))
   (prom/then (partial setUser app))
   (prom/then (partial reset! user-cell))
   (prom/err))))

(defn- auth-handler [auth]
  (-> auth
    (prom/then #(js->clj % :keywordize-keys true))
    (prom/then #(get % :user))
    (prom/then #(reset! user %))
    (prom/err)))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; Authentication Functions ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn register! [users email password]
  (-> (client/signup! users email password)
    (prom/err)))

(defn auth! [app users]
  (client/auth! app))
 ;(handle-auth app users (client/auth! app)))

(defn login! [app users strategy & [opts]]
  (auth-handler (client/login! app strategy opts)))
 ;(handle-auth app users (client/login! app strategy opts)))

(defn logout!
 ([app] (logout! user app))
 ([user-cell app]
  (-> (client/logout! app)
   (prom/then #(reset! user-cell nil))
   (prom/err))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; Authentication Events ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn reauthenticate! [app callback]
  (let []
    (.on app "reauthentication-error"
      (fn []
        (prn "REAUTHENTICATION-ERROR")
        (reset! user-cell nil)
        (callback)))))

;(defn- when-auth [app callback]
;  (client/on app "authenticated" callback)
;  (client/on app "logout" callback))
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
