(ns degree9.shopify.location.core
 (:require
  degree9.shopify.core))

(def locations! (partial degree9.shopify.core/api! "/admin/locations.json"))
