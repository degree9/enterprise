(ns degree9.shopify.location.data)

(def location-response-example
 {:location
  {:admin_graphql_api_id "gid://shopify/Location/13854933035"
   :province_code nil
   :phone ""
   :name "Test location A"
   :city ""
   :address1 ""
   :updated_at "2018-07-07T05:29:24-04:00"
   :country_code "AF"
   :country_name "Afghanistan"
   :address2 ""
   :zip ""
   :id 13854933035
   :legacy false
   :province ""
   :country "AF"
   :created_at "2018-07-07T05:29:24-04:00"}})

(def locations-response-example
 {:locations
  [{:admin_graphql_api_id "gid://shopify/Location/13854933035"
    :province_code nil
    :phone ""
    :name "Test location A"
    :city ""
    :address1 ""
    :updated_at "2018-07-07T05:29:24-04:00"
    :country_code "AF"
    :country_name "Afghanistan"
    :address2 ""
    :zip ""
    :id 13854933035
    :legacy false
    :province ""
    :country "AF"
    :created_at "2018-07-07T05:29:24-04:00"}
   {:admin_graphql_api_id "gid://shopify/Location/13854965803"
    :province_code nil
    :phone ""
    :name "Test location B"
    :city ""
    :address1 ""
    :updated_at "2018-07-07T05:29:40-04:00"
    :country_code "AF"
    :country_name "Afghanistan"
    :address2 ""
    :zip ""
    :id 13854965803
    :legacy false
    :province ""
    :country "AF"
    :created_at "2018-07-07T05:29:40-04:00"}]})

(def count-response-example
 {:count 3})

(def inventory-levels-response-example
 {:inventory_levels []})
