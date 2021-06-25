(ns degree9.browser.indexed-db.objectstore)

(defprotocol IDBObjectStore
  (indexNames [this] "List the names of indexes on objects in this object store.")
  (keyPath [this] "The key path of this object store.")
  (name [this] "The name of this object store.")
  (transaction [this] "The IDBTransaction object to which this object store belongs.")
  (autoIncrement [this] "The value of the auto increment flag for this object store.")
  (add [this value] [this value key] "Add new records to an object store.")
  (clear [this] "Delete all current records out of an object store.")
  (count [this] [this query] "Return the total number of records in the store.")
  (createIndex [this indexName keyPath] [this indexName keyPath objectParameters] "Create a new index during a version upgrade.")
  (delete [this key] "Delete individual records out of an object store.")
  (deleteIndex [this indexName] "Destroys the specified index during a version upgrade.")
  (get [this key] "Return specific records from an object store.")
  (getKey [this key] "Return the record key for the object in the object stored matching the specified parameter.")
  (getAll [this] [this query] [this query count] "Returns all objects in the object store matching the specified parameter or all objects.")
  (getAllKeys [this] [this query] [this query count] "Returns all objects in the object store matching the specified parameter or all objects.")
  (index [this name] "Opens an index from this object store.")
  (openCursor [this] "Used for iterating through an object store by primary key with a cursor.")
  (openKeyCursor [this] "Used for iterating through an object store with a key.")
  (put [this] "This is for updating existing records in an object store when the transaction's mode is readwrite."))

(extend-type js/IDBObjectStore
  IDBObjectStore
  (indexNames [this]
    (.-indexNames this))
  (keyPath [this]
    (.-keyPath this))
  (name [this]
    (.-name this))
  (transaction [this]
    (.-transaction this))
  (autoIncrement [this]
    (.-autoIncrement this))
  (add
    ([this value] (.add this value))
    ([this value key] (.add this value key)))
  (clear [this] (.clear this))
  (count
    ([this] (.count this))
    ([this query] (.count this query)))
  (createIndex
    ([this indexName keyPath] (.createIndex this indexName keyPath))
    ([this indexName keyPath objectParameters] (.createIndex this indexName keyPath objectParameters)))
  (delete [this key] (.delete this key))
  (deleteIndex [this indexName] (.deleteIndex this indexName))
  (get [this key] (.get this key))
  (getKey [this key] (.getKey this key))
  (getAll
    ([this] (.getAll this))
    ([this query](.getAll this query))
    ([this query count] (.getAll this query count)))
  (getAllKeys
    ([this] (.getAllKeys this))
    ([this query](.getAllKeys this query))
    ([this query count] (.getAllKeys this query count)))
  (index [this name] (.index this name))
  (openCursor
    ([this] (.openCursor this))
    ([this query] (.openCursor this query))
    ([this query direction] (.openCursor this query direction)))
  (openKeyCursor
    ([this] (.openKeyCursor this))
    ([this query] (.openKeyCursor this query))
    ([this query direction] (.openKeyCursor this query direction)))
  (put
    ([this item] (.put this item))
    ([this item key] (.put this item key))))
