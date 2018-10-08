; implements the REST API for the Order resource
; https://help.shopify.com/en/api/reference/orders/order
(ns degree9.shopify.orders.order.core
 (:require
  degree9.shopify.spec
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
; (get! :params [639742640171]) ; returns entire order
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

; Close an order by ID
;
; Needs order write permissions.
;
; # Examples
;
; ```
; (close! :params [639742640171]) ; returns the closed order
; ```
;
; # References
;
; - https://help.shopify.com/en/api/reference/orders/order#close
;
(def close!
 (partial
  degree9.shopify.core/api!
  :endpoint "order.close"
  :input-spec :degree9.shopify/id
  :spec :degree9.shopify.orders.order/order))

; Open an order by ID
;
; Needs order write permissions.
;
; # Examples
;
; ```
; (open! :params [639742640171]) ; returns the opened order
; ```
;
; # References
;
; - https://help.shopify.com/en/api/reference/orders/order#open
;
(def open!
 (partial
  degree9.shopify.core/api!
  :endpoint "order.open"
  :input-spec :degree9.shopify/id
  :spec :degree9.shopify.orders.order/order))

; Cancel an order by ID
;
; Orders that have a fulfillment object can't be canceled.
; Needs order write permissions.
; Has several params important params, duplicated from docs here.
;
; - `amount`: The amount to refund. If set, Shopify attempts to void or refund
;             the payment, depending on its status.
; - `restock`: Whether to restock refunded items back to your store's inventory.
; - `reason`: The reason for the order cancellation. Valid values: customer,
;             inventory, fraud, declined, and other.
; - `email`: Whether to send an email to the customer notifying them of the
;            cancellation.
; - `refund`: The refund transactions to perform. Required for some more complex
;             refund situations. For more information, see the Refund API.
;
; # Examples
;
; ```
; (cancel! :params [639742640171 {:amount 50}]) ; returns the canceled order
; ```
;
; # References
;
; - https://help.shopify.com/en/api/reference/orders/order#cancel
;
(def cancel!
 (partial
  degree9.shopify.core/api!
  :endpoint "order.cancel"
  :input-spec :degree9.shopify/id
  :spec :degree9.shopify.orders.order/order))

; Create an order
;
; Needs order write permissions.
; At least one line item is required.
; Takes additional params to the order fields.
;
; - `send_receipt`: Whether to send an order confirmation to the customer.
; - `send_fulfillment_receipt`: Whether to send a shipping confirmation to the
;                               customer.
; - `inventory_behaviour`: The behaviour to use when updating inventory.
;                          (default: bypass)
;   - `bypass`: Do not claim inventory.
;   - `decrement_ignoring_policy`: Ignore the product's inventory policy and
;                                  claim amounts no matter what.
;   - `decrement_obeying_policy`: Obey the product's inventory policy.
;
; # Examples
;
; ```
; ; creates and returns an order
; (create! :params [{:line_items [{:variant_id 12891968536619 :quantity 1}]}])
; ```
;
; # References
;
; - https://help.shopify.com/en/api/reference/orders/order#create
;
(def create!
 (partial
  degree9.shopify.core/api!
  :endpoint "order.create"
  :input-spec :degree9.shopify.orders.order/order
  :spec :degree9.shopify.orders.order/order))

; Updates an order by order ID
;
; Needs order write permissions.
;
; # Examples
;
; ```
; ; updates 639784583211 and returns the order with a note
; (update! :params [639784583211 {:note "Hi!"}])
; ```
;
; # References
;
; - https://help.shopify.com/en/api/reference/orders/order#update
;
(def update!
 (partial
  degree9.shopify.core/api!
  :endpoint "order.update"
  :input-spec [:degree9.shopify/id :degree9.shopify.orders.order/order]
  :spec :degree9.shopify.orders.order/order))

; Deletes an order by order ID
;
; Needs order write permissions.
;
; # Examples
;
; ```
; (delete! :params [639784583211]) ; deletes order 639784583211, no return
; ```
;
; # References
;
; - https://help.shopify.com/en/api/reference/orders/order#destroy
;
(def delete!
 (partial
  degree9.shopify.core/api!
  :endpoint "order.delete"
  :input-spec :degree9.shopify/id
  :spec :degree9.shopify/empty-api-response))
