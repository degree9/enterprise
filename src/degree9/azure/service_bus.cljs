(ns degree9.azure.service-bus
  (:require [goog.object :as obj]
            [meta.promise :as prom]
            [degree9.env :as env]
            ["@azure/service-bus" :as sbus]))

;; Azure Service Bus ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(def service-bus (obj/get sbus "ServiceBusClient"))

(def receive-mode (obj/get sbus "ReceiveMode"))

(defn connection-string [conn & [opts]]
  (.createFromConnectionString service-bus conn (clj->js opts)))

(defn queue-client [sbus queue]
  (.createQueueClient sbus queue))

(defn topic-client [sbus topic]
  (.createTopicClient sbus topic))

(defn subscription-client [sbus topic]
  (.createSubscriptionClient sbus topic))

(defn sender [client]
  (.createSender client))

(defn send! [sender message]
  (.send sender message))

(defn receiver [client mode]
  (.createReceiver client mode))

(def peek-lock (.-peekLock reveive-mode))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; Azure Service Bus Queue Service ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn QueuePublisher [queue & [opts]]
  (let [endpoint (:endpoint opts (env/get "AZURE_SERVICEBUS_CONNECTION_STRING"))
        sbus (queue-client (connection-string endpoint) queue)
        sender (sender sbus)]
    (reify
      Object
      (create [this data params]
        (send! sender (clj->js {:body data :contentType "application/json"}))))))
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; Azure Service Bus Topic Service ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn TopicPublisher [topic & [opts]]
  (let [endpoint (:endpoint opts (env/get "AZURE_SERVICEBUS_CONNECTION_STRING"))
        sbus (topic-client (connection-string endpoint) topic)
        sender (sender sbus)]
    (reify
      Object
      (create [this data params]
        (send! sender (clj->js {:body data :contentType "application/json"}))))))
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
