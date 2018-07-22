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

; prices are decimal strings
(spec/def :degree9.shopify/price string?)

; titles are strings
(spec/def :degree9.shopify/title string?)

; system state values
(spec/def :degree9.shopify/position int?)
(spec/def :degree9.shopify/empty-api-response
 (spec/and map? empty?))

; counts are ints
(spec/def :degree9.shopify/count nat-int?)
