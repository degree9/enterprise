(ns degree9.hellosign.callback
 (:require
  degree9.multipart-form))

(defn all-signed! [])

(defn hellosign-service [& opts]
  (reify Object
    (create [this data params]
      (let []
       (prn
        (js->clj
         (.parse js/JSON
          (.-json data))
         :keywordize-keys true))
       (prn (js->clj params))
       (js/Promise. (fn [resolve reject] (resolve "Hello API Event Received")))))))

(defn with-callback! [app]
 (degree9.multipart-form/multipart-none
  app
  "/hellosign/callback"
  (hellosign-service)))
