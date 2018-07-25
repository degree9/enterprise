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

; addresses have several fields
(spec/def :degree9.shopify.address/first_name string?)
(spec/def :degree9.shopify.address/last_name string?)
(spec/def :degree9.shopify.address/name string?)
(spec/def :degree9.shopify.address/address1 string?)
(spec/def :degree9.shopify.address/address2 string?)
(spec/def :degree9.shopify.address/phone :degree9.shopify/phone)
(spec/def :degree9.shopify.address/city string?)
(spec/def :degree9.shopify.address/zip string?)
(spec/def :degree9.shopify.address/province string?)
(spec/def :degree9.shopify.address/province_code string?)
(spec/def :degree9.shopify.address/country string?)
(spec/def :degree9.shopify.address/country_code string?)
(spec/def :degree9.shopify.address/company (spec/nilable string?))
(spec/def :degree9.shopify.address/latitude number?)
(spec/def :degree9.shopify.address/longitude number?)

(spec/def :degree9.shopify.address/address
 (spec/keys
  :req-un
  [:degree9.shopify.address/first_name
   :degree9.shopify.address/last_name
   :degree9.shopify.address/name
   :degree9.shopify.address/address1
   :degree9.shopify.address/address2
   :degree9.shopify.address/phone
   :degree9.shopify.address/city
   :degree9.shopify.address/zip
   :degree9.shopify.address/province
   :degree9.shopify.address/province_code
   :degree9.shopify.address/country
   :degree9.shopify.address/country_code
   :degree9.shopify.address/company
   :degree9.shopify.address/latitude
   :degree9.shopify.address/longitude]))

; client details has several fields
(spec/def :degree9.shopify/browser_ip :degree9.shopify/ip)
(spec/def :degree9.shopify/accept_language (spec/nilable string?))
(spec/def :degree9.shopify/user_agent (spec/nilable string?))
(spec/def :degree9.shopify/session_hash (spec/nilable string?))
(spec/def :degree9.shopify/browser_width (spec/nilable string?))
(spec/def :degree9.shopify/browser_height (spec/nilable string?))
(spec/def :degree9.shopify/client_details
 (spec/keys
  :req-un
  [:degree9.shopify/browser_ip
   :degree9.shopify/accept_language
   :degree9.shopify/user_agent
   :degree9.shopify/session_hash
   :degree9.shopify/browser_width
   :degree9.shopify/browser_height]))
