(ns degree9.hellosign.callback
 (:require
  degree9.multipart-form
  degree9.twilio.fax.fixture
  degree9.twilio.fax.api
  degree9.twilio.client
  degree9.hellosign.core))

(defn all-signed! [])

(defn data->parsed [data]
 (js->clj
  (.parse js/JSON
   (.-json data))
  :keywordize-keys true))

(defn parsed->final-copy-url [parsed]
 (str
  "https://"
  (degree9.hellosign.core/api-key)
  ":@api.hellosign.com"
  (:final_copy_uri (:signature_request parsed))))

(defn hellosign-success
 [resolve reject]
 (resolve "Hello API Event Received"))

(defn hellosign-service [& opts]
  (reify Object
    (create [this data params]
      (let [parsed (data->parsed data)]
       (when
        (and
         (-> parsed :event :event_type #{"signature_request_all_signed"})
         (-> parsed :signature_request :has_error not)
         (-> parsed :signature_request :is_complete)
         (-> parsed :signature_request :is_declined not))
        (.then
         (degree9.twilio.fax.api/fax!
          (degree9.twilio.client/client!)
          (degree9.twilio.fax.fixture/simple-fax-request (parsed->final-copy-url parsed)))
         hellosign-success))
       (js/Promise. hellosign-success)))))

(defn with-callback! [app]
 (degree9.multipart-form/multipart-none
  app
  "/hellosign/callback"
  (hellosign-service)))
