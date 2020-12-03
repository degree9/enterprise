(ns degree9.storage
  (:require [javelin.core :as j]
            [degree9.object :as obj]
            [degree9.browser.window :as win]
            [degree9.browser.storage :as store]
            [degree9.events :as events]))

(defn local-storage [key & [default]]
  (let [local (store/local-storage)
        store (j/cell (store/get local key default))]
    (j/with-let [store= (j/cell= store (partial store/assoc local key))]
      (win/listen :storage
        (fn [event]
          (when (and (= (:key event) key)
                     (= (:storageArea event) local))
            (reset! store (js->clj (:newValue event)))))))))

(defn session-storage [key & [default]]
  (let [session (store/session-storage)
        store (j/cell (store/get session key default))]
    (j/with-let [store= (j/cell= store (partial store/assoc session key))]
      (win/listen :storage
        (fn [event]
          (when (and (= (:key event) key)
                     (= (:storageArea event) session))
            (reset! store (js->clj (:newValue event)))))))))
