(ns degree9.azure.event-hubs
  (:require [goog.object :as obj]
            [degree9.env :as env]
            ["@azure/event-hubs" :as event]))

;; Azure Event Hubs ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(def event-hubs (obj/get event "EventHubClient"))

(defn connection-string [conn eventhub]
  (.createFromConnectionString event-hubs conn eventhub))

(defn send! [client message]
  (.send client message))
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; Azure Event Hubs Service ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn EventHub [eventhub & [opts]]
  (let [endpoint (:endpoint opts (env/get "AZURE_EVENTHUBS_CONNECTION_STRING"))
        ehubs    (connection-string endpoint eventhub)]
    (reify
      Object
      (find [this params]
        (send! ehubs (clj->js {:body {:type "find"   :id nil :data nil}  :contentType "application/json"})))
      (get [this id params]
        (send! ehubs (clj->js {:body {:type "get"    :id id  :data nil}  :contentType "application/json"})))
      (create [this data params]
        (send! ehubs (clj->js {:body {:type "create" :id nil :data data} :contentType "application/json"})))
      (update [this id data params]
        (send! ehubs (clj->js {:body {:type "update" :id id  :data data} :contentType "application/json"})))
      (patch [this id data params]
        (send! ehubs (clj->js {:body {:type "patch"  :id id  :data data} :contentType "application/json"})))
      (remove [this id params]
        (send! ehubs (clj->js {:body {:type "remove" :id id  :data nil}  :contentType "application/json"}))))))
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
