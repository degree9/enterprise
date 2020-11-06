(ns degree9.app
  (:require ["path" :as path]))

(defn client-routing [app]
  (.use app "*" (fn [req res] (.sendFile res (.resolve path "index.html")))))
