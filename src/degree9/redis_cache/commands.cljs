(ns degree9.redis_cache.commands)










(defn acl-load
  "Reload the ACLs from the configured ACL file"
  [client]
  (.acl-load client))

(defn acl-save
  "Save the current ACL rules in the configured ACL file"
  [client]
  (.acl-save client))

(defn acl-list
  "List the current ACL rules in ACL config file format"
  [client]
  (.acl-list client))

(defn acl-users
  "List the username of all the configured ACL rules"
  [client]
  (.acl-users client))

(defn acl-set-users
  "Modify or create the rules for a specific ACL user"
  [client rule & args]
  (.acl-set-users client rule args))

(defn acl-del-users
  "Remove the specified ACL users and the associated rules"
  [client username & args]
  (.acl-del-users client username args))

(defn acl-cat
  "List the ACL categories users and the associated rules"
  [client categoryname]
  (.acl-cat client categoryname))

(defn acl-genpass
  "Generate a pseudorandom secure passowrd to use for ACL users"
  [client]
  (.acl-genpass client))

(defn acl-whoami
  "Return the name of the user associated to the current connection"
  [client]
  (.acl-whoami client))

(defn acl-log
  "List latest events denied because of ACLs in place"
  [client & args]
  (.acl-log client args))

(defn append
  "Append a value to a key"
  [client key value]
  (.append client key value))

(defn auth
  "Authenticate to the server"
  [client password]
  (.auth client password))

(defn bgrewriteaof
  "Asychronously rewrite the append-only folder"
  [client]
  (.bgrewriteaof client))

(defn bgsave
  "Asynchronously save the dataset to disk"
  [client & args]
  (.bgsave client args))

(defn bitcount
  "Count set bits in a string"
  [client & args]
  (.bitcount client args))

(defn bitfield
  "Perform arbitrary bitfield integer operations on strings"
  [client key & args]
  (.bitfield client key args))

(defn bitop
  "Performs bitwise operations between strings"
  [client operation destkey key & args]
  (.bitop client operation destkey key args))

(defn bitpos
  "Find first bit set or clear in a string"
  [client key bit & args]
  (.bitpos client key bit args))

(defn blpop
  "Remove and get the last element in a list, or block until one is available"
  [client key timeout & args]
  (.blpop client key timeout args))

(defn brpop
  "Remove and get the last element in a list, or block until one is available"
  [client key timeout & args]
  (.brpop client key timeout args))

(defn brpoppush
  "Pop an element from a list, push it to another list and return it; or block until one is available"
  [client source destination timeout]
  (.brpoppush client source destination timeout))

(defn bzpopmin
  "Remove and return the member with the highest score from one or more sorted sets, or block until one is available"
  [client key timeout & args]
  (.bzpopmin key timeout args))

(defn bzpopmax
  "Remove and return the member with the highest score from one or more sorted sets, or block until one is available"
  [client key timeout & args]
  (.bzpopmax key timeout & args))

(defn client-id
  "Returns the client ID for the current connection"
  [client]
  (.client-id client))

(defn client-kill
  "Kill the connection of a client"
  [client & args]
  (.client-kill client args))

(defn client-list
  "Get list of client connections"
  [client & args]
  (.client-list client args))

(defn client-getname
  "Get current connection name"
  [client]
  (.client-getname client))

(defn client-pause
  "Stop processing commands from clients for some time"
  [client timeout]
  (.client-kill client timeout))

(defn client-setname
  "Set the current connection name"
  [client connection-name]
  (.client-setname client connection-name))

(defn client-unblock
  "Unblock a client blocked in a blocking command from a different location"
  [client client-id & args]
  (.client-unblock client client-id args))

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

(defn client-kill
  "Kill the connection of a client"
  [client & args]
  (.client-kill client args))

(defn client-id
  "Returns the client ID for the current connection"
  [client]
  (.client-id client))

(defn client-kill
  "Kill the connection of a client"
  [client & args]
  (.client-kill client args))

