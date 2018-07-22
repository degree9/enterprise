(ns degree9.shopify.products.product.spec
 (:require
  degree9.shopify.spec
  [cljs.spec.alpha :as spec]))

(spec/def :degree9.shopify.products.product/count int?)

(spec/def :degree9.shopify.products.product.variant/inventory_management string?)
(spec/def :degree9.shopify.products.product.variant/barcode string?)
(spec/def :degree9.shopify.products.product.variant/product_id :degree9.shopify/id)
(spec/def :degree9.shopify.products.product.variant/inventory_policy string?)
(spec/def :degree9.shopify.products.product.variant/inventory_quantity int?)
(spec/def :degree9.shopify.products.product.variant/old_inventory_quantity :degree9.shopify.products.product.variant/inventory_quantity)
(spec/def :degree9.shopify.products.product.variant/weight_unit string?)
(spec/def :degree9.shopify.products.product.variant/title string?)
(spec/def :degree9.shopify.products.product.variant/grams number?)
(spec/def :degree9.shopify.products.product.variant/sku string?)
(spec/def :degree9.shopify.products.product.variant/fulfillment_service string?)
(spec/def :degree9.shopify.products.product.variant/compare_at_price (spec/nilable :degree9.shopify/price))
(spec/def :degree9.shopify.products.product.variant/weight number?)
(spec/def :degree9.shopify.products.product.variant/inventory_item_id :degree9.shopify/id)
(spec/def :degree9.shopify.products.product.variant/position int)
(spec/def :degree9.shopify.products.product.variant/option1 (spec/nilable string?))
(spec/def :degree9.shopify.products.product.variant/option2 (spec/nilable string?))
(spec/def :degree9.shopify.products.product.variant/option3 (spec/nilable string?))
(spec/def :degree9.shopify.products.product.variant/taxable boolean?)
(spec/def :degree9.shopify.products.product.variant/image_id (spec/nilable :degree9.shopify/id))
(spec/def :degree9.shopify.products.product.variant/requires_shipping boolean?)

(spec/def :degree9.shopify.products.product.variant/variant
 (spec/keys
  :req-un
  [:degree9.shopify/admin_graphql_api_id
   :degree9.shopify/created_at
   :degree9.shopify/updated_at
   :degree9.shopify/price
   :degree9.shopify/title

   :degree9.shopify.products.product.variant/product_id
   :degree9.shopify.products.product.variant/inventory_management
   :degree9.shopify.products.product.variant/barcode
   :degree9.shopify.products.product.variant/inventory_policy
   :degree9.shopify.products.product.variant/inventory_quantity
   :degree9.shopify.products.product.variant/old_inventory_quantity
   :degree9.shopify.products.product.variant/weight_unit
   :degree9.shopify.products.product.variant/grams
   :degree9.shopify.products.product.variant/sku
   :degree9.shopify.products.product.variant/fulfillment_service
   :degree9.shopify.products.product.variant/compare_at_price
   :degree9.shopify.products.product.variant/weight
   :degree9.shopify.products.product.variant/inventory_item_id
   :degree9.shopify.products.product.variant/position
   :degree9.shopify.products.product.variant/option1
   :degree9.shopify.products.product.variant/option2
   :degree9.shopify.products.product.variant/option3
   :degree9.shopify.products.product.variant/taxable
   :degree9.shopify.products.product.variant/image_id
   :degree9.shopify.products.product.variant/requires_shipping]))

(spec/def :degree9.shopify.products.product.option/name :degree9.shopify/title)
(spec/def :degree9.shopify.products.product.option/value string?)
(spec/def :degree9.shopify.products.product.option/values
 (spec/coll-of
  :degree9.shopify.products.product.option/value))
(spec/def :degree9.shopify.products.product.options/option
 (spec/keys
  :req-un
  [:degree9.shopify/id
   :degree9.shopify/position

   :degree9.shopify.products.product.variant/product_id
   :degree9.shopify.products.product.option/name
   :degree9.shopify.products.product.option/values]))
(spec/def :degree9.shopify.products.product.options/options
 (spec/coll-of
  :degree9.shopify.products.product.options/option))

; @TODO what does an image look like?
(spec/def :degree9.shopify.products.product/image (spec/nilable any?))

(spec/def :degree9.shopify.products.product/vendor string?)
(spec/def :degree9.shopify.products.product/tags string?)
(spec/def :degree9.shopify.products.product/variants
 (spec/coll-of :degree9.shopify.products.product.variant/variant))
(spec/def :degree9.shopify.products.product/images
 (spec/coll-of :degree9.shopify.products.product/image))
(spec/def :degree9.shopify.products.product/product_type string?)
(spec/def :degree9.shopify.products.product/handle string?)
(spec/def :degree9.shopify.products.product/body_html string?)
(spec/def :degree9.shopify.products.product/template_suffix (spec/nilable string?))
(spec/def :degree9.shopify.products.product/published_scope string?)

(spec/def :degree9.shopify.products.product/product
 (spec/keys
  :opt-un
  [:degree9.shopify/admin_graphql_api_id
   :degree9.shopify/title
   :degree9.shopify/created_at
   :degree9.shopify/updated_at
   :degree9.shopify/published_at
   :degree9.shopify/id

   :degree9.shopify.products.product/vendor
   :degree9.shopify.products.product/tags
   :degree9.shopify.products.product.variant/variants
   :degree9.shopify.products.product/images
   :degree9.shopify.products.product/image
   :degree9.shopify.products.product/handle
   :degree9.shopify.products.product/body_html
   :degree9.shopify.products.product.options/options
   :degree9.shopify.products.product/template_suffix
   :degree9.shopify.products.product/published_scope]))
