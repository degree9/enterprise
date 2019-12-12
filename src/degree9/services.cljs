(ns degree9.services
 (:require
  ["fs" :as fs]
  ["path" :as path]
  [degree9.debug :as dbg]))

(dbg/defdebug debug "degree9:enterprise:services")

(defn entrypoint [app & [opts]]
  (.use app "/:entrypoint"
    (fn [req res next]
      (let [entry (.-entrypoint (.-params req))
            fpath (.resolve path (str "./" entry ".html"))]
        (if (= "index" entry)
          (.redirect res "/")
          (if (.existsSync fs fpath)
            (.sendFile res fpath)
            (next)))))))
