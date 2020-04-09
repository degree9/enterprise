(ns degree9.redis.config)



(defn config-get
  "Get the value of a configuration parameter"
  [client parameter]
  (.client-kill client parameter))

(defn config-rewrite
  "Rewrite the configuration file with the in memory configuration"
  [client]
  (.config-rewrite client))

(defn config-set
  "Set a configuration parameter to the given value"
  [client parameter value]
  (.config-set client parameter value))

(defn config-reset-stat
  "Reset the stats returned by INFO"
  [client]
  (.config-reset-stat client))
