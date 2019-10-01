(ns degree9.shopify.core
 (:require
  ["shopify-api-node" :as shopify]
  ["request" :as request]
  degree9.shopify.auth
  degree9.shopify.url
  oops.core
  degree9.env
  taoensso.timbre
  degree9.shopify.products.product.spec
  [cljs.spec.alpha :as spec]))

(defn -lib
 [shop-name auth]
 (let [shop-name (or shop-name (degree9.env/get :shopify-host))
       auth (or auth (degree9.shopify.auth/default-auth))
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

(defn parse-response
 [response]
 (js->clj response :keywordize-keys true))

(defn assert-response-spec
 [response spec]
 (let [parsed (parse-response response)]
  (when-not
   (spec/valid? spec parsed)
   (taoensso.timbre/error "response failed spec:" (spec/explain-str spec parsed)))))

(defn api!
 [& {:keys [endpoint auth params shop-name spec input-spec]}]
 (let [endpoint (str endpoint)
       ; wrap or coerce non-nil values into a vector
       vectorize (fn [v] (when v (if (coll? v) (vec v) [v])))
       params (vectorize params)
       input-spec (vectorize input-spec)
       promise
       (oops.core/oapply+
        (lib shop-name auth)
        endpoint
        (map clj->js params))]
  (when input-spec
   (doall
    (map
     (fn [spec input]
      (when-not
       (spec/valid? spec input)
       (taoensso.timbre/error "input failed spec:" (spec/explain-str spec input))))
     input-spec
     params)))
  (taoensso.timbre/debug "hitting endpoint:" endpoint)
  (-> promise
   (.then
    (fn [response]
     (taoensso.timbre/debug "response:" (parse-response response))
     response))
   ; if a spec is provided, validate the result
   (.then
    (fn [response]
     (when spec
      (assert-response-spec response spec))
     response))
   (.catch #(prn %)))
  promise))
