(ns degree9.shopify.location.core
 (:require
  degree9.shopify.core
  degree9.shopify.location.spec
  degree9.shopify.location.data
  [cljs.spec.alpha :as spec]
  [cljs.test :refer-macros [deftest is]]))

(defn location?
 [maybe-location]
 (spec/valid?
  :degree9.shopify.location/location
  maybe-location))

(defn locations?
 [maybe-locations]
 (spec/valid?
  :degree9.shopify.location/locations
  maybe-locations))

(def locations! (partial degree9.shopify.core/api! "/admin/locations.json"))

; TESTS

(deftest ??location?
 (is (-> degree9.shopify.location.data/locations-example :locations first location?))
 (is (not (location? {:foo :bar}))))

(deftest ??locations?
 (is (-> degree9.shopify.location.data/locations-example :locations locations?))
 (is (not (locations? [{:foo :bar}]))))
