(ns degree9.azure.event-hubs
  (:require [goog.object :as obj]
            [degree9.env :as env]
            ["@azure/event-hubs" :as event]))

;; Azure Service Bus ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(def producer-client (obj/get event "EventHubsProducerClient"))

(def consumer-client (obj/get event "EventHubConsumerClient"))
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; Azure Service Bus Queue Service ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn Producer [eventhub & [opts]]
  (let [endpoint (:endpoint opts (env/get "AZURE_EVENTHUBS_CONNECTION_STRING"))
        producer (producer-client. endpoint eventhub)]
    (reify
      Object
      (create [this data params]
        (let [batch (.createBatch producer)]
          (.tryAdd batch (clj->js {:body data}))
          (.sendBatch producer batch))))))

(defn Consumer [& [opts]]
  (let [endpoint (:endpoint opts (env/get "AZURE_EVENTHUBS_CONNECTION_STRING"))
        group (:group opts)
        consumer (consumer-client. group endpoint eventhub)]
    (reify
      Object
      (create [this data params]))))
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
