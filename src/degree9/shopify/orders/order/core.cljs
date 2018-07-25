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

; Get an order by order ID
;
; Accepts a single optional parameter `fields` to filter returned fields.
;
; # Examples
;
; ```
; (get! :params [639742640171]) ; returns entire order object
; (get! :params [639742640171 {:fields [:created_at]}]) ; returns {:created_at "2018-07-25T04:03:36-04:00"}
; ```
;
; # References
;
; - https://help.shopify.com/en/api/reference/orders/order#show
;
(def get!
 (partial
  degree9.shopify.core/api!
  :endpoint "order.get"
  :input-spec :degree9.shopify/id
  :spec :degree9.shopify.orders.order/order))

; Fetch the total orders count
;
; Accepts several parameters for filtering what is counted, see Shopify docs
;
; # Examples
;
; ```
; (count!) ; count all orders
; (count! :params [{:status "open"}]) ; count all open orders
; ```
;
; # References
;
; - https://help.shopify.com/en/api/reference/orders/order#count
;
(def count!
 (partial
  degree9.shopify.core/api!
  :endpoint "order.count"
  :spec :degree9.shopify/count))
