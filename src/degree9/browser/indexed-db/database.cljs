(ns degree9.browser.indexed-db.database)

(defprotocol IDBDatabase
  (close [this] "Async closes the connection to a database.")
  (createObjectStore [this name] [this name  opts] "Creates a new object store.")
  (deleteObjectStore [this name] "Delete an object store.")
  (transaction [this stores] [this stores mode] "Returns the transaction object.")
  (objectStoreNames [this] "Returns a list of all existing object stores."))

(extend-type js/IDBDatabase
  IDBDatabase
  (close [this]
    (.close this))
  (createObjectStore
    ([this name]
     (.createObjectStore this name))
    ([this name opts]
     (.createObjectStore this name opts)))
  (deleteObjectStore [this name]
    (.deleteObjectStore this name))
  (transaction
    ([this stores]
     (.transaction this stores))
    ([this stores mode]
     (.transaction this stores mode)))
  (objectStoreNames [this]
    (.-objectStoreNames this)))
