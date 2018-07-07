(ns degree9.shopify.url
 (:require
  cemerick.url
  degree9.shopify.data))

(defn url?
 [maybe-url]
 (instance? cemerick.url/URL maybe-url))

(defn endpoint->url
 [endpoint]
 {:pre [(or (string? endpoint) (url? endpoint))]
  :post [(url? %)]}
 (->
  (cemerick.url/url endpoint)
  (assoc :protocol degree9.shopify.data/protocol)
  (assoc :host degree9.shopify.data/host)))
