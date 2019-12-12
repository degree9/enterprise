(ns degree9.azure
  (:require ["azure" :as azuresdk]
            [degree9.debug :as dbg]))

(dbg/defdebug debug "degree9:enterprise:azure")

(defn service-bus [& [conn]]
  (.createServiceBusService azuresdk conn))

(defn retry-exponential []
  (.ExponentialRetryPolicyFilter azuresdk))

(defn retry-linear []
  (.LinearRetryPolicyFilter azuresdk))
