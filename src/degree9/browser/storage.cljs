(ns degree9.browser.storage
  (:refer-clojure :exclude [key get assoc dissoc empty]))

(defprotocol IStorage
  "Interface for interacting with Web Storage API."
  (key         [this index]     "Returns name of nth key.")
  (get-item    [this key]       "Return keys value or null.")
  (set-item    [this key value] "Adds key to storage or updates it's value.")
  (remove-item [this key]       "Removes keys from storage if it exists.")
  (clear       [this]           "Clears all keys from storage."))

(extend-protocol IStorage
  js/Storage
  (key         [this index]       (.key this index))
  (get-item    [this index]       (.getItem this index))
  (set-item    [this index value] (.setItem this index value))
  (remove-item [this index]       (.removeItem this index))
  (clear       [this]             (.clear this)))

(defn get [store key & [default]]
  (or (js->clj (get-item store (name key))) default))

(defn assoc [store key value]
  (set-item store (name key) (clj->js value)))

(defn dissoc [store key]
  (remove-item store (name key)))

(defn empty [store]
  (clear store))
