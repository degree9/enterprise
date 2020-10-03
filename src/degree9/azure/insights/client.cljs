(ns degree9.azure.insights.client
  "Azure Application Insights Browser Client"
  (:require ["@microsoft/applicationinsights-web" :as insights]
            [goog.object :as obj]))

(def ApplicationInsights (obj/get insights "ApplicationInsights"))

(defn setup [config]
  (ApplicationInsights. (clj->js config)))

(defn start [client]
  (.loadAppInsights client))

(defn init! [config]
  (doto (setup config)
    (start)))
