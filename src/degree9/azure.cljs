(ns degree9.azure
  (:require ["azure" :as azuresdk]))

(defn service-bus [& [conn]]
  (.createServiceBusService azuresdk conn))

(defn retry-exponential []
  (.ExponentialRetryPolicyFilter azuresdk))

(defn retry-linear []
  (.LinearRetryPolicyFilter azuresdk))
