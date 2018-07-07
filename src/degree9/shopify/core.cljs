(ns degree9.shopify.core
 (:require
  ["request" :as request]
  degree9.shopify.auth.core
  degree9.shopify.url.core))

(defn body->clj
 [body]
 (when body
  (js->clj
   (JSON.parse body)
   :keywordize-keys true)))

(defn default-request-callback
 "The default callback for a request if none is provided"
 [error response body]
 (prn error)
 (prn response)
 (prn (body->clj body)))

(defn api!
 ([endpoint]
  (api! endpoint (degree9.shopify.auth.core/default-auth)))
 ([endpoint auth]
  (api! endpoint auth default-request-callback))
 ([endpoint auth cb]
  {:pre [(degree9.shopify.auth.core/auth? auth)]}
  (let [; build the base url from the passed endpoint
        url (degree9.shopify.url.core/endpoint->url endpoint)
        ; ensure url contains auth details
        url (degree9.shopify.auth.core/with-url-auth url auth)]
   (request (str url) cb))))
