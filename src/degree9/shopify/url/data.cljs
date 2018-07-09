(ns degree9.shopify.url.data
 (:require
  degree9.env))

(def host (partial degree9.env/get :shopify-host))
(def protocol (partial degree9.env/get :shopify-protocol "https"))
