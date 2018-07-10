(ns degree9.shopify.auth.spec
 (:require
  [cljs.spec.alpha :as spec]))

(spec/def :degree9.shopify.auth/username string?)
(spec/def :degree9.shopify.auth/password string?)

(spec/def :degree9.shopify.auth/credentials
 (spec/keys
  :req [:degree9.shopify.auth/username
        :degree9.shopify.auth/password]))