(defn client-id
  "Returns the client ID for the current connection"
  [client]
  (.client-id client))

(defn client-kill
  "Kill the connection of a client"
  [client & args]
  (.client-kill client args))

(defn client-id
  "Returns the client ID for the current connection"
  [client]
  (.client-id client))

(defn client-kill
  "Kill the connection of a client"
  [client & args]
  (.client-kill client args))

(defn client-id
  "Returns the client ID for the current connection"
  [client]
  (.client-id client))

(defn client-kill
  "Kill the connection of a client"
  [client & args]
  (.client-kill client args))

(defn client-id
  "Returns the client ID for the current connection"
  [client]
  (.client-id client))

(defn client-kill
  "Kill the connection of a client"
  [client & args]
  (.client-kill client args))

(defn client-id
  "Returns the client ID for the current connection"
  [client]
  (.client-id client))

(defn client-kill
  "Kill the connection of a client"
  [client & args]
  (.client-kill client args))

(defn client-id
  "Returns the client ID for the current connection"
  [client]
  (.client-id client))

(defn client-kill
  "Kill the connection of a client"
  [client & args]
  (.client-kill client args))

(defn client-id
  "Returns the client ID for the current connection"
  [client]
  (.client-id client))

(defn client-kill
  "Kill the connection of a client"
  [client & args]
  (.client-kill client args))

(defn client-id
  "Returns the client ID for the current connection"
  [client]
  (.client-id client))

(defn client-kill
  "Kill the connection of a client"
  [client & args]
  (.client-kill client args))

(defn client-id
  "Returns the client ID for the current connection"
  [client]
  (.client-id client))

(defn client-kill
  "Kill the connection of a client"
  [client & args]
  (.client-kill client args))

(defn client-id
  "Returns the client ID for the current connection"
  [client]
  (.client-id client))

(defn client-kill
  "Kill the connection of a client"
  [client & args]
  (.client-kill client args))

(defn client-id
  "Returns the client ID for the current connection"
  [client]
  (.client-id client))

(defn client-kill
  "Kill the connection of a client"
  [client & args]
  (.client-kill client args))

(defn client-id
  "Returns the client ID for the current connection"
  [client]
  (.client-id client))

(defn client-kill
  "Kill the connection of a client"
  [client & args]
  (.client-kill client args))

(defn client-id
  "Returns the client ID for the current connection"
  [client]
  (.client-id client))

(defn client-kill
  "Kill the connection of a client"
  [client & args]
  (.client-kill client args))

(defn client-id
  "Returns the client ID for the current connection"
  [client]
  (.client-id client))

(defn client-kill
  "Kill the connection of a client"
  [client & args]
  (.client-kill client args))

(defn client-id
  "Returns the client ID for the current connection"
  [client]
  (.client-id client))

(defn client-kill
  "Kill the connection of a client"
  [client & args]
  (.client-kill client args))

(defn client-id
  "Returns the client ID for the current connection"
  [client]
  (.client-id client))

(defn client-kill
  "Kill the connection of a client"
  [client & args]
  (.client-kill client args))

(defn client-id
  "Returns the client ID for the current connection"
  [client]
  (.client-id client))

(defn client-kill
  "Kill the connection of a client"
  [client & args]
  (.client-kill client args))

(defn client-id
  "Returns the client ID for the current connection"
  [client]
  (.client-id client))

(defn client-kill
  "Kill the connection of a client"
  [client & args]
  (.client-kill client args))

(defn client-id
  "Returns the client ID for the current connection"
  [client]
  (.client-id client))

(defn client-kill
  "Kill the connection of a client"
  [client & args]
  (.client-kill client args))

(defn client-id
  "Returns the client ID for the current connection"
  [client]
  (.client-id client))

(defn client-kill
  "Kill the connection of a client"
  [client & args]
  (.client-kill client args))

(defn client-id
  "Returns the client ID for the current connection"
  [client]
  (.client-id client))

(defn client-kill
  "Kill the connection of a client"
  [client & args]
  (.client-kill client args))
