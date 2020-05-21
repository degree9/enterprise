(ns degree9.paysafe.core
  (:refer-clojure :exclude [get])
  (:require [degree9.env :as env]
            [degree9.request :as req]))

(defn- json->clj [res]
  (js->clj (.json res) :keywordize-keys true))

(defn- paysafe-url [path]
  (str (env/get "PAYSAFE_API_URL" "http://api.test.paysafe.com") path))

(defn post [path data & [{:or {} :as opts}]]
  (-> (req/post (paysafe-url path) data opts)
    (.then json->clj)))

(defn get [path & [{:or {} :as opts}]]
  (-> (req/get (paysafe-url path) opts)
    (.then json->clj)))

(defn delete [path & [{:or {} :as opts}]]
  (-> (req/delete (paysafe-url path) opts)
    (.then json->clj)))

(defn put [path data & [{:or {} :as opts}]]
  (-> (req/put (paysafe-url path) data opts)
    (.then json->clj)))

(defn patch [path data & [{:or {} :as opts}]]
  (-> (req/patch (paysafe-url path) data opts)
    (.then json->clj)))
