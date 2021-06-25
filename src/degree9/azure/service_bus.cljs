(ns degree9.azure.service-bus
  (:require ["@azure/service-bus" :as sbus]
            [degree9.object :as obj]))

; ;; Service Bus Receive Mode ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; (def receive-mode (obj/get sbus "ReceiveMode"))
;
; (def peek-lock (.-peekLock receive-mode))
;
; (def receive-delete (.-receiveAndDelete receive-mode))
; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; Azure Service Bus ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn service-bus [conn]
  (sbus/ServiceBusClient. conn))

(defn sender [sbus name]
  (.createSender sbus name))

(defn send-messages [sender message]
  (.sendMessages sender message))

(defn subscription-receiver [sbus topic subscription]
  (.createReceiver sbus topic subscription))

(defn queue-receiver [client queue]
  (.createReceiver client queue))

; (defn register-message-handler [client msg err & [opts]]
;   (.registerMessageHandler client msg err opts))

; (defn- message-handler [service]
;   (fn [msg]
;     (let [{:keys [body]} (js->clj msg :keywordize-keys true)]
;       (.emit service (:type body) (dissoc body :type)))))
;
; (defn- error-handler [this]
;   (fn [err]
;     (throw (js/Error. err))))
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; Azure Service Bus Queue Service ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; (defn queue [queue & [opts]]
;   (let [endpoint (:endpoint opts (env/get "AZURE_SERVICEBUS_CONNECTION_STRING"))
;         mode (:receive-mode opts peek-lock)
;         sbus (queue-client (connection-string endpoint) queue)
;         opts (select-keys opts [:autoComplete :maxConcurrentCalls :maxMessageAutoRenewLockDurationInSeconds])
;         sender (sender sbus)
;         receiver (receiver sbus mode)]
;     (reify
;       Object
;       (setup [this app path]
;         (obj/set this "sender" sender)
;         (obj/set this "receiver" receiver)
;         (register-message-handler receiver (message-handler this) (error-handler this) (clj->js opts)))
;       (find [this params]
;         (send! sender (clj->js {:body {:type "find"   :id nil :data nil}  :contentType "application/json"})))
;       (get [this id params]
;         (send! sender (clj->js {:body {:type "get"    :id id  :data nil}  :contentType "application/json"})))
;       (create [this data params]
;         (send! sender (clj->js {:body {:type "create" :id nil :data data} :contentType "application/json"})))
;       (update [this id data params]
;         (send! sender (clj->js {:body {:type "update" :id id  :data data} :contentType "application/json"})))
;       (patch [this id data params]
;         (send! sender (clj->js {:body {:type "patch"  :id id  :data data} :contentType "application/json"})))
;       (remove [this id params]
;         (send! sender (clj->js {:body {:type "remove" :id id  :data nil}  :contentType "application/json"}))))))
; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

; ;; Azure Service Bus Topic Service ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; (defn topic [topic & [opts]]
;   (let [endpoint (:endpoint opts (env/get "AZURE_SERVICEBUS_CONNECTION_STRING"))
;         sbus (topic-client (connection-string endpoint) topic)
;         sender (sender sbus)]
;     (reify
;       Object
;       (setup [this app path]
;         (obj/set this "sender" sender))
;       (find [this params]
;         (send! sender (clj->js {:body {:type "find"   :id nil :data nil}  :contentType "application/json"})))
;       (get [this id params]
;         (send! sender (clj->js {:body {:type "get"    :id id  :data nil}  :contentType "application/json"})))
;       (create [this data params]
;         (send! sender (clj->js {:body {:type "create" :id nil :data data} :contentType "application/json"})))
;       (update [this id data params]
;         (send! sender (clj->js {:body {:type "update" :id id  :data data} :contentType "application/json"})))
;       (patch [this id data params]
;         (send! sender (clj->js {:body {:type "patch"  :id id  :data data} :contentType "application/json"})))
;       (remove [this id params]
;         (send! sender (clj->js {:body {:type "remove" :id id  :data nil}  :contentType "application/json"}))))))
; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

; ;; Azure Service Bus Topic Service ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; (defn subscription [topic & [opts]]
;   (let [endpoint (:endpoint opts (env/get "AZURE_SERVICEBUS_CONNECTION_STRING"))
;         mode (:receive-mode opts peek-lock)
;         sbus (subscription-client (connection-string endpoint) topic)
;         receiver (receiver sbus mode)]
;     (reify
;       Object
;       (setup [this app path]
;         (obj/set this "receiver" receiver)
;         (register-message-handler receiver (message-handler this) (error-handler this) (clj->js opts))))))
; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
