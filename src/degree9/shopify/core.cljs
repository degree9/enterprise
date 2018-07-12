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
 [& {:keys [endpoint auth callback params]}]
 (let [callback (or callback default-request-callback)
       auth (or auth (degree9.shopify.auth.core/default-auth))
       ; build the base url from the passed endpoint
       url (degree9.shopify.url.core/endpoint->url endpoint)
       ; ensure url contains auth details
       url (degree9.shopify.auth.core/with-url-auth url auth)
       params (merge
               {:method "GET"
                :uri (str url)}
               params)]
  (request
   (clj->js params)
   callback)))
