(ns degree9.services
  (:require [cljs.nodejs :as node]
            [meta.server :as svr]))

(def fs (node/require "fs"))
(def path (node/require "path"))

(defn entrypoint [app & [opts]]
  (svr/using app "/:entrypoint"
    (fn [req res next]
      (let [entry (.-entrypoint (.-params req))
            fpath (.resolve path (str "./" entry ".html"))]
        (if (= "index" entry)
          (.redirect res "/")
          (if (.existsSync fs fpath)
            (.sendFile res fpath)
            (next)))))))
