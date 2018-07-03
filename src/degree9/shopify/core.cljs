(ns degree9.shopify.core
 (:require
  degree9.shopify.http-shim
  [cljs.test :refer-macros [deftest is]]
  degree9.shopify.data
  cemerick.url
  ["request" :as request]))

(defn username-password->auth
 [username password]
 {:username username
  :password password})
(def default-auth
 (partial username-password->auth
  degree9.shopify.data/api-key
  degree9.shopify.data/api-secret))

(defn url?
 [maybe-url]
 (instance? cemerick.url/URL maybe-url))

(defn endpoint->url
 [endpoint]
 {:pre [(or (string? endpoint) (url? endpoint))]
  :post [(url? %)]}
 (->
  (cemerick.url/url endpoint)
  (assoc :protocol degree9.shopify.data/protocol)
  (assoc :host degree9.shopify.data/host)))

(defn with-auth
 [url]
 {:pre [(url? url)]}
 (-> url
  (assoc :username degree9.shopify.data/api-key)
  (assoc :password degree9.shopify.data/api-secret)))

(def with-url-defaults
 (comp
  str
  with-auth
  endpoint->url))

(def with-params-defaults identity)

(defn default-callback
 [err response body]
 (prn err response body))

(defn api!
 ([endpoint]
  (api! (default-auth) endpoint))
 ([auth endpoint] (api! auth endpoint default-callback))
 ([auth endpoint cb]
  (let [url (with-url-defaults endpoint)]
        ; params (with-params-defaults params)]
   (request url cb))))

(defn ??api!
 []
 (prn
  (api!
   "/admin/shop.json")))
