(ns degree9.shopify.discounts.code
 (:require
  [cljs.spec.alpha :as spec]))

(spec/def :degree9.shopify.discounts.discount/code string?)
(spec/def :degree9.shopify.discounts.discount/amount :degree9.shopify/monetary_amount)
(spec/def :degree9.shopify.discounts.discount/type string?)

(spec/def :degree9.shopify.discounts.discount/discount
 (spec/keys
  :req-un
  [:degree9.shopify.discounts.discount/code
   :degree9.shopify.discounts.discount/amount
   :degree9.shopify.discounts.discount/type]))

(spec/def :degree9.shopify.discounts.discount/discounts
 (spec/coll-of
  :degree9.shopify.discounts.discount/discount))
