(ns degree9.shopify.core
 (:require
  ["shopify-api-node" :as shopify]
  ["request" :as request]
  degree9.shopify.auth.core
  degree9.shopify.url.core
  oops.core
  degree9.env
  taoensso.timbre))

(defn -lib
 [shop-name auth]
 (let [shop-name (or shop-name (degree9.env/get :shopify-host))
       auth (or auth (degree9.shopify.auth.core/default-auth))
       params
       (clj->js
        {:shopName shop-name
         :apiKey (:degree9.shopify.auth/username auth)
         :password (:degree9.shopify.auth/password auth)
         ; @see https://help.shopify.com/en/api/getting-started/api-call-limit?ref=microapps
         :autoLimit {:calls 2
                     :interval 1000
                     ; be conservative to avoid the 40 hard limit
                     :bucketSize 35}})]
  (shopify. params)))

(def lib (memoize -lib))

(defn api!'
 [& {:keys [endpoint-method auth params shop-name]}]
 (let [endpoint-method (or endpoint-method "")
       promise
       (oops.core/ocall+
        (lib shop-name auth)
        endpoint-method
        (clj->js params))]
  (-> promise
   (.then #(prn (js->clj % :keywordize-keys true)))
   (.catch #(taoensso.timbre/error %)))
  promise))

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
