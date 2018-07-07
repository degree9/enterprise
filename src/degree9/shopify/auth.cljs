(ns degree9.shopify.auth
 (:require
  degree9.shopify.spec
  degree9.shopify.url
  [cljs.spec.alpha :as spec]))

(defn auth?
 "True if the passed value is valid auth credentials"
 [maybe-auth]
 (spec/valid? ::credentials maybe-auth))

(defn username-password->auth
 "Given a username and password, returns auth credentials"
 [username password]
 {:post [(auth? %)]}
 {:degree9.shopify.auth/username username
  :degree9.shopify.auth/password password})

; default auth using the creds in data
(def default-auth
 (partial username-password->auth
  degree9.shopify.data/api-key
  degree9.shopify.data/api-secret))

(defn with-url-auth
 "Given a URL and auth credentials, returns a URL with auth"
 [url auth]
 {:pre [(degree9.shopify.url/url? url)
        (auth? auth)]}
 (-> url
  (assoc :username (:degree9.shopify.auth/username auth))
  (assoc :password (:degree9.shopify.auth/password auth))))
