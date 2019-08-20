(ns degree9.twilio.fax
 (:require
  degree9.twilio.core
  degree9.twilio.fax.data
  degree9.twilio.fax.spec
  [cljs.spec.alpha :as spec]))

(defn lib [] (.-fax (degree9.twilio.core/client!)))

(defn faxes
 ([] (.-faxes (lib)))
 ([fax-id] (.faxes (lib) fax-id)))

(defn list!
 []
 (.then
  (.list
   (faxes))
  #(doseq [f (js->clj %)]
    (prn (.-sid f)))))

(defn fetch!
 []
 (.then
  (.fetch
   (faxes
    "FX08e9aeaf57c1b606cef9713fb054385e"))
  #(prn (.-mediaUrl %))))

(defn fax!
 [fax]
 {:pre (spec/valid? :degree9.twilio.fax.spec/fax fax)}
 (prn "foozz" (clj->js fax))
 (.then
   (.create
    (faxes)
    (clj->js fax))
  #(prn "bar" (js->clj %))))

(defn test!
 []
 (degree9.twilio.fax/fax! (degree9.twilio.fax.data/test-fax)))
