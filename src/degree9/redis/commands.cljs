(ns degree9.redis_cache.commands)












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

(defn command
  "Get array of redis command details"
  [client]
  (.command client))

(defn command-count
  "Get total number of redis commands"
  [client]
  (.command-count client))

(defn command-get-keys
  "Extract keys given a full redis command"
  [client]
  (.command-get-keys client))

(defn command-info
  "Get array of specific redis command details"
  [client command-name & args]
  (.command-info client command-name args))



(defn db-size
  "Return the number of keys in the selected database"
  [client]
  (.db-size client))

(defn debug-object
  "Get debugging information about a key"
  [client destkey]
  (.debug-object client key))

(defn debug-segfault
  "Make the server crash"
  [client]
  (.debug-segfault client))

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

(defn discard
  "Discard all commands issued after MULTI"
  [client]
  (.discard client))

(defn dump
  "Returns a serialized version of the value stored at the specified key"
  [client key]
  (.dump client key))

(defn echo
  "Echo the given string"
  [client message]
  (.echo client message))

(defn eval
  "Execute a lua script server side"
  [client script numkeys key & args]
  (.eval client script numkeys key args))

(defn eval-sha
  "Execute a lua script server side"
  [client sha1 numkeys key & args]
  (.eval-sha client shai numkeys key args))

(defn exec
  "Executes all commands issued after MULTI"
  [client]
  (.exec client))

(defn exists
  "Determines if a key exists"
  [client key & args]
  (.exists client key args))

(defn expire
  "Set a key's time to live in seconds"
  [client key seconds]
  (.client-id client key seconds))

(defn expire-at
  "Set the expiration for a key as a UNIX timestamp"
  [client key timestamp]
  (.expire-at client key timestamp))

(defn flush-all
  "Remove all keys from all databases"
  [client & args]
  (.flush-all client & args))

(defn flush-db
  "Remove all keys from the current database"
  [client & args]
  (.flush-db client args))

(defn get
  "Get the value of the key"
  [client key]
  (.get client key))

(defn get-bit
  "Returns the bit value at offset in the string value stored at key"
  [client key offset]
  (.get-bit client key offset))

(defn get-range
  "Get a substring of the string stored at a key"
  [client key start end]
  (.get-range client key start end))

(defn get-set
  "Set the string value of a key and return it's old value"
  [client key value]
  (.get-set client key value))

(defn incr
  "Increment the integer value of a key by one"
  [client key]
  (.incr client key))

(defn incr-by
  "Increment the integer value of a key by the given amount"
  [client key increment]
  (.incr-by client increment))

(defn incr-by-float
  "Increment the float value of a key by the given amount"
  [client key increment]
  (.client-id client key increment))

(defn info
  "Get information and statistics about the server"
  [client & args]
  (.info client args))

(defn lolwut
  "Display some computer art and the redis version"
  [client & args]
  (.lolwut client arg))

(defn keys
  "Find all the keys matching a given pattern"
  [client pattern]
  (.keys client pattern))

(defn last-save
  "Get the UNIX time stamp of the last successful save to disk"
  [client]
  (.lastsave client))

(defn lindex
  "Get an element from a list by its index"
  [client key index]
  (.lindex client key index))

(defn linsert
  "Insert an element before or after another element in a list"
  [client key pivot element & args]
  (.linsert client key pivot element & args))

(defn llen
  "Get the length of a list"
  [client key]
  (.llen client key))

(defn lpop
  "Remove and get the first element in a list"
  [client key]
  (.lpop client key))

(defn lpush
  "Prepend one or multiple elements to a list"
  [client key element & args]
  (.client-kill client key element args))

(defn lpushx
  "Prepend an element to a list, only if the list exists"
  [client key element & args]
  (.lpushx client key element & args))

(defn lrange
  "Get a range of elements from a list"
  [client key start stop]
  (.lrange client key start stop))

(defn lrem
  "Remove elements from a list"
  [client key count element]
  (.lrem client key count element))

(defn lset
  "Set the value of an element in a list by its index"
  [client key index element]
  (.lset client key index element))

(defn ltrim
  "Trim a list to the specified range"
  [client key start stop]
  (.ltrim client key start stop))

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
  (.client-id client key args))

(defn mget
  "Get the values of all the given keys"
  [client key & args]
  (.mget client key args))

(defn migrate
  "Atomically transfer a key from a redis instance to another one"
  [client host port key destination-db timeout & args]
  (.migrate client host port key destination-db timeout args))

(defn module-list
  "List all modules loaded by the server"
  [client]
  (.module-list client))

(defn module-load
  "Load a module"
  [client path & args]
  (.module-load client path args))

(defn module-unload
  "Unload a module"
  [client name]
  (.module-unload client name))

(defn monitor
  "Listen for all requests received by the server in real time"
  [client]
  (.monitor client))

(defn move
  "Move a key to another database"
  [client key db]
  (.move client key db))

(defn mset
  "Set multiple keys to multiple values"
  [client key value & args]
  (.mset client key value & args))

(defn msetnx
  "Set multiple keys to multiple values, only if none of the keys exist"
  [client key value & args]
  (.msetnx client key value args))

(defn multi
  "Returns the client ID for the current connection"
  [client]
  (.multi client))

(defn object
  "Kill the connection of a client"
  [client & args]
  (.object client args))

(defn lrem
  "Returns the client ID for the current connection"
  [client]
  (.client-id client))

(defn client-kill
  "Kill the connection of a client"
  [client & args]
  (.client-kill client args))

(defn lrem
  "Returns the client ID for the current connection"
  [client]
  (.client-id client))

(defn client-kill
  "Kill the connection of a client"
  [client & args]
  (.client-kill client args))

(defn lrem
  "Returns the client ID for the current connection"
  [client]
  (.client-id client))

(defn client-kill
  "Kill the connection of a client"
  [client & args]
  (.client-kill client args))


(defn lrem
  "Returns the client ID for the current connection"
  [client]
  (.client-id client))

(defn client-kill
  "Kill the connection of a client"
  [client & args]
  (.client-kill client args))

(defn lrem
  "Returns the client ID for the current connection"
  [client]
  (.client-id client))

(defn client-kill
  "Kill the connection of a client"
  [client & args]
  (.client-kill client args))

(defn lrem
  "Returns the client ID for the current connection"
  [client]
  (.client-id client))

(defn client-kill
  "Kill the connection of a client"
  [client & args]
  (.client-kill client args))

(defn lrem
  "Returns the client ID for the current connection"
  [client]
  (.client-id client))

(defn client-kill
  "Kill the connection of a client"
  [client & args]
  (.client-kill client args))

(defn lrem
  "Returns the client ID for the current connection"
  [client]
  (.client-id client))

(defn client-kill
  "Kill the connection of a client"
  [client & args]
  (.client-kill client args))

(defn lrem
  "Returns the client ID for the current connection"
  [client]
  (.client-id client))

(defn client-kill
  "Kill the connection of a client"
  [client & args]
  (.client-kill client args))
