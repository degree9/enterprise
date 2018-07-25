(ns degree9.shopify.orders.order.spec
 (:require
  degree9.shopify.spec
  degree9.shopify.discounts.code
  [cljs.spec.alpha :as spec]))

(spec/def :degree9.shopify.orders.order.discount/type string?)
(spec/def :degree9.shopify.orders.order.discount/value string?)
(spec/def :degree9.shopify.orders.order.discount/value_type string?)
(spec/def :degree9.shopify.orders.order.discount/allocation_method string?)
(spec/def :degree9.shopify.orders.order.discount/target_selection string?)
(spec/def :degree9.shopify.orders.order.discount/target_type string?)
(spec/def :degree9.shopify.orders.order.discount/code :degree9.shopify.discounts.discount/code)

(spec/def :degree9.shopify.orders.order.discount/discount
 (spec/keys
  :req-un
  [:degree9.shopify.orders.order.discount/type
   :degree9.shopify.orders.order.discount/value
   :degree9.shopify.orders.order.discount/value_type
   :degree9.shopify.orders.order.discount/allocation_method
   :degree9.shopify.orders.order.discount/target_selection
   :degree9.shopify.orders.order.discount/target_type
   :degree9.shopify.orders.order.discount/code]))

(spec/def :degree9.shopify.orders.order.discount/discounts
 (spec/coll-of
  :degree9.shopify.orders.order.discount/discount))
(spec/def :degree9.shopify.orders.order/discount_applications :degree9.shopify.orders.order.discount/discounts)

(spec/def :degree9.shopify.orders.order/discount_codes :degree9.shopify.orders.order.discount/discounts)

