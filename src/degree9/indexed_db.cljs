(ns degree9.indexed-db
  (:require [degree9.browser.indexed-db :as idb]))

(defn get-result [event]
  (:result (:target event)))

(defn get-version [event]
  [(:oldVersion event) (:newVersion event)])

(defn object-store= [req store key]
  (let [idb (j/cell nil)
        ostore (j/cell nil)
        ostore! (partial reset! ostore)]
    (when-success req
      (fn [event]
        (reset! idb (get-result event))
        (let [store (-> @idb (db/transaction store) (tx/objectStore store))]
          (when-success (ostore/get store key)
            (fn [event]
              (let [result (get-result event)]
                (ostore! (js->clj result))))))))
    (j/cell= ostore
      (fn [val]
        (let [store (-> @idb (db/transaction store "readwrite") (tx/objectStore store))]
          (when-success (ostore/put store (clj->js val) key)
            (fn [event]
              (ostore! val))))))))

(defn upgrade-database! [db config]
  (idb/onupgradeneeded db
    (fn [event]
      (let [[old new] (get-version event)
            db (get-result event)]
        (loop [old old new new]
          (when-not (> old new)
            (when-let [stores (get config old)]
              (create-stores db stores)
              (delete-stores db stores))
            (recur (inc old) new)))))))
