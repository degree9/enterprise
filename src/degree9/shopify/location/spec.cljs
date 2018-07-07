(ns degree9.shopify.location.spec
 (:require
  [cljs.spec.alpha :as spec]))

(spec/def :degree9.shopify.location/admin_graphql_api_id string?)
(spec/def :degree9.shopify.location/province_code
 (spec/nilable string?))
(spec/def :degree9.shopify.location/phone string?)
(spec/def :degree9.shopify.location/name string?)
(spec/def :degree9.shopify.location/city string?)
(spec/def :degree9.shopify.location/address1 string?)
(spec/def :degree9.shopify.location/updated_at string?)
(spec/def :degree9.shopify.location/country_code string?)
(spec/def :degree9.shopify.location/country_name string?)
(spec/def :degree9.shopify.location/address2 string?)
(spec/def :degree9.shopify.location/zip string?)
(spec/def :degree9.shopify.location/id pos-int?)
(spec/def :degree9.shopify.location/legacy boolean?)
(spec/def :degree9.shopify.location/province string?)
(spec/def :degree9.shopify.location/country string?)
(spec/def :degree9.shopify.location/created_at string?)

(spec/def :degree9.shopify.location/location
 (spec/keys
  :req-un [:degree9.shopify.location/admin_graphql_api_id
           :degree9.shopify.location/province_code
           :degree9.shopify.location/phone
           :degree9.shopify.loation/name
           :degree9.shopify.location/city
           :degree9.shopify.location/address1
           :degree9.shopify.location/updated_at
           :degree9.shopify.location/country_code
           :degree9.shopify.location/country_name
           :degree9.shopify.location/address2
           :degree9.shopify.location/zip
           :degree9.shopify.location/id
           :degree9.shopify.location/legacy
           :degree9.shopify.location/province
           :degree9.shopify.location/country
           :degree9.shopify.location/created_at]))

(spec/def :degree9.shopify.location/locations
 (spec/coll-of
  :degree9.shopify.location/location
  :kind vector?))
