(ns degree9.browser.indexed-db
  (:require [javelin.core :as j]
            [degree9.object :as obj]
            [degree9.browser :as bom]
            [degree9.browser.indexed-db.factory :as factory]
            [degree9.browser.indexed-db.opendbrequest :as oreq]
            [degree9.browser.indexed-db.objectstore :as ostore]
            [degree9.browser.indexed-db.database :as db]
            [degree9.browser.indexed-db.transaction :as tx])
  (:require-macros hoplon.indexed-db))

(defn open-database
  ([name] (factory/open (bom/indexed-db) name))
  ([name version] (factory/open (bom/indexed-db) name version)))

(defn create-stores [db stores]
  (let [current (db/objectStoreNames db)]
    (doseq [store stores]
      (when-not (.contains current store)
        (db/createObjectStore db store)))))

(defn delete-stores [db stores]
  (let [current (db/objectStoreNames db)]
    (doseq [store current]
      (when-not (contains? (set stores) store)
        (db/deleteObjectStore db store)))))

(defn when-success [tx callback]
  (tx/onsuccess tx callback))

(defn when-error [tx callback]
  (tx/onerror tx callback))

(defn when-upgrading [tx callback]
  (tx/onupgradeneeded tx callback))
