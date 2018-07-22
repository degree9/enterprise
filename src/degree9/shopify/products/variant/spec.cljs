(ns degree9.shopify.products.variant.spec
 (:require
  degree9.shopify.spec
  [cljs.spec.alpha :as spec]))

(spec/def :degree9.shopify.products.variant/inventory_management string?)
(spec/def :degree9.shopify.products.variant/barcode string?)
(spec/def :degree9.shopify.products.variant/product_id :degree9.shopify/id)
(spec/def :degree9.shopify.products.variant/inventory_policy string?)
(spec/def :degree9.shopify.products.variant/inventory_quantity int?)
(spec/def :degree9.shopify.products.variant/old_inventory_quantity :degree9.shopify.products.variant/inventory_quantity)
(spec/def :degree9.shopify.products.variant/weight_unit string?)
(spec/def :degree9.shopify.products.variant/title string?)
(spec/def :degree9.shopify.products.variant/grams number?)
(spec/def :degree9.shopify.products.variant/sku string?)
(spec/def :degree9.shopify.products.variant/fulfillment_service string?)
(spec/def :degree9.shopify.products.variant/compare_at_price (spec/nilable :degree9.shopify/price))
(spec/def :degree9.shopify.products.variant/weight number?)
(spec/def :degree9.shopify.products.variant/inventory_item_id :degree9.shopify/id)
(spec/def :degree9.shopify.products.variant/position int)
(spec/def :degree9.shopify.products.variant/option1 (spec/nilable string?))
(spec/def :degree9.shopify.products.variant/option2 (spec/nilable string?))
(spec/def :degree9.shopify.products.variant/option3 (spec/nilable string?))
(spec/def :degree9.shopify.products.variant/taxable boolean?)
(spec/def :degree9.shopify.products.variant/image_id (spec/nilable :degree9.shopify/id))
(spec/def :degree9.shopify.products.variant/requires_shipping boolean?)

(spec/def :degree9.shopify.products.variant/variant
 (spec/keys
  ; keys are optional to support `fields` filter in API calls
  :opt-un
  [:degree9.shopify/admin_graphql_api_id
   :degree9.shopify/created_at
   :degree9.shopify/updated_at
   :degree9.shopify/price
   :degree9.shopify/title

   :degree9.shopify.products.variant/product_id
   :degree9.shopify.products.variant/inventory_management
   :degree9.shopify.products.variant/barcode
   :degree9.shopify.products.variant/inventory_policy
   :degree9.shopify.products.variant/inventory_quantity
   :degree9.shopify.products.variant/old_inventory_quantity
   :degree9.shopify.products.variant/weight_unit
   :degree9.shopify.products.variant/grams
   :degree9.shopify.products.variant/sku
   :degree9.shopify.products.variant/fulfillment_service
   :degree9.shopify.products.variant/compare_at_price
   :degree9.shopify.products.variant/weight
   :degree9.shopify.products.variant/inventory_item_id
   :degree9.shopify.products.variant/position
   :degree9.shopify.products.variant/option1
   :degree9.shopify.products.variant/option2
   :degree9.shopify.products.variant/option3
   :degree9.shopify.products.variant/taxable
   :degree9.shopify.products.variant/image_id
   :degree9.shopify.products.variant/requires_shipping]))

(spec/def :degree9.shopify.products.variant/variants
 (spec/coll-of :degree9.shopify.products.variant/variant))
