; implements the REST API for the Locations resource
; @see https://help.shopify.com/en/api/reference/inventory/location
(ns degree9.shopify.inventory.location.core
 (:require
  degree9.shopify.core
  degree9.shopify.inventory.location.spec
  degree9.shopify.inventory.location.data
  [cljs.spec.alpha :as spec]
  [cljs.test :refer-macros [deftest is]]))

; Fetch all locations
;
; # Examples
;
; ```
; (locations!) ; fetches all locations
; ```
;
; # References
;
; - https://help.shopify.com/en/api/reference/inventory/location#index
;
(def list!
 (partial
  degree9.shopify.core/api!
  :endpoint "location.list"
  :spec :degree9.shopify.inventory.location/locations))

; Fetch a single location by ID
;
; # Examples
;
; ```
; (location! :params [1234]) ; fetches location with ID 1234
; ```
;
; # References
;
; - https://help.shopify.com/en/api/reference/inventory/location#show
;
(def get!
 (partial
  degree9.shopify.core/api!
  :endpoint "location.get"
  :spec :degree9.shopify.inventory.location/location))

; Fetch the total locations count
;
; # References
;
; - https://help.shopify.com/en/api/reference/inventory/location#count
; - https://help.shopify.com/en/api/reference/inventory/location#count
;
; @TODO missing in upstream lib
; @see https://github.com/degree9/enterprise/issues/14
;
; (def count!
;  (partial
;   degree9.shopify.core/api!
;   :endpoint "location.count"))
;   :spec :degree9.shopify/count

; Fetch the inventory levels for a location
;
; Note: Requires `read_inventory` scope
; Note: API only!
;       looks like location inventories are NOT available in the web UI and can
;       only be read/write through the API.
;       @see https://help.shopify.com/en/api/guides/inventory-migration-guide
;
; # References
;
; - https://help.shopify.com/en/api/reference/inventory/location#inventory_levels)
;
; @TODO missing in upstream lib
; @see https://github.com/degree9/enterprise/issues/15
;
; (def inventory-levels!
;  (partial
;   degree9.shopify.core/api!
;   :endpoint "location.inventory-levels"))

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
