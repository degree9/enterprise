(ns degree9.redis.hashes)




(defn hdel
  "Delete one or more hash fields"
  [client key field & args]
  (.hdel client key field args))

(defn hexists
  "Determine if a hash field exists"
  [client key field]
  (.hexists client key field))

(defn hget
  "Get the value of a hash field"
  [client key field]
  (.hget client key field))

(defn hget-all
  "Get all the fields and values in a hash"
  [client key]
  (.hget-all client key))

(defn hincr-by
  "Increment the integer value of a hash field by the given number"
  [client key field increment]
  (.hincr-by client key field increment))

(defn hincr-by-float
  "Increment the float value of a hash field by the given amount"
  [client key field increment]
  (.hincr-by-float client key field increment))

(defn hkeys
  "Get all the fields in a hash"
  [client key]
  (.client-id client key))

(defn hlen
  "Get the number of fields in a hash"
  [client key]
  (.hlen client key))

(defn hmget
  "Get the values of all the given hash fields"
  [client key field & args]
  (.hmget client key field args))

(defn hmset
  "Set multiple hash fields to multiple values"
  [client key field value & args]
  (.hmset client key field value args))

(defn hset
  "Get the values of all the given hash fields"
  [client key field value & args]
  (.hset client key field value args))

(defn hsetnx
  "Set the value of a hash field, only if the field does not exist"
  [client key field value]
  (.hsetnx client key field value))

(defn hstrlen
  "Get all the values in a hash"
  [client key field]
  (.hstrlen client key field))

(defn hvals
  "Get all the values in a hash"
  [client key]
  (.hvals client key))  
