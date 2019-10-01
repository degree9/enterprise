(ns degree9.microsoft.graph
  (:require [goog.object :as obj]
            ["@microsoft/microsoft-graph-client" :as graph]))

(defn mkclient []
  (let [client (obj/get graph "client")]
    (client.)))

(def client (mkclient))
