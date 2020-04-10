(ns degree9.paysafe.core
  (:refer-clojure :exclude [get])
  (:require [degree9.env :as env]
            [degree9.request :as req]))

(defn- paysafe-url [path]
  (str (env/get "PAYSAFE_API_URL" "http://api.test.paysafe.com") path))

(defn post [path data & [{:or {} :as opts}]]
  (req/post (paysafe-url path) data opts))

(defn get [path data & [{:or {} :as opts}]]
  (req/get (paysafe-url path) data opts))

(defn put [path data & [{:or {} :as opts}]]
  (req/put (paysafe-url path) data opts))
