(ns degree9.shopify.spec
 (:require
  [cljs.spec.alpha :as spec]))

; all gid:// are strings
(spec/def :degree9.shopify/admin_graphql_api_id string?)

; all IDs are integers
(spec/def :degree9.shopify/id pos-int?)

; all times are iso8601
(spec/def :degree9.shopify/time string?)
(spec/def :degree9.shopify/created_at :degree9.shopify/time)
(spec/def :degree9.shopify/updated_at :degree9.shopify/time)
(spec/def :degree9.shopify/published_at :degree9.shopify/time)

; weights are numbers
(spec/def :degree9.shopify/weight number?)

; money
(spec/def :degree9.shopify/monetary_amount string?)
(spec/def :degree9.shopify/price :degree9.shopify/monetary_amount)
(spec/def :degree9.shopify/currency string?)

; titles are strings
(spec/def :degree9.shopify/title string?)

; skus are strings
(spec/def :degree9.shopify/sku string?)

; tags are strings
(spec/def :degree9.shopify/tags string?)

; vendors are strings
(spec/def :degree9.shopify/vendor string?)

; fulfillment services are strings
(spec/def :degree9.shopify/fulfillment_service string?)

; emails are strings
(spec/def :degree9.shopify/email string?)

; urls are strings
(spec/def :degree9.shopify/url string?)

; phone numbers are strings
(spec/def :degree9.shopify/phone string?)

; quantities are numbers
(spec/def :degree9.shopify/quantity nat-int?)

; i18n
(spec/def :degree9.shopify/locale string?)

; IPs are strings
(spec/def :degree9.shopify/ip string?)

; system state values
(spec/def :degree9.shopify/position int?)
(spec/def :degree9.shopify/empty-api-response
 (spec/and map? empty?))
(spec/def :degree9.shopify/token string?)

; counts are ints
(spec/def :degree9.shopify/count nat-int?)

; attributes are name/value pairs
(spec/def :degree9.shopify.attribute/name string?)
(spec/def :degree9.shopify.attribute/value string?)
(spec/def :degree9.shopify/attribute
 (spec/keys
  :req-un
  [:degree9.shopify.attribute/name
   :degree9.shopify.attribute/value]))
