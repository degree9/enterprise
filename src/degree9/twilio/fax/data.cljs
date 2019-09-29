(ns degree9.twilio.fax.data)

(defn fax-instance->clj
 "
 turn a twilio FaxInstance into some clj data
 https://www.twilio.com/docs/fax/api/fax-resource#fax-properties
 "
 [fax-instance]
 {:sid (.-sid fax-instance)
  :account-sid (.-accountSid fax-instance)
  :from (.-from fax-instance)
  :to (.-to fax-instance)
  :quality (.-quality fax-instance)
  :media-sid (.-mediaSid fax-instance)
  :media-url (.-medialUrl fax-instance)
  :num-pages (.-numPages fax-instance)
  :duration (.-duration fax-instance)
  :status (.-status fax-instance)
  :direction (.-direction fax-instance)
  :api-version (.-apiVersion fax-instance)
  :price (.-price fax-instance)
  :price-unit (.-priceUnit fax-instance)
  :date-created (.-dateCreated fax-instance)
  :date-updated (.-dateUpdated fax-instance)
  :links (js->clj (.-links fax-instance))
  :url (.-url fax-instance)})
