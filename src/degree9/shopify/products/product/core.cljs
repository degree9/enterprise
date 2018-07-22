; implements the REST API for the Product resource
; https://help.shopify.com/en/api/reference/products/product
(ns degree9.shopify.products.product.core
 (:require
  degree9.shopify.core
  degree9.shopify.products.product.spec))

; List all products
;
; Accepts many parameters for filtering, see Shopify docs
;
; # Examples
;
; ```
; (list!) ; lists all products
; (list! :params [{:fields [:id]}]) ; lists all product IDs
; ```
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
  :input-spec :degree9.shopify/id
  :spec :degree9.shopify.products.product/product))

; Create a product
;
; Accepts a valid :degree9.shopify.products.product/product object with a title.
; Any missing values will have defaults merged in by Shopify.
; Returns the newly created product.
;
; Requires additional app permissions to be set.
;
; # Examples
;
; ```
; (create!) ; 400 error, need to provide a title!
; (create! :params [{:body_html "foo"}] ; 422 error, need to provide a title!
; ; creates and returns new product with title "foo"
; (create! :params [{:title "foo"}])
; ; creates and returns new product with title "foo" and body "bar"
; (create! :params [{:title "foo" :body_html "bar"}])
; ```
;
; # References
;
; -  https://help.shopify.com/en/api/reference/products/product#create
;
(def create!
 (partial
  degree9.shopify.core/api!
  :endpoint "product.create"
  :input-spec :degree9.shopify.products.product/product
  :spec :degree9.shopify.products.product/product))

; Update a product by ID
;
; Accepts an ID and valid :degree9.shopify.products.product/product object.
;
; # Examples
;
; ```
; (update! :params [1393014931499 {:title "bar"}]) ; updates and returns product
; ```
;
; # References
;
; - https://help.shopify.com/en/api/reference/products/product#update
;
(def update!
 (partial
  degree9.shopify.core/api!
  :endpoint "product.update"
  :input-spec [:degree9.shopify/id :degree9.shopify.products.product/product]
  :spec :degree9.shopify.products.product/product))

; Delete a product by ID
;
; # Examples
;
; ```
; (delete! :params [1393013522475]) ; deletes product and returns `{}`
; ```
;
; # References
;
; - https://help.shopify.com/en/api/reference/products/product#destroy
;
(def delete!
 (partial
  degree9.shopify.core/api!
  :endpoint "product.delete"
  :input-spec :degree9.shopify/id
  :spec :degree9.shopify/empty-api-response))

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
