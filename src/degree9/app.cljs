(ns degree9.app
  (:require ["path" :as path]))

(defn client-routing [app]
  (.get app "*" (fn [req res] (.sendFile res (.resolve path "index.html")))))
