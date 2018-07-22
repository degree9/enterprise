; implements the REST API for the Product resource
; @see https://help.shopify.com/en/api/reference/products/product
(ns degree9.shopify.products.product.core
 (:require
  degree9.shopify.core
  taoensso.timbre
  degree9.shopify.products.product.spec))

; List all products
;
; Accepts many parameters for filtering, see Shopify docs
;
; # Examples
;
; ```
; (list!
;
; # References
;
; - https://help.shopify.com/en/api/reference/products/product#index
;
(def list!
 (partial
  degree9.shopify.core/api!
  :endpoint "product.list"
  :spec :degree9.shopify.products.product/products))

; Get a single product by ID
;
; Accepts a single optional parameter `fields` to filter returned fields.
;
; # Examples
;
; ```
; (get! :params [1370204536875]) ; returns entire product object
; (get! :params [1370204536875 {:fields [:title]}]) ; returns {:title "Test product A"}
; ```
;
; # References
;
; - https://help.shopify.com/en/api/reference/products/product#show
;
(def get!
 (partial
  degree9.shopify.core/api!
  :endpoint "product.get"
  :spec :degree9.shopify.products.product/product))

; Create a product
;
; Accepts a valid :degree9.shopify.products.product/product object.
;
; Requires additional app permissions to be set.
;
; # Examples
;
; ```
; (create! :params [{:title "foo"}]) ; creates and returns new product with title "foo"
; ```
;
; # References
;
; -  https://help.shopify.com/en/api/reference/products/product#create
;
(def create!
 (partial
  degree9.shopify.core/api!
  :endpoint "product.create"))

; Update a product
; @see https://help.shopify.com/en/api/reference/products/product#update
(defn update!
 [product-data]
 (taoensso.timbre/error "update! endpoint not implemented, see issue #11"))

; Delete a product
; @see https://help.shopify.com/en/api/reference/products/product#destroy
(defn delete!
 [product-data]
 (taoensso.timbre/error "delete! endpoint not implemented, see issue #11"))

; Fetch the total products count
;
; Accepts several parameters for filtering what is counted, see Shopify docs
;
; # Examples
;
; ```
; (count!) ; count all products
; (count! :params [{:vendor :cannabit-dev}]) ; count all products for vendor
; ```
;
; # References
;
; - https://help.shopify.com/en/api/reference/products/product#count
;
(def count!
 (partial
  degree9.shopify.core/api!
  :endpoint "product.count"
  :spec :degree9.shopify/count))
