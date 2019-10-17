(ns degree9.twilio.fax.api)

#?(:node
   (defn -lib [client] (.-fax client)))

#?(:node
   (defn -faxes
    "normalises the inconsistent interface to the faxes data"
    ([client] (.-faxes (-lib client)))
    ([client fax-id] (.faxes (-lib client) fax-id))))

#?(:node
   (defn list!
    "list all faxes"
    [client]
    (.list (-faxes client))))

#?(:node
   (defn fetch!
    "get a fax by ID"
    [client id]
    (.fetch
     (-faxes client id))))

#?(:node
   (defn fax!
    "send a fax"
    [client fax]
    (.create
     (-faxes client)
     (clj->js fax))))
