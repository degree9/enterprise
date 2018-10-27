(ns degree9.azure.insights
  "Azure Application Insights Node.JS Client"
  (:require ["applicationinsights" :as insights]
            [degree9.env :as env]
            [goog.object :as obj]))

(defn setup
  ([] (.setup insights))
  ([ikey] (.setup insights ikey)))

(defn start [client]
  (.start client))

(defn init!
  ([] (init! (env/get "APPINSIGHTS_INSTRUMENTATIONKEY")))
  ([ikey] (start (setup ikey))))

(defn client []
  (obj/get insights "defaultClient"))

(defn event! [client event]
  (.trackEvent client (clj->js event)))

(defn error! [client error]
  (.trackException client (clj->js error)))

(defn metric! [client value]
  (.trackMetric client (clj->js value)))

(defn trace! [client trace]
  (.trackTrace client (clj->js trace)))

(defn dependency! [client dep]
  (.trackDependency client (clj->js dep)))

(defn request! [client req]
  (.trackRequest client (clj->js req)))

(defn http! [client req res]
  (.trackNodeHttpRequest client
    (clj->js {:request res :response res})))
