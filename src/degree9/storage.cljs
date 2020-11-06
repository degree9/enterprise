(ns degree9.storage
  (:require [javelin.core :as j]
            [degree9.object :as obj]
            [degree9.browser :as bom]
            [degree9.browser.storage :as store]
            [degree9.events :as events]))

(defn local-storage [key & [default]]
  (let [local (bom/local-storage)
        store (j/cell (store/get local key default))]
    (j/with-let [store= (j/cell= store (partial store/assoc local key))]
      (events/listen (bom/get-window) :storage
        (fn [event]
          (when (and (= (:key event) key)
                     (= (:storageArea event) local))
            (reset! store (js->clj (:newValue event)))))))))

(defn session-storage [key & [default]]
  (let [session (bom/session-storage)
        store (j/cell (store/get session key default))]
    (j/with-let [store= (j/cell= store (partial store/assoc session key))]
      (events/listen (bom/get-window) :storage
        (fn [event]
          (when (and (= (:key event) key)
                     (= (:storageArea event) session))
            (reset! store (js->clj (:newValue event)))))))))
