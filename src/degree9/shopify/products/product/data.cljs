(ns degree9.shopify.products.product.data)

(def product-example
 {:vendor "cannabit-dev"
  :tags ""
  :admin_graphql_api_id "gid://shopify/Product/1370204536875"
  :variants [{:admin_graphql_api_id "gid://shopify/ProductVariant/12891968536619"
              :inventory_management "shopify"
              :barcode ""
              :product_id 1370204536875
              :inventory_policy "deny"
              :old_inventory_quantity 50
              :weight_unit "kg"
              :inventory_quantity 50
              :title "Default Title"
              :grams 0
              :sku "TP-A"
              :updated_at "2018-07-07T05:27:35-04:00"
              :fulfillment_service "manual"
              :compare_at_price nil
              :weight 0
              :inventory_item_id 13320318484523
              :id 12891968536619
              :option3 nil
              :position 1
              :option1 "Default Title"
              :option2 nil
              :taxable true
              :price "100.00"
              :image_id nil
              :created_at "2018-07-07T05:26:28-04:00"
              :requires_shipping true}]
  :images []
  :title "Test product A"
  :product_type ""
  :published_at "2018-07-07T05:25:36-04:00"
  :updated_at "2018-07-07T05:31:17-04:00"
  :id 1370204536875
  :handle "test-product-a"
  :body_html "some description"
  :image nil
  :options [{:id 1932968394795
             :product_id 1370204536875
             :name "Title"
             :position 1
             :values ["Default Title"]}]
  :template_suffix nil
  :published_scope "global"
  :created_at "2018-07-07T05:26:28-04:00"})
