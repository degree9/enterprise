; implements the REST API for the Locations resource
; @see https://help.shopify.com/en/api/reference/inventory/location
(ns degree9.shopify.inventory.location.core
 (:require
  degree9.shopify.core
  degree9.shopify.inventory.location.spec
  degree9.shopify.inventory.location.data
  [cljs.spec.alpha :as spec]
  [cljs.test :refer-macros [deftest is]]))

(defn location?
 [maybe-location]
 (spec/valid?
  :degree9.shopify.inventory.location/location
  maybe-location))

(defn locations?
 [maybe-locations]
 (spec/valid?
  :degree9.shopify.inventory.location/locations
  maybe-locations))

; Fetch all locations
; @see https://help.shopify.com/en/api/reference/inventory/location#index
(def locations!
 (partial
  degree9.shopify.core/api!
  :endpoint "location.list"))

; Fetch a single location
; @see https://help.shopify.com/en/api/reference/inventory/location#show
(defn location!
 [id & {:keys [auth callback params]}]
 {:pre [(spec/valid? :degree9.shopify.inventory.location/id id)]
  :post [(location? (:location %))]}
 (let [endpoint (str "/admin/locations/" id ".json")]
  (degree9.shopify.core/api!
   :endpoint endpoint
   :auth auth
   :callback callback
   :params params)))

; Fetch the total locations count
; @see https://help.shopify.com/en/api/reference/inventory/location#count
(def count!
 (partial
  degree9.shopify.core/api!
  :endpoint "location.count"))

; Fetch the inventory levels for a location
; Note: Requires `read_inventory` scope
; Note: API only!
;       looks like location inventories are NOT available in the web UI and can
;       only be read/write through the API.
;       @see https://help.shopify.com/en/api/guides/inventory-migration-guide
; @see https://help.shopify.com/en/api/reference/inventory/location#inventory_levels
(defn inventory-levels!
 [id & {:keys [auth params shop-name]}]
 {:pre [(spec/valid? :degree9.shopify.inventory.location/id id)]}
 (let [endpoint (str "locations/" id "/inventory_levels.json")]
  (degree9.shopify.core/api!
   :endpoint endpoint
   :auth auth
   :callback callback
   :params params)))

; TESTS

(deftest ??location?
 (is (-> degree9.shopify.inventory.location.data/locations-response-example :locations first location?))
 (is (not (location? {:foo :bar}))))

(deftest ??locations?
 (is (-> degree9.shopify.inventory.location.data/locations-response-example :locations locations?))
 (is (not (locations? [{:foo :bar}]))))

(deftest ??location!
 (is (-> degree9.shopify.inventory.location.data/location-response-example :location location?)))

(deftest ??count!
 (is (-> degree9.shopify.inventory.location.data/count-response-example :count pos-int?)))
