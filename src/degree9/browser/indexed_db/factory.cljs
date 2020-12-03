(ns degree9.browser.indexed-db.factory)

(defprotocol IDBFactory
  (open [this name] [this name version] "Open the connection to indexedDB database.")
  (deleteDatabase [this name] "Delete an indexedDB database.")
  (databases [this] "List all available databases, including name and version."))

(extend-type js/IDBFactory
  IDBFactory
  (open
    ([this name]
     (.open this name))
    ([this name version]
     (.open this name version)))
  (deleteDatabase [this name]
    (.deleteDatabase this name))
  (databases [this]
    (.deleteDatabase this)))
