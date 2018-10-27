(ns degree9.azure.insights.client
  "Azure Application Insights JS Client")
  ;;App Insights seems to be broken, dist/ai.js does not export anything
  ;(:require ["applicationinsights-js/dist/ai" :as AppInsights]))

(goog-define APPINSIGHTS_INSTRUMENTATIONKEY "")

(prn AppInsights)

;(defn- insights [config]
;  (insights/Microsoft.ApplicationInsights.Initialization. #js{:config config}))

;(defn- setup [config]
;  (.downloadAndSetup ApplicationInsights config))

(defn init! []
  nil)
;  ([] (init! APPINSIGHTS_INSTRUMENTATIONKEY))
;  ([ikey] (setup #js{:instrumentationKey ikey})))
