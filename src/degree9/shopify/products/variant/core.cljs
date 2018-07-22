; implements REST API for products Variant resource
; https://help.shopify.com/en/api/reference/products/product_variant
(ns degree9.shopify.products.variant.core
 (:require
  degree9.shopify.core))

; List all variants for a single product ID
;
; Accepts pagination and `fields` parameters, see Shopify docs
;
; # Examples
;
; ```
; (list! :params [1370204536875]) ; lists all variants for product 1370204536875
; (list! :params [1370204536875 {:fields [:id]}]) ; lists all variants' ids
; ```
;
; # References
;
; - https://help.shopify.com/en/api/reference/products/product#index
;
(def list!
 (partial
  degree9.shopify.core/api!
  :endpoint "productVariant.list"
  :input-spec :degree9.shopify/id
  :spec :degree9.shopify.products.variant/variants))

; Count all variants for a single product ID
;
; # Examples
;
; ```
; (count! :params [1370204536875]) ; count of variants for product 1370204536875
; ```
;
; # References
;
; - https://help.shopify.com/en/api/reference/products/product_variant#count
;
(def count!
 (partial
  degree9.shopify.core/api!
  :endpoint "productVariant.count"
  :input-spec :degree9.shopify/id
  :spec :degree9.shopify/count))

; Get a single variant by variant ID
;
; Accepts a single `fields` param to filter the values returned.
;
; # Examples
;
; ```
; (get! :params [12891968536619]) ; returns variant 12891968536619
; (get! :params [12891968536619 {:fields [:id]}]) ; returns {:id 12891968536619}
; ```
;
; # References
;
; - https://help.shopify.com/en/api/reference/products/product_variant#show
;
(def get!
 (partial
  degree9.shopify.core/api!
  :endpoint "productVariant.get"
  :input-spec :degree9.shopify/id
  :spec :degree9.shopify.products.variant/variant))
