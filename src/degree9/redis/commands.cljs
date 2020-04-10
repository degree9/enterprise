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
  "Mark the start of a transaction block"
  [client]
  (.multi client))

(defn object
  "Inspect the internals of redis objects"
  [client subcommand & args]
  (.object client subcommand args))

(defn Persist
  "Remove the expiration from a key"
  [client key]
  (.persist client key))

(defn pexpire
  "Set a key's time to live in milliseconds"
  [client key milliseconds]
  (.pexpire client key milliseconds args))

(defn pexpire-at
  "Returns the client ID for the current connection"
  [client milliseconds-timestamp]
  (.pexpireat client milliseconds-timestamp))

(defn pfadd
  "Adds the specified elements to the specified hyperloglog"
  [client key element & args]
  (.pfadd client key element args))

(defn pfcount
  "Return the approximated cardinality of the set(s) observed by the hyperloglog at key(s)"
  [client key & args]
  (.pfcount client key args))

(defn pfmerge
  "Merge N different hyperloglogs into a single one"
  [client destkey sourcekey & args]
  (.pfmerge client destkey sourcekey args))

(defn ping
  "ping the server"
  [client & args]
  (.ping client args))

(defn psetex
  "Set the value and expiration in milliseconds of a key"
  [client key milliseconds value]
  (.psetex client key milliseconds value))

(defn psubscribe
  "Listen for messages published to channels matching the given patterns"
  [client pattern & args]
  (.psubscribe client pattern args))

(defn pubsub
  "Inspect the state of the pub/sub subsystem"
  [client subcommand & args]
  (.pubsub client subcommand args))

(defn pttl
  "Get the time to live for a key in milliseconds"
  [client key]
  (.pttl client key))

(defn publish
  "Post a message to a channel"
  [client channel message]
  (.publish client channel message))

(defn punsubscribe
  "Stop listening for messages posted to channels matching the given patterns"
  [client & args]
  (.punsubscribe client arg))

(defn quit
  "Close the connection"
  [client]
  (.quit client))

(defn random-key
  "Return a random key from the keyspace"
  [client]
  (.randomkey client))

(defn read-only
  "Enables read queries for a connection to a cluster replica node"
  [client]
  (.readonly client))

(defn read-write
  "Disables read queries for a connection to a cluster replica node"
  [client]
  (.readwrite client))

(defn rename
  "Rename a key"
  [client key newkey]
  (.rename client key newkey))

(defn renamenx
  "Rename a key, only if the new key does not exist"
  [client key newkey]
  (.renamenx client key newkey))

(defn restore
  "Create a key using the provided serialized value, previously obtained using DUMP"
  [client key ttl serialized-value & args]
  (.restore client key ttl serialized-value args))

(defn role
  "Returns the role of the instance in the context of replication"
  [client]
  (.role client))

(defn rpop
  "Remove and get the last element in a list"
  [client key]
  (.rpop client key))

(defn rpoplpush
  "Remove the last element in a list, prepend it to another list and return it"
  [client source and destination]
  (.rpoplpush client source and destination))

(defn rpush
  "Append one or multiple elements to a list"
  [client key element & args]
  (.rpush client key element args))

(defn rpushx
  "Append an element to a list, only if the list exists"
  [client key element & args]
  (.rpushx client key element args))

(defn sadd
  "Add one or more members to a set"
  [client key member & args]
  (.sadd client key member args))

(defn save
  "Synchronously save the dataset to disk"
  [client]
  (.save client))

(defn scard
  "Get the number of members in a set"
  [client key]
  (.scard client key))

(defn script-debug
  "Set the debug mode for executed scripts"
  [client & args]
  (.script-debug client args))

(defn script-exists
  "Check existance of the scripts in the script cache"
  [client sha1 & args]
  (.script-exists client sha1 args))

(defn script-flush
  "Remove all the scripts from the script cache"
  [client]
  (.script-flush client))

(defn script-kill
  "Kill the script currently in execution"
  [client]
  (.script-kill client))

(defn script-load
  "Load the specified lua script into the script cache"
  [client script]
  (.script-load client script))

(defn sdiff
  "Subtract multiple sets"
  [client key & args]
  (.sdiff client key args))

(defn sdiffstore
  "Subtract multiple sets and store the resulting set in a key"
  [client destination key & args]
  (.sdiffstore client destination key args))

(defn select
  "Change the selected database for the current connection"
  [client index]
  (.select client index))

(defn set
  "Set the string value of a key"
  [client key value & args]
  (.select client key value args))

