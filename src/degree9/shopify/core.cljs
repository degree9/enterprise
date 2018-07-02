(ns degree9.shopify.core
 (:require
  [cljs.test :refer-macros [deftest is]]
  ajax.core
  degree9.shopify.data
  cemerick.url))

(defn with-auth
 [url]
 (-> url
  (assoc :username degree9.shopify.data/api-key)
  (assoc :password degree9.shopify.data/api-secret)))

(def with-defaults identity)

(defn api!
 ([endpoint] (api! endpoint nil))
 ([endpoint params]
  (let [url (with-auth (cemerick.url/url degree9.shopify.data/base-url endpoint))
        params (with-defaults params)]
   (ajax.core/GET url params))))

(deftest ??api!
 (prn
  (api!
   "admin/shop.json")))
