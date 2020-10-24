(ns degree9.redis
  (:require ["ioredis" :as ioredis]
            [degree9.env :as env]
            [degree9.debug :as dbg]))

(dbg/defdebug debug "degree9:enterprise:redis")

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn- mkclient [{:keys [protocol host port password] :or {protocol "rediss://"}}]
  (let [url (str protocol ":" password "@" host ":" port)]
    (debug "Connecting redis client to %s:%s" host port)
    (ioredis. url)))

(defn connect! [& [opts]]
  (let [host     (env/require "REDISCACHEHOSTNAME")
        port     (env/require "REDISCACHEPORT")
        password (env/require "REDISCACHEKEY")]
    (debug "Initializing redis client")
    (mkclient
      (merge {:host host :port port :password password} opts))))
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
