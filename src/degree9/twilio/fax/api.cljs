(ns degree9.twilio.fax.api)

(defn -lib [] (.-fax (degree9.twilio.core/client!)))

(defn -faxes
 "normalises the inconsistent interface to the faxes data"
 ([] (.-faxes (-lib)))
 ([fax-id] (.faxes (-lib) fax-id)))

(defn list!
 "list all faxes"
 []
 (.list (-faxes)))

(defn fetch!
 "get a fax by ID"
 [id]
 (.fetch
  (-faxes id)))

(defn fax!
 "send a fax"
 [fax]
 ; {:pre (spec/valid? :degree9.twilio.fax.spec/fax-request fax)}
 (.create
  (-faxes)
  (clj->js fax)))
