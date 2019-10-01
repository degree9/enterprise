(ns degree9.twilio.fax.api
 (:require
  degree9.twilio))

#?(:node
   (defn -lib [] (.-fax (degree9.twilio/client!))))

#?(:node
   (defn -faxes
    "normalises the inconsistent interface to the faxes data"
    ([] (.-faxes (-lib)))
    ([fax-id] (.faxes (-lib) fax-id))))

#?(:node
   (defn list!
    "list all faxes"
    []
    (.list (-faxes))))

#?(:node
   (defn fetch!
    "get a fax by ID"
    [id]
    (.fetch
     (-faxes id))))

#?(:node
   (defn fax!
    "send a fax"
    [fax]
    ; {:pre (spec/valid? :degree9.twilio.fax.spec/fax-request fax)}
    (.create
     (-faxes)
     (clj->js fax))))