(defn setbit
  "Sets or clears the bit at offset in the string value stored at a key"
  [client key offset value]
  (.setbit client key offset value))

(defn setex
  "Set the value of a key, only if the key does not exist"
  [client key seconds value]
  (.setex client key seconds value))

(defn setrange
  "Overwrite part of the string at key starting at the specified offset"
  [client key offset value]
  (.setrange client key offset value))

(defn shutdown
  "Synchronously save the dataset to disk and then shutdown the server"
  [client & args]
  (.shutdown client args))

(defn sinter
  "Intersect multiple sets"
  [client key & args]
  (.sinter client key args))

(defn sinterstore
  "Intersect multiple sets and store the resulting set in a key"
  [client destination key & args]
  (.sinterstore client destination key args))

(defn s-is-member
  "Determine if a given value is a member of a set"
  [client key member]
  (.sismember client key member))

(defn slave-of
  "Make the server a replica of another instance, or promote it as master. Depricated with redis 5. Use REPLICAOF instead"
  [client host port]
  (.slaveof client host port))

(defn replica-of
  "Make the server a replica of another instance, or promote it as master"
  [client host port]
  (.replicaof client host port))

(defn slow-log
  "Manages the redis slow queries log"
  [client subcommand & args]
  (.slowlog client subcommand args))

(defn s-members
  "Get all the members in a set"
  [client key]
  (.smembers client key))

(defn s-move
  "Move a member from one set to another"
  [client source destination member]
  (.smove client source destination member))

(defn sort
  "Sort the elements in a list, set or sorted set"
  [client key & args]
  (.sort client key args))

(defn s-pop
  "Remove and return one or multiple random members from a set"
  [client key & args]
  (.spop client key args))

(defn s-rand-member
  "Get one or multiple random members from a set"
  [client key & args]
  (.srandmember client key args))

(defn s-rem
  "Remove one or more members from a set"
  [client key member & args]
  (.srem client key member args))

(defn str-len
  "Get the length of the value stored in a key"
  [client key]
  (.strlen client key))

(defn subscribe
  "Listen for a message published to the given channels"
  [client channel & args]
  (.subscribe client channel args))

(defn s-union
  "Add multiple keys"
  [client key & args]
  (.sunion client key args))

(defn s-union-store
  "Add multiple sets and store the resulting set in a key"
  [client destination key & args]
  (.sunionstore client destination key args))

(defn swap-db
  "Swaps two redis databases"
  [client index1 index2]
  (.swapdb client index1 index2))

(defn sync
  "Internal command used for replication"
  [client]
  (.sync client))

(defn p-sync
  "Internal command used for replication"
  [client replicationid offset]
  (.psync client replicationid offset))

(defn time
  "return the current server time"
  [client]
  (.time client))

(defn touch
  "Alters the last access time of a key(s). Returns the number of existing keys specified"
  [client key & args]
  (.client-id client keys args))

(defn ttl
  "Get the time to live for a key"
  [client key]
  (.ttl client key))

(defn type
  "Determine the type stored at key"
  [client key]
  (.type client key))

(defn unsubscribe
  "Stop listening for messages posted to the given channels"
  [client & args]
  (.unsubscribe client args))

(defn unlink
  "Delete a key asynchronously in another thread. Otherwise it is just as DEL, but non blocking"
  [client key & args]
  (.unlink client key args))

(defn unwatch
  "Forget about all watched keys"
  [client]
  (.unwatch client))

(defn wait
  "Wait for the synchronous replication of all the write commands sent in the context of the current connection"
  [client numreplicas timeout]
  (.wait client numreplicas timeout))

(defn watch
  "Watch the given keys to determine execution of the MULTI/EXEC block"
  [client key & args]
  (.watch client key args))

(defn z-add
  "Add one or more members to a sorted set, or update it's score if it already exists"
  [client key score member & args]
  (.zadd client key score member args))

(defn z-card
  "Get the number of members in a sorted set"
  [client key]
  (.zcard client key))

(defn z-count
  "Count the members in a sorted set with scores within the given values"
  [client key min max]
  (.zcount client key min max))

(defn z-incr-by
  "Increment the score of a member in a sorted set"
  [client key increment member]
  (.zincrby client key increment member))

(defn z-inter-store
  "Intersect multiple sorted sets and store the resulting sorted set in a new key"
  [client destination numkeys key & args]
  (.zinterstore client destination numkeys key args))

(defn z-lex-count
  "Count the number of members in a sorted set between a given lexicographical range"
  [client key min max]
  (.zlexcount client key min max))

(defn z-pop-max
  "Remove and return members with the highest scores in a sorted set"
  [client key & args]
  (.zpopmax client key args))

