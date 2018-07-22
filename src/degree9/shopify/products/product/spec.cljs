(ns degree9.shopify.products.product.spec
 (:require
  degree9.shopify.products.variant.spec
  degree9.shopify.spec
  [cljs.spec.alpha :as spec]))

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

   :degree9.shopify.products.variant/product_id
   :degree9.shopify.products.product.option/name
   :degree9.shopify.products.product.option/values]))
(spec/def :degree9.shopify.products.product.options/options
 (spec/coll-of
  :degree9.shopify.products.product.options/option))

; @TODO what does an image look like?
(spec/def :degree9.shopify.products.product/image (spec/nilable any?))

(spec/def :degree9.shopify.products.product/vendor string?)
(spec/def :degree9.shopify.products.product/tags string?)
(spec/def :degree9.shopify.products.product/images
 (spec/coll-of :degree9.shopify.products.product/image))
(spec/def :degree9.shopify.products.product/product_type string?)
(spec/def :degree9.shopify.products.product/handle string?)
(spec/def :degree9.shopify.products.product/body_html string?)
(spec/def :degree9.shopify.products.product/template_suffix (spec/nilable string?))
(spec/def :degree9.shopify.products.product/published_scope string?)

(spec/def :degree9.shopify.products.product/product
 (spec/keys
  ; keys are optional to support `fields` filter in api calls
  :opt-un
  [:degree9.shopify/admin_graphql_api_id
   :degree9.shopify/title
   :degree9.shopify/created_at
   :degree9.shopify/updated_at
   :degree9.shopify/published_at
   :degree9.shopify/id

   :degree9.shopify.products.variant/variants

   :degree9.shopify.products.product/vendor
   :degree9.shopify.products.product/tags
   :degree9.shopify.products.product/images
   :degree9.shopify.products.product/image
   :degree9.shopify.products.product/handle
   :degree9.shopify.products.product/body_html
   :degree9.shopify.products.product.options/options
   :degree9.shopify.products.product/template_suffix
   :degree9.shopify.products.product/published_scope]))
