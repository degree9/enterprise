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

(defn api!
 [& {:keys [endpoint auth params shop-name]}]
 (let [endpoint (str endpoint)
       params (if (coll? params) (vec params) [params])
       promise
       (oops.core/oapply+
        (lib shop-name auth)
        endpoint
        (map clj->js params))]
  (-> promise
   (.then #(prn (js->clj % :keywordize-keys true)))
   (.catch #(taoensso.timbre/error %)))
  promise))
