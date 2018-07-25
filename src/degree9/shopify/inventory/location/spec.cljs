(ns degree9.shopify.inventory.location.spec
 (:require
  degree9.shopify.spec
  [cljs.spec.alpha :as spec]))

(spec/def :degree9.shopify.inventory.location/province_code
 (spec/nilable string?))
(spec/def :degree9.shopify.inventory.location/phone string?)
(spec/def :degree9.shopify.inventory.location/name :degree9.shopify/title)
(spec/def :degree9.shopify.inventory.location/city string?)
(spec/def :degree9.shopify.inventory.location/address1 string?)
(spec/def :degree9.shopify.inventory.location/updated_at string?)
(spec/def :degree9.shopify.inventory.location/country_code string?)
(spec/def :degree9.shopify.inventory.location/country_name string?)
(spec/def :degree9.shopify.inventory.location/address2 string?)
(spec/def :degree9.shopify.inventory.location/zip string?)
(spec/def :degree9.shopify.inventory.location/legacy boolean?)
(spec/def :degree9.shopify.inventory.location/province string?)
(spec/def :degree9.shopify.inventory.location/country string?)

(spec/def :degree9.shopify.inventory.location/location
 (spec/keys
  :req-un [:degree9.shopify/admin_graphql_api_id
           :degree9.shopify/id
           :degree9.shopify/created_at
           :degree9.shopify/updated_at

           :degree9.shopify.location/name
           :degree9.shopify.inventory.location/province_code
           :degree9.shopify.inventory.location/phone
           :degree9.shopify.inventory.location/city
           :degree9.shopify.inventory.location/address1
           :degree9.shopify.inventory.location/country_code
           :degree9.shopify.inventory.location/country_name
           :degree9.shopify.inventory.location/address2
           :degree9.shopify.inventory.location/zip
           :degree9.shopify.inventory.location/legacy
           :degree9.shopify.inventory.location/province
           :degree9.shopify.inventory.location/country]))

(spec/def :degree9.shopify.inventory.location/locations
 (spec/coll-of
  :degree9.shopify.inventory.location/location
  :kind vector?))
