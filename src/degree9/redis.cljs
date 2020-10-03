(ns degree9.redis
  (:require ["redis" :as rs]
            [degree9.env :as env]
            [degree9.debug :as dbg]
            [degree9.string :as s]))

(dbg/defdebug debug "degree9:enterprise:redis")

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn- mkclient [{:keys [host port password]}]
  (prn "REDIS" host port password)
  (.createClient rs (str "rediss://:" password "@" host ":" port) (clj->js {:tls {:servername host}})))

(defn connect! [& [opts]]
  (let [host     (env/require "REDISCACHEHOSTNAME")
        port     (env/require "REDISCACHEPORT")
        password (env/require "REDISCACHEKEY")]
    (debug "Initializing redis client.")
    (mkclient
      (merge {:host host :port port :password password} opts))))
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
