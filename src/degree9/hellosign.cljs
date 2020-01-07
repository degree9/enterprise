(ns degree9.hellosign
  (:require [goog.object :as obj]
            [degree9.env :as env]
            [degree9.multipart-form :as mp]
            [degree9.hellosign.core :as hello]
            [degree9.hellosign.signatures :as sig]
            [degree9.hellosign.embedded :as emb]
            [degree9.hellosign.templates :as tpl]))

;; HelloSign Embedded ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn edit-url [& [opts]]
  (let [conf (merge {:key (env/get "HELLOSIGN_API_KEY")} opts)
        hello (hello/embedded (hello/hellosign conf))]
    (reify Object
      (get [this id & [params]]
        (emb/get-edit-url hello id)))))

(defn sign-url [& [opts]]
  (let [conf (merge {:key (env/get "HELLOSIGN_API_KEY")} opts)
        hello (hello/embedded (hello/hellosign conf))]
    (reify Object
      (get [this id & [params]]
        (emb/get-sign-url hello id)))))
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; HelloSign Template ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn template [& [opts]]
  (let [conf (merge {:key (env/get "HELLOSIGN_API_KEY")} opts)
        hello (hello/template (hello/hellosign conf))]
    (reify Object
      (find [this & [params]]
        (tpl/list-templates hello))
      (get [this id & [params]]
        (tpl/get-template hello id))
      (create [this data & [params]]
        (tpl/create-embedded-draft hello data)))))
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; HelloSign Signature Request ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn signature-request [& [opts]]
  (let [conf  (merge {:key (env/get "HELLOSIGN_API_KEY")} opts)
        hello (hello/signature-request (hello/hellosign conf))]
    (reify Object
      (get [this id params]
        (sig/get-signature hello id)))))

(defn embedded-request [& [opts]]
  (let [client (env/get "HELLOSIGN_CLIENT_ID")
        test   (env/get "HELLOSIGN_TEST_MODE")
        conf   (merge {:key (env/get "HELLOSIGN_API_KEY")} opts)
        hello  (hello/signature-request (hello/hellosign conf))]
    (reify Object
      (create [this data params]
        (let [data (merge (js->clj data) {:clientId client :test_mode test})]
          (if (or (:file data) (:file_url data))
            (sig/create-embedded hello (clj->js data))
            (sig/create-embedded-template hello (clj->js data))))))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; HelloSign Callback ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn hellosign-callback [& opts]
  (reify Object
    (create [this data params]
      (js/Promise. (fn [resolve reject] (resolve "Hello API Event Received"))))))

(defn hellosign-json []
  (fn [hook]
    (let [params (obj/get hook "params")]
      (if-let [multi (obj/get params "multipart")]
        (if-let [json (obj/get multi "json")]
          (obj/set hook "data" (.parse js/JSON json))
          hook)
        hook))))

(defn callback [app]
  (mp/multipart-none app "/hellosign/callback"
    (hellosign-callback)
    {:before {:create [(hellosign-json)]}}))
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
