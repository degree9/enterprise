(ns degree9.shopify.url.core
 (:require
  cemerick.url
  degree9.shopify.url.data
  [cljs.test :refer-macros [deftest is]]))

(defn url?
 [maybe-url]
 (instance? cemerick.url/URL maybe-url))

(defn endpoint->url
 [endpoint]
 {:pre [(or (string? endpoint) (url? endpoint))]
  :post [(url? %)]}
 (let [; normalise the root of the endpoint path
       endpoint (if (clojure.string/starts-with? endpoint "/")
                 endpoint
                 (str "/" endpoint))]
  (->
   (cemerick.url/url endpoint)
   (assoc :protocol degree9.shopify.url.data/protocol)
   (assoc :host degree9.shopify.url.data/host))))

; TESTS

(deftest ??url?
 (let [u "https://example.com"]
  (is (url? (cemerick.url/url u)))
  (is (not (url? u)))))

(deftest ??endpoint->url
 (let [; endpoints need to normalise root of path
       endpoints ["foo/bar.json" "/foo/bar.json"]
       expected (cemerick.url/url "https://cannabit-dev.myshopify.com/foo/bar.json")]
  (doseq [endpoint endpoints]
   (is (= expected (endpoint->url endpoint))))))
