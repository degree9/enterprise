(ns degree9.azure.service-bus
  (:require [meta.promise :as prom]
            [degree9.azure :as azure]
            [degree9.env :as env]))

;; Azure Service Bus Queues Low API ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn create-queue
  ([sbus queue callback]
   (.createQueueIfNotExists sbus queue callback))
  ([sbus queue opts callback]
   (.createQueueIfNotExists sbus queue (clj->js opts) callback)))

(defn queue-send [sbus message callback]
  (.sendQueueMessage sbus (clj->js message) callback))

(defn queue-receive
  ([sbus callback]
   (.receiveQueueMessage sbus callback))
  ([sbus opts callback
    (.receiveQueueMessage sbus (clj->js opts) callback)]))
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; Azure Service Bus Topics Low API ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn create-topic
  ([sbus topic callback]
   (.createTopicIfNotExists sbus topic callback))
  ([sbus topic opts callback]
   (.createTopicIfNotExists sbus topic (clj->js opts) callback)))

(defn create-subscription [sbus topic sub callback]
   (.createSubscription sbus topic sub callback))

(defn topic-send [sbus message callback]
  (.sendTopicMessage sbus (clj->js message) callback))

(defn subscription-receive
  ([sbus callback]
   (.receiveSubscriptionMessage sbus callback))
  ([sbus opts callback]
   (.receiveSubscriptionMessage sbus (clj->js opts) callback)))
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; Azure Service Bus Queue Service ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn QueuePublisher [queue & [opts]]
  (let [id (:idField opts "id")
        endpoint (:endpoint opts (env/get "AZURE_SERVICEBUS_CONNECTION_STRING"))
        sbus (azure/service-bus endpoint)]
    (reify
      Object
      (id [this] id)
      (setup [this app path]
        (prom/with-callback callback
          (create-queue sbus queue callback)))
      (create [this data params]
        (prom/with-callback callback
          (queue-send sbus {:body data :customProperties params} callback))))))
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; Azure Service Bus Topic Service ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn TopicPublisher [topic & [opts]]
  (let [id (:idField opts "id")
        endpoint (:endpoint opts (env/get "AZURE_SERVICEBUS_CONNECTION_STRING"))
        sbus (azure/service-bus endpoint)]
    (reify
      Object
      (id [this] id)
      (setup [this app path]
        (prom/with-callback callback
          (create-topic sbus topic callback)))
      (create [this data params]
        (prom/with-callback callback
          (topic-send sbus {:body data :customProperties params} callback))))))
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
