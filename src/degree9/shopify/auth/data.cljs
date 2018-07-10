(ns degree9.shopify.auth.data
 (:require
  degree9.env))

(def api-key (partial degree9.env/get :shopify-api-key))
(def api-secret (partial degree9.env/get :shopify-api-secret))
