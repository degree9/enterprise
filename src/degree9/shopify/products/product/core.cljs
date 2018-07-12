; implements the REST API for the Product resource
; @see https://help.shopify.com/en/api/reference/products/product
(ns degree9.shopify.products.product.core
 (:require
  degree9.shopify.core
  taoensso.timbre))

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

; Fetch all products
; @see https://help.shopify.com/en/api/reference/products/product#index
(def products!
 (partial
  degree9.shopify.core/api!
  :endpoint "/admin/products.json"))

; Fetch a single product
; @see https://help.shopify.com/en/api/reference/products/product#show
(defn product!
 [id & {:keys [auth callback params]}]
 {:pre [(spec/valid? :degree9.shopify.products.product/id id)]
  :post [(product? (:product %))]}
 (let [endpoint (str "/admin/products/" id ".json")]
  (degree9.shopify.core/api!
   :endpoint endpoint
   :auth auth
   :callback callback
   :params params)))

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
; @see https://help.shopify.com/en/api/reference/products/product#count
(def count!
 (partial
  degree9.shopify.core/api!
  :endpoint "/admin/products/count.json"))
