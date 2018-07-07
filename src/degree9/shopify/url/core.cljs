(ns degree9.shopify.url.core
 (:require
  cemerick.url
  degree9.shopify.url.data))

(defn url?
 [maybe-url]
 (instance? cemerick.url/URL maybe-url))

(defn endpoint->url
 [endpoint]
 {:pre [(or (string? endpoint) (url? endpoint))]
  :post [(url? %)]}
 (->
  (cemerick.url/url endpoint)
  (assoc :protocol degree9.shopify.url.data/protocol)
  (assoc :host degree9.shopify.url.data/host)))