(spec/def :degree9.shopify.orders.order/closed_at (spec/nilable :degree9.shopify/time))
(spec/def :degree9.shopify.orders.order/number nat-int?)
(spec/def :degree9.shopify.orders.order/note (spec/nilable string?))
(spec/def :degree9.shopify.orders.order/token :degree9.shopify/token)
(spec/def :degree9.shopify.orders.order/gateway string?)
(spec/def :degree9.shopify.orders.order/test boolean?)
(spec/def :degree9.shopify.orders.order/total_price :degree9.shopify/monetary_amount)
(spec/def :degree9.shopify.orders.order/subtotal_price :degree9.shopify/monetary_amount)
(spec/def :degree9.shopify.orders.order/total_weight :degree9.shopify/weight)
(spec/def :degree9.shopify.orders.order/total_tax :degree9.shopify/monetary_amount)
(spec/def :degree9.shopify.orders.order/taxes_included boolean?)
(spec/def :degree9.shopify.orders.order/financial_status #{"authorized" "pending" "paid" "refunded" "voided"})
(spec/def :degree9.shopify.orders.order/confirmed boolean?)
(spec/def :degree9.shopify.orders.order/total_discounts :degree9.shopify/monetary_amount)
(spec/def :degree9.shopify.orders.order/total_line_items_price :degree9.shopify/monetary_amount)
(spec/def :degree9.shopify.orders.order/cart_token (spec/nilable :degree9.shopify/token))
(spec/def :degree9.shopify.orders.order/buyer_accepts_marketing boolean?)
(spec/def :degree9.shopify.orders.order/name string?)
(spec/def :degree9.shopify.orders.order/referring_site (spec/nilable :degree9.shopify/url))
(spec/def :degree9.shopify.orders.order/landing_site (spec/nilable :degree9.shopify/url))
(spec/def :degree9.shopify.orders.order/cancelled_at (spec/nilable :degree9.shopify/time))
(spec/def :degree9.shopify.orders.order/cancel_reason (spec/nilable string?))
(spec/def :degree9.shopify.orders.order/total_price_usd :degree9.shopify/monetary_amount)
(spec/def :degree9.shopify.orders.order/checkout_token (spec/nilable :degree9.shopify/token))
(spec/def :degree9.shopify.orders.order/reference (spec/nilable string?))
(spec/def :degree9.shopify.orders.order/user_id :degree9.shopify/id)
(spec/def :degree9.shopify.orders.order/location_id :degree9.shopify/id)
(spec/def :degree9.shopify.orders.order/source_identifier (spec/nilable string?))
(spec/def :degree9.shopify.orders.order/source_url (spec/nilable :degree9.shopify/url))
(spec/def :degree9.shopify.orders.order/processed_at (spec/nilable :degree9.shopify/time))
(spec/def :degree9.shopify.orders.order/device_id (spec/nilable :degree9.shopify/id))
(spec/def :degree9.shopify.orders.order/customer_locale (spec/nilable :degree9.shopify/locale))
(spec/def :degree9.shopify.orders.order/app_id (spec/nilable :degree9.shopify/id))
(spec/def :degree9.shopify.orders.order/browser_ip (spec/nilable :degree9.shopify/ip))
(spec/def :degree9.shopify.orders.order/landing_site_ref (spec/nilable string?))
(spec/def :degree9.shopify.orders.order/order_number :degree9.shopify/id)

(spec/def :degree9.shopify.orders.order/note_attributes
 (spec/coll-of
  :degree9.shopify/attribute))

(spec/def :degree9.shopify.orders.order/payment_gateway_name string?)
(spec/def :degree9.shopify.orders.order/payment_gateway_names
 (spec/coll-of
  :degree9.shopify.orders.order/payment_gateway_name))

(spec/def :degree9.shopify.orders.order/processing_method string?)
(spec/def :degree9.shopify.orders.order/checkout_id (spec/nilable :degree9.shopify/id))
(spec/def :degree9.shopify.orders.order/source_name string?)
(spec/def :degree9.shopify.orders.order/fulfillment_status (spec/nilable #{"shipped" "partial" "unshipped"}))

; tax lines

(spec/def :degree9.shopify.orders.order/price :degree9.shopify/monetary_amount)
(spec/def :degree9.shopify.orders.order/rate number?)
(spec/def :degree9.shopify.orders.order/title :degree9.shopify/title)
(spec/def :degree9.shopify.orders.order/tax_line
 (spec/keys
  :req-un
  [:degree9.shopify.orders.order/price
   :degree9.shopify.orders.order/rate
   :degree9.shopify.orders.order/title]))
(spec/def :degree9.shopify.orders.order/tax_lines
 (spec/coll-of
  :degree9.shopify.orders.order/tax_line))

(spec/def :degree9.shopify.orders.order/contact_email (spec/nilable :degree9.shopify/email))
(spec/def :degree9.shopify.orders.order/order_status_url :degree9.shopify/url)

; line items

(spec/def :degree9.shopify.orders.order/variant_id :degree9.shopify.products.variant/id)
(spec/def :degree9.shopify.orders.order/variant_title (spec/nilable :degree9.shopify.products.variant/title))
(spec/def :degree9.shopify.orders.order/product_id :degree9.shopify/id)
(spec/def :degree9.shopify.orders.order/taxable boolean?)
(spec/def :degree9.shopify.orders.order/gift_card boolean?)
(spec/def :degree9.shopify.orders.order/name :degree9.shopify/title)
(spec/def :degree9.shopify.orders.order/variant_inventory_management string?)
(spec/def :degree9.shopify.orders.order/properties
 (spec/coll-of
  :degree9.shopify/attribute))
(spec/def :degree9.shopify.orders.order/product_exists boolean?)
(spec/def :degree9.shopify.orders.order/fulfillable_quantity :degree9.shopify/quantity)
(spec/def :degree9.shopify.orders.order/grams :degree9.shopify/weight)
(spec/def :degree9.shopify.orders.order/total_discount :degree9.shopify/monetary_amount)
(spec/def :degree9.shopify.orders.order/fulfillment_status (spec/nilable string?))
; @TODO are discount allocations and applications the same thing?
(spec/def :degree9.shopify.orders.order/discount_allocations :degree9.shopify.orders.order/discount_applications)

(spec/def :degree9.shopify.orders.order/line_item
 (spec/keys
  :req-un
  [:degree9.shopify/id
   :degree9.shopify/title
   :degree9.shopify/quantity
   :degree9.shopify/price
   :degree9.shopify/sku
   :degree9.shopify/vendor
   :degree9.shopify/fulfillment_service
   :degree9.shopify/admin_graphql_api_id

   :degree9.shopify.orders.order/variant_id
   :degree9.shopify.orders.order/variant_title
   :degree9.shopify.orders.order/product_id
   :degree9.shopify.orders.order/taxable
   :degree9.shopify.orders.order/gift_card
   :degree9.shopify.orders.order/name
   :degree9.shopify.orders.order/variant_inventory_management
   :degree9.shopify.orders.order/properties
   :degree9.shopify.orders.order/product_exists
   :degree9.shopify.orders.order/fulfillable_quantity
   :degree9.shopify.orders.order/grams
   :degree9.shopify.orders.order/total_discount
   :degree9.shopify.orders.order/fulfillment_status
   :degree9.shopify.orders.order/discount_allocations
   :degree9.shopify.orders.order/tax_lines]))

(spec/def :degree9.shopify.orders.order/line_items
 (spec/coll-of
  :degree9.shopify.orders.order/line_item))

; shipping items

(spec/def :degree9.shopify.orders.order/code string?)
(spec/def :degree9.shopify.orders.order/source string?)
(spec/def :degree9.shopify.orders.order/phone (spec/nilable :degree9.shopify/phone))
(spec/def :degree9.shopify.orders.order/requested_fulfillment_service_id (spec/nilable string?))
(spec/def :degree9.shopify.orders.order/delivery_category (spec/nilable string?))
(spec/def :degree9.shopify.orders.order/carrier_identifier (spec/nilable string?))
(spec/def :degree9.shopify.orders.order/discounted_price :degree9.shopify/price)

(spec/def :degree9.shopify.orders.order/shipping_line
 (spec/keys
  :req-un
  [:degree9.shopify/id
   :degree9.shopify/title
   :degree9.shopify/price

   :degree9.shopify.orders.order/code
   :degree9.shopify.orders.order/source
   :degree9.shopify.orders.order/phone
   :degree9.shopify.orders.order/requested_fulfillment_service_id
   :degree9.shopify.orders.order/delivery_category
   :degree9.shopify.orders.order/carrier_identifier
   :degree9.shopify.orders.order/discounted_price
   :degree9.shopify.orders.order/discount_allocations
   :degree9.shopify.orders.order/tax_lines]))
(spec/def :degree9.shopify.orders.order/shipping_lines
 (spec/coll-of
  :degree9.shopify.orders.order/shipping_line))

(spec/def :degree9.shopify.orders.order/billing_address :degree9.shopify.address/address)
(spec/def :degree9.shopify.orders.order/shipping_address :degree9.shopify.address/address)

(spec/def :degree9.shopify.orders.order/order_id :degree9.shopify/id)
(spec/def :degree9.shopify.orders.order/status #{"open" "closed"})
(spec/def :degree9.shopify.orders.order/service string?)
(spec/def :degree9.shopify.orders.order/tracking_company (spec/nilable string?))
(spec/def :degree9.shopify.orders.order/shipment_status (spec/nilable string?))
(spec/def :degree9.shopify.orders.order/location_id :degree9.shopify/id)
(spec/def :degree9.shopify.orders.order/tracking_number string?)
(spec/def :degree9.shopify.orders.order/tracking_numbers
 (spec/coll-of
  :degree9.shopify.orders.order/tracking_number))
(spec/def :degree9.shopify.orders.order/tracking_url :degree9.shopify/url)
(spec/def :degree9.shopify.orders.order/tracking_urls
 (spec/coll-of
  :degree9.shopify.orders.order/tracking_url))

(spec/def :degree9.shopify.order.order.fulfilment.receipt/testcase boolean?)
(spec/def :degree9.shopify.order.order.fulfilment.receipt/authorization string?)
(spec/def :degree9.shopify.order.order.fulfilment.receipt/receipt
 (spec/keys
  :req-un
  [:degree9.shopify.order.order.receipt/testcase
   :degree9.shopify.order.order.receipt/authorization]))

(spec/def :degree9.shopify.orders.order/fulfillment
 (spec/keys
  :req-un
  [:degree9.shopify/id
   :degree9.shopify/admin_graphql_api_id
   :degree9.shopify/created_at
   :degree9.shopify/updated_at
   :degree9.shopify/client_details
   :degree9.shopify.orders.order/order_id
   :degree9.shopify.orders.order/line_items
   :degree9.shopify.orders.order.fulfilment/status
   :degree9.shopify.orders.order.fulfilment/service
   :degree9.shopify.orders.order.fulfilment/tracking_company
   :degree9.shopify.orders.order.fulfilment/shipment_status
   :degree9.shopify.orders.order.fulfilment/location_id
   :degree9.shopify.orders.order.fulfilment/tracking_number
   :degree9.shopify.orders.order.fulfilment/tracking_numbers
   :degree9.shopify.orders.order.fulfilment/tracking_url
   :degree9.shopify.orders.order.fulfilment/tracking_urls
   :degree9.shopify.order.order.fulfilment.receipt/receipt
   :degree9.shopify.orders.order.fulfilment/name]))

(spec/def :degree9.shopify.orders.order/fulfillments
 (spec/coll-of
  :degree9.shopify.orders.order/fulfillment))

(spec/def :degree9.shopify.orders.order/refund
 (spec/keys
  :req-un
  [:degree9.shopfy/id
   :degree9.shopify/created_at
   :degree9.shopify/created_at
   :degree9.shopify/admin_graphql_api_id
   :degree9.shopify.orders.order/order_id
   :degree9.shopify.orders.order/user_id
   :degree9.shopify.orders.order/processed_at
   :degree9.shopify.orders.order.refund/note
   :degree9.shopify.orders.order.refund/restock
   :degree9.shopify.orders.order.refund/refund_line_items
   :degree9.shopify.orders.order.refund/transactions
   :degree9.shopify.orders.order.refund/order_adjustments]))

(spec/def :degree9.shopify.orders.order/order
 (spec/keys
  ; optional keys to support `fields` filtering
  :opt-un
  [:degree9.shopify/id
   :degree9.shopify/email
   :degree9.shopify/created_at
   :degree9.shopify/updated_at
   :degree9.shopify/currency
   :degree9.shopify/tags
   :degree9.shopify/admin_graphql_api_id

   :degree9.shopify.orders.order/phone
   :degree9.shopify.orders.order/closed_at
   :degree9.shopify.orders.order/number
   :degree9.shopify.orders.order/note
   :degree9.shopify.orders.order/token
   :degree9.shopify.orders.order/gateway
   :degree9.shopify.orders.order/test
   :degree9.shopify.orders.order/total_price
   :degree9.shopify.orders.order/subtotal_price
   :degree9.shopify.orders.order/total_weight
   :degree9.shopify.orders.order/total_tax
   :degree9.shopify.orders.order/taxes_included
   :degree9.shopify.orders.order/financial_status
   :degree9.shopify.orders.order/confirmed
   :degree9.shopify.orders.order/total_discounts
   :degree9.shopify.orders.order/total_line_items_price
   :degree9.shopify.orders.order/cart_token
   :degree9.shopify.orders.order/buyer_accepts_marketing
   :degree9.shopify.orders.order/name
   :degree9.shopify.orders.order/referring_site
   :degree9.shopify.orders.order/landing_site
   :degree9.shopify.orders.order/cancelled_at
   :degree9.shopify.orders.order/cancel_reason
   :degree9.shopify.orders.order/total_price_usd
   :degree9.shopify.orders.order/checkout_token
   :degree9.shopify.orders.order/reference
   :degree9.shopify.orders.order/user_id
   :degree9.shopify.orders.order/location_id
   :degree9.shopify.orders.order/source_identifier
   :degree9.shopify.orders.order/source_url
   :degree9.shopify.orders.order/processed_at
   :degree9.shopify.orders.order/device_id
   :degree9.shopify.orders.order/customer_locale
   :degree9.shopify.orders.order/app_id
   :degree9.shopify.orders.order/browser_ip
   :degree9.shopify.orders.order/landing_site_ref
   :degree9.shopify.orders.order/order_number

   :degree9.shopify.orders.order/discount_applications
   :degree9.shopify.orders.order/discount_codes

   :degree9.shopify.orders.order/note_attributes

   :degree9.shopify.orders.order/payment_gateway_names

   :degree9.shopify.orders.order/processing_method
   :degree9.shopify.orders.order/checkout_id
   :degree9.shopify.orders.order/source_name
   :degree9.shopify.orders.order/fulfillment_status

   :degree9.shopify.orders.order/tax_lines

   :degree9.shopify.orders.order/contact_email
   :degree9.shopify.orders.order/order_status_url

   :degree9.shopify.orders.order/line_items
   :degree9.shopify.orders.order/shipping_lines

   :degree9.shopify.orders.order/billing_address
   :degree9.shopify.orders.order/shipping_address

   :degree9.shopify.orders.order/fulfillments
   :degree9.shopify.orders.order/refunds

   :degree9.shopify.orders.order/payment_details
   :degree9.shopify.orders.order/customer]))

(spec/def :degree9.shopify.orders.order/orders
 (spec/coll-of
  :degree9.shopify.orders.order/order))
