(ns degree9.hellosign
  (:require [goog.object :as obj]
            [degree9.multipart-form :as mp]))


(defn hellosign-service [& opts]
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

(defn hellosign-callback [app]
  (mp/multipart-none app "/hellosign/callback" (hellosign-service) {:before {:create [(hellosign-json)]}}))
