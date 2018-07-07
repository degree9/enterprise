(ns degree9.shopify.auth.core
 (:require
  degree9.shopify.auth.spec
  degree9.shopify.auth.data
  degree9.shopify.url.core
  [cljs.spec.alpha :as spec]
  [cljs.test :refer-macros [deftest is]]))

(defn auth?
 "True if the passed value is valid auth credentials"
 [maybe-auth]
 (spec/valid? :degree9.shopify.auth/credentials maybe-auth))

(defn username-password->auth
 "Given a username and password, returns auth credentials"
 [username password]
 {:post [(auth? %)]}
 {:degree9.shopify.auth/username username
  :degree9.shopify.auth/password password})

; default auth using the creds in data
(def default-auth
 (partial username-password->auth
  degree9.shopify.auth.data/api-key
  degree9.shopify.auth.data/api-secret))

(defn with-url-auth
 "Given a URL and auth credentials, returns a URL with auth"
 [url auth]
 {:pre [(degree9.shopify.url.core/url? url)
        (auth? auth)]}
 (-> url
  (assoc :username (:degree9.shopify.auth/username auth))
  (assoc :password (:degree9.shopify.auth/password auth))))

; TESTS

(deftest ??auth?
 (is (auth? {:degree9.shopify.auth/username "foo" :degree9.shopify.auth/password "bar"}))
 (is (not (auth? {:username "foo" :password "bar"})))
 (is (not (auth? {:foo "foo" :bar "bar"}))))

(deftest ??username-password->auth
 (let [expected {:degree9.shopify.auth/username "foo"
                 :degree9.shopify.auth/password "bar"}]
  (is (= expected (username-password->auth "foo" "bar")))))

(deftest ??default-auth
 (let [expected {:degree9.shopify.auth/username degree9.shopify.auth.data/api-key
                 :degree9.shopify.auth/password degree9.shopify.auth.data/api-secret}]
  (is (= expected (default-auth)))))

(deftest ??with-url-auth
 (let [url (degree9.shopify.url.core/endpoint->url "foo.json")
       auth {:degree9.shopify.auth/username "bar"
             :degree9.shopify.auth/password "baz"}
       expected "https://bar:baz@cannabit-dev.myshopify.com/foo.json"]
  (is (= expected (str (with-url-auth url auth))))))
