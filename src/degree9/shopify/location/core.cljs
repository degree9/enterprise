; implements the REST API for the Locations resource
; @see https://help.shopify.com/en/api/reference/inventory/location
(ns degree9.shopify.location.core
 (:require
  degree9.shopify.core
  degree9.shopify.location.spec
  degree9.shopify.location.data
  [cljs.spec.alpha :as spec]
  [cljs.test :refer-macros [deftest is]]))

(defn location?
 [maybe-location]
 (spec/valid?
  :degree9.shopify.location/location
  maybe-location))

(defn locations?
 [maybe-locations]
 (spec/valid?
  :degree9.shopify.location/locations
  maybe-locations))

; https://help.shopify.com/en/api/reference/inventory/location#index
(def locations!
 (partial
  degree9.shopify.core/api!
  :endpoint "/admin/locations.json"))

; https://help.shopify.com/en/api/reference/inventory/location#show
(defn location!
 [id & {:keys [auth callback params]}]
 {:pre [(spec/valid? :degree9.shopify.location/id id)]
  :post [(location? (:location %))]}
 (let [endpoint (str "/admin/locations/" id ".json")]
  (degree9.shopify.core/api!
   :endpoint endpoint
   :auth auth
   :callback callback
   :params params)))

; https://help.shopify.com/en/api/reference/inventory/location#count
(def count!
 (partial
  degree9.shopify.core/api!
  :endpoint "/admin/locations/count.json"))

; TESTS

(deftest ??location?
 (is (-> degree9.shopify.location.data/locations-example :locations first location?))
 (is (not (location? {:foo :bar}))))

(deftest ??locations?
 (is (-> degree9.shopify.location.data/locations-example :locations locations?))
 (is (not (locations? [{:foo :bar}]))))
