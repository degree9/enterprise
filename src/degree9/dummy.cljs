(ns degree9.dummy
  "Provides a stub service that returns static JSON."
  (:require
    [degree9.debug :as dbg]
    [meta.server :as server]
    [meta.promise :as prom]))

(dbg/defdebug debug "degree9:enterprise:dummy")

(defn Dummy [& [data]]
  (let []
    (reify
      Object
      (id [this] "id")
      (find [this params]
        (prom/with-callback callback
          (callback nil (clj->js data))))
      (get [this id params]
        (prom/with-callback callback
          (let [data (get (zipmap (map :id data) data) id)]
            (callback nil (clj->js data)))))
      (create [this data params]
        (prom/with-callback callback
          (callback nil (clj->js data))))
      (update [this id data params]
        (prom/with-callback callback
          (callback nil (clj->js data))))
      (patch [this id data params]
        (prom/with-callback callback
          (callback nil (clj->js data))))
      (remove [this id params]
        (prom/with-callback callback
          (callback nil (clj->js data)))))))

(defn api
  "Mount a dummy service endpoint."
  [app path data hooks]
  (debug "Mount dummy service at %s" path)
  (server/api app path (Dummy data) hooks))
