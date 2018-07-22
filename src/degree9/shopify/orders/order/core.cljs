; implements the REST API for the Order resource
; https://help.shopify.com/en/api/reference/orders/order
(ns degree9.shopify.orders.order.core
 (:require
  degree9.shopify.core
  degree9.shopify.orders.order.spec))

; List all orders
;
; Accepts many parameters for filtering, see Shopify docs
;
; # Examples
;
; ```
; (list!) ; lists all orders
; ```
;
; # References
;
; - https://help.shopify.com/en/api/reference/orders/order#index
;
(def list!
 (partial
  degree9.shopify.core/api!
  :endpoint "order.list"
  :spec :degree9.shopify.orders.order/orders))
