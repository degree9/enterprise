; implements the REST API for the Product resource
; @see https://help.shopify.com/en/api/reference/products/product
(ns degree9.shopify.products.product.core
 (:require
  degree9.shopify.core
  taoensso.timbre
  degree9.shopify.products.product.spec))

(defn product?
 [maybe-product]
 (spec/valid?
  :degree9.shopify.products.product/product
  maybe-product))

(defn image?
 [maybe-image]
 (spec/valid?
  :degree9.shopify.products.product/image
  maybe-image))

(defn products?
 [maybe-products]
 (spec/valid?
  :degree9.shopify.products.product/products
  maybe-products))

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
  :endpoint "product.list"))

; Get a single product by ID
;
; Accepts a single optional parameter `fields` to filter returned fields.
;
; # Examples
;
; ```
; (get! :params [1370204536875]) ; returns entire product object
; (get! :params [1370204536875 {:fields [:title]}]) ; {:title "Test product A"}
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
; @see https://help.shopify.com/en/api/reference/products/product#create
(defn create!
 [product-data]
 (taoensso.timbre/error "create! endpoint not implemented, see issue #11"))

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
; # Examples
;
; ```
; (count!) ; promise resolves to int
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
  :spec :degree9.shopify.products.product/count))
