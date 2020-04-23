(ns degree9.redis.diagnostics)


(defn append
  "Append a value to a key"
  [client key value]
  (.append client key value))

(defn decr
  "Decrements the integer value of a key by one"
  [client key]
  (.decr client key))

(defn decrby
  "Decrement the integer value of a key by the given number"
  [client key decrement]
  (.decrby client key decrement))

(defn del
  "Delete a key"
  [client key & args]
  (.del client key args))
