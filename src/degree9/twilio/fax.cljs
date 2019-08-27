(ns degree9.twilio.fax
 (:require
  degree9.twilio.core
  degree9.twilio.fax.data
  ; degree9.twilio.fax.spec
  [cljs.spec.alpha :as spec]))

(defn lib [] (.-fax (degree9.twilio.core/client!)))

(defn faxes
 "normalises the inconsistent interface to the faxes data"
 ([] (.-faxes (lib)))
 ([fax-id] (.faxes (lib) fax-id)))

(defn fax-instance->clj
 "turn a twilio FaxInstance into some clj data"
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
  :links (.-links fax-instance)
  :url (.-url fax-instance)})

(defn list!
 []
 (.list (faxes)))

(defn fetch!
 [id]
 (.fetch
  (faxes id)))

(defn fax!
 [fax]
 ; {:pre (spec/valid? :degree9.twilio.fax.spec/fax-request fax)}
 (.create
  (faxes)
  (clj->js fax)))

(defn test!
 []
 (.then
  (degree9.twilio.fax/fax! (degree9.twilio.fax.data/test-fax-request))
  (fn [fax-instance]
   (prn "foo")
   (let [fax-instance (fax-instance->clj fax-instance)]))))
    ; (prn "bar" (spec/valid? :degree9.twilio.fax.spec/fax-instance fax-instance))
    ; (prn "foo" fax-instance)))))