(defn z-pop-min
  "Remove and return members with the lowest scores in a sorted set"
  [client key & args]
  (.zpopmin client key args))

(defn z-range
  "Return a range of members in a sorted set, by index"
  [client key start stop & args]
  (.zrange client key start stop args))

(defn z-range-by-lex
  "Returns a range of members in a sorted set, by lexicographical range, ordered from higher to lower strings"
  [client key min max & args]
  (.zrangebylex client key min max args))

(defn z-range-by-score
  "Return a range of members in a sorted set, by score"
  [client key min max & args]
  (.zrangebyscore client key min max args))

(defn z-rank
  "Determine the index of a member in a sorted set"
  [client key member]
  (.zrank client key member))

(defn z-rem
  "Remove one or more members from a sorted set"
  [client key member & args]
  (.zrem client key member args))

(defn z-rem-range-by-lex
  "Remove all members in a sorted set between the given lexicographical range"
  [client key min max]
  (.zremrangebylex client key min max))

(defn z-rem-range-by-rank
  "Remove all members in a sorted set within the given indexes"
  [client key start stop]
  (.zremrangebyrank client key start stop))

(defn z-rem-range-by-score
  "Remove all members in a sorted set within the given scores"
  [client key start stop]
  (.zremrangebyscore client key start stop))

(defn z-rev-rank
  "Determine the index of a member in a sorted set, with scores ordered from high to low"
  [client key member]
  (.zrevrank client key member))

(defn z-score
  "Get the score associated with the given member in a sorted set"
  [client key member]
  (.client-kill client key member))

(defn z-union-store
  "Add multiple sorted sets and store the resulting sorted set in a new key"
  [client destination numkeys key & args]
  (.zunionstore client destination numkeys key args))

(defn scan
  "Incrementally iterate the keyspace"
  [client cursor & args]
  (.scan client cursor args))

(defn s-scan
  "Incrementally iterate set elements"
  [client key cursor & args]
  (.sscan client key cursor args))

(defn h-scan
  "Incrementally iterate hash fields and associated values"
  [client key cursor & args]
  (.hscan client key cursor args))

(defn z-scan
  "Incrementally iterate sorted sets elements and associated scores"
  [client key cursor & args]
  (.zscan client key cursor args))

(defn x-info
  "Get information on streams and consumer groups"
  [client & args]
  (.xinfo client args))

(defn x-add
  "Appends a new entry to a stream"
  [client key id field value & arg]
  (.xadd client key id field value arg))

(defn x-trim
  "Trims the stream to (approximately if '~' is passed) a certain size"
  [client key maxlen count & args]
  (.xtrim client key maxlen count args))

(defn x-del
  "Removes the specified enties from the stream. returns the number of items actually deleted
  that may be different from the number of ID's passed in case certain ID's do not exist"
  [client key id & args]
  (.xdel client key id args))

(defn x-range
  "Return a range of elements in a stream, with ID's matching the specified ID's interval"
  [client key start end & args]
  (.xrange client key start end args))

(defn x-rev-range
  "Return a range of elements in a stream, with ID's matching the specified ID's interval, in reverse order
  (from greater to smaller ID's) compared to XRANGE"
  [client key start end & args]
  (.xrevrange client key start end args))

(defn x-len
  "Return the number of entries in a stream"
  [client key]
  (.xlen client key))

(defn x-read
  "Return never seen elements in multiple streams, with ID's greater than the ones reported
  by the caller for each stream. Can block"
  [client key id & args]
  (.xread client args))

(defn x-group
  "Create, destroy, and manage consumer groups"
  [client & args]
  (.xgroup client args))

(defn x-read-group
  "Return new entries from a stream using a consumer group, or access the history of the pending entries
  for a given consumer. Can block"
  [client group consumer & args]
  (.xreadgroup client group consumer args))

(defn x-ack
  "Marks a pending message as correctly processed, effectively removing it from the pending entries list of the
  consumer group. Return value of the command is the number of messages successfully acknowledged, that is,
  the ID's we were actually able to resolve in the PEL"
  [client key group id & args]
  (.xack client key group id args))

(defn x-claim
  "Changes (or requires) ownership of a message in a consumer group, as if the message was delivered to the
  consumer"
  [client key group consumer min-idle-time id & args]
  (.xclaim client key group consumer min-idle-time id args))

(defn x-pending
  "Return information and entries from a stream consumer group pending entries list, that are messages fetched
  but never acknowledged"
  [client key group & args]
  (.xpending client key group args))

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
