(ns degree9.redis.diagnostics)



(defn memory-doctor
  "Outputs memory problems report"
  [client]
  (.memory-doctor client))

(defn memory-help
  "Show helpful text about the different subcommands"
  [client]
  (.memory-help client))

(defn memory-malloc-stats
  "Show allocator internal stats"
  [client]
  (.memory-malloc-stats client))

(defn memory-purge
  "Ask the allocator to release memory"
  [client]
  (.memory-purge client))

(defn memory-stats
  "Show memory usage details"
  [client]
  (.memory-stats client))

(defn memory-usage
  "Estimate the memory usage of a key"
  [client key & args]
  (.memory-usage client key args))

(defn latency-doctor
  "Return a human readable latency analysis report"
  [client]
  (.latency-doctor client))

(defn latency-graph
  "Return a latency graph for event"
  [client event]
  (.latency-graph client event))

(defn latency-history
  "Return timestamp-latency samples for the event"
  [client event]
  (.latency-history client event))

(defn latency-latest
  "Return the latest latency samples for all event"
  [client]
  (.latency-latest client))

(defn latency-reset
  "Reset latency data for one or more events"
  [client & args]
  (.client-kill client args))

(defn latency-help
  "Show helpful text about the different subcommands"
  [client]
  (.latency-help client))
