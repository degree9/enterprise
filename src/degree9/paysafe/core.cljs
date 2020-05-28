(ns degree9.paysafe.core
  (:refer-clojure :exclude [get])
  (:require [goog.crypt.base64 :as base64]
            [clojure.string :as cstr]
            [degree9.env :as env]
            [degree9.request :as req]))

(defn- json->clj [res]
  (js->clj (.json res) :keywordize-keys true))

(defn- check-status [res]
  (if (.-ok res) res
    (throw (js/Error. (.-statusText res)))))

(defn- paysafe-url [path & [query]]
  (str (env/get "PAYSAFE_URL" "http://api.test.paysafe.com") path))

(defn- paysafe-headers [headers]
  (let [username (env/get "PAYSAFE_USERNAME")
        password (env/get "PAYSAFE_PASSWORD")
        auth     (base64/encodeString (str username ":" password))]
    (merge {:authorization (str "Basic " auth)} headers)))

(defn- paysafe-request [{:keys [method path data query headers] :as opts}]
  (let [method  (cstr/upper-case (name method))
        headers (paysafe-headers headers)
        url     (paysafe-url path query)]
    (-> (req/fetch url (clj->js {:method method :body data :headers headers}))
        (.then check-status)
        (.then json->clj))))

(defn post [path data & [{:keys [headers] :as opts}]]
  (paysafe-request
    {:method :post
     :path path
     :data data
     :headers headers}))

(defn get [path & [{:keys [headers] :as opts}]]
  (paysafe-request
    {:method :get
     :path path
     :headers headers}))

(defn delete [path & [{:keys [headers] :as opts}]]
  (paysafe-request
    {:method :delete
     :path path
     :headers headers}))

(defn put [path data & [{:keys [headers] :as opts}]]
  (paysafe-request
    {:method :put
     :path path
     :data data
     :headers headers}))

(defn patch [path data & [{:keys [headers] :as opts}]]
  (paysafe-request
    {:method :patch
     :path path
     :data data
     :headers headers}))
