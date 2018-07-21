; implements the REST API for the InventoryItem resource
; @see https://help.shopify.com/en/api/reference/inventory/inventoryitem
(ns degree9.shopify.inventory.item
 (:require
  degree9.shopify.core))

; Fetch all items
;
; A list of up to 100 `ids` in params is required.
;
; Also supports `page` and `limit` params.
;
; # Examples
;
; ```
; (items! :params [{:ids [1234 2345]}]) ; fetches items 1234 and 2345
; ```
;
; # References
;
; - https://help.shopify.com/en/api/reference/inventory/inventoryitem#index
;
(def items!
 (partial
  degree9.shopify.core/api!
  :endpoint "inventoryItem.list"))

; Fetch one item by ID
;
; # Examples
;
; ```
; (item! :params [1234]) ; fetches item 1234
; ```
;
; # References
;
; - https://help.shopify.com/en/api/reference/inventory/inventoryitem#show
;
(def item!
 (partial
  degree9.shopify.core/api!
  :endpoint "inventoryItem.get"))

; Update one item in place by ID
;
; # Examples
;
; ```
; (update! :params [1234 {:sku "foo"}]) ; sets the sku to "foo" for 1234
; ```
;
; # References
;
; - https://help.shopify.com/en/api/reference/inventory/inventoryitem#update
;
(def update!
 (partial
  degree9.shopify.core/api!
  :endpoint "inventoryItem.update"))
