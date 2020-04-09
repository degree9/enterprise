(ns degree.redis.cluster)


(defn cluster-addslots
  "Assign new hash slots to receive node"
  [client slot & args]
  (.client-kill client slot args))

(defn cluster-bumpepoch
  "Advance the cluster config epoch"
  [client]
  (.cluster-bumpepoch client))

(defn cluster-count-failure-reports
  "Returns the number of failure reports active for a given node"
  [client node-id]
  (.cluster-count-failure-reports client node-id))

(defn cluster-count-keys-in-slot
  "Returns the number of local keys in the specified hash slot"
  [client slot]
  (.cluster-count-keys-in-slot client slot))

(defn cluster-del-slots
  "Set hash slots as unbound in receiving node"
  [client slot & args]
  (.cluster-del-slots client slot args))

(defn cluster-failover
  "Forces a replica to perform a manual failover of it's master"
  [client & args]
  (.cluster-failover client args))

(defn cluster-flush-slots
  "Deletes a nodes own slots information"
  [client]
  (.cluster-flush-slots client))

(defn cluster-forget
  "Remove a node from the nodestable"
  [client node-id]
  (.cluster-forget client node-id))

(defn cluster-get-keys-in-slot
  "Returns local key names in the specified hash slot"
  [client slot count]
  (.cluster-get-keys-in-slot client slot count))

(defn cluster-info
  "Provides info about redis cluster node state"
  [client]
  (.cluster-info client))

(defn cluster-key-slot
  "Return the hash slot of the specified key"
  [client key]
  (.cluster-key-slot client key))

(defn cluster-meet
  "Force a node cluster to handshake with another node"
  [client ip port]
  (.cluster-meet client ip port))

(defn cluster-my-id
  "Returns the node ID"
  [client]
  (.cluster-my-id client))

(defn cluster-nodes
  "Get cluster config for the node"
  [client]
  (.cluster-nodes client))

(defn cluster-replicate
  "Reconfigure a node as a replica of the specified master node"
  [client node-id]
  (.cluster-replicate client node-id))

(defn cluster-reset
  "Reset a redis cluster node"
  [client & args]
  (.cluster-reset client args))

(defn cluster-save-config
  "Forces node to save cluster state on disk"
  [client]
  (.cluster-save-config client))

(defn cluster-set-config-epoch
  "Set the configuration epoch in a new node"
  [client config-epoch]
  (.client-id client config-epoch))

(defn cluster-set-slot
  "Bind a hash slot to a specific node"
  [client slot & args]
  (.cluster-set-slot client slot args))

(defn cluster-slaves
  "List replica nodes of the specified master node"
  [client node-id]
  (.cluster-slaves client node-id))

(defn cluster-replicas
  "List replica nodes of the specified master node"
  [client node-id]
  (.cluster-replicas client node-id))

(defn cluster-slots
  "Get array of cluster slot to node mappings"
  [client]
  (.cluster-slots client))  
