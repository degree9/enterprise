(ns degree9.shopify.core
 (:require
  ["request" :as request]
  degree9.shopify.auth
  degree9.shopify.url))

(defn default-request-callback
 "The default callback for a request if none is provided"
 [error response body]
 (prn error)
 (prn response)
 (prn body))

(defn api!
 ([endpoint]
  (api! endpoint (degree9.shopify.auth/default-auth)))
 ([endpoint auth]
  (api! endpoint auth default-request-callback))
 ([endpoint auth cb]
  (let [url (degree9.shopify.url/endpoint->url endpoint)]
        ; headers (doto (js/Headers.))]
                 ; (.append "Authorization" (str "Basic " (js/btoa degree9.shopify.data/api-key ":" degree9.shopify.data/api-secret))))]
   (prn "*" url)
   (request
    url
    default-request-callback))))
