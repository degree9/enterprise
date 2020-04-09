(ns degree9.redis.connections)


  
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
  (.client-pause client timeout))

(defn client-setname
  "Set the current connection name"
  [client connection-name]
  (.client-setname client connection-name))

(defn client-unblock
  "Unblock a client blocked in a blocking command from a different location"
  [client client-id & args]
  (.client-unblock client client-id args))
