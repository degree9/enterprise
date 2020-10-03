(ns degree9.redis
  (:require ["redis" :as rs]
            [degree9.env :as env]
            [degree9.debug :as dbg]
            [degree9.string :as s]))

(dbg/defdebug debug "degree9:enterprise:redis")

(defn- mkclient [& {:keys [host port password]}]
  (.createClient rs port host (clj->js {:auth_pass password :tls {:servername host}})))

(defn redis [& [opts]]
  (let [host     (env/require "REDISCACHEHOSTNAME")
        port     (env/require "REDISCACHEPORT")
        password (env/require "REDISCACHEKEY")
        client   (:client opts (mkclient {:host host :port port :password password}))]
    (debug "Initializing redis service.")
    (reify
      Object
      (find [this params])
      ;  (list-namespace api))
      (get [this id params])
      ;  (read-namespace api id))
      (create [this data params])
      ;  (create-namespace api data))
      (update [this id data params])
      ;  (replace-namespace api id data))
      (patch [this id data params])
      ;  (patch-namespace api id data))
      (remove [this id params]))))
      ;  (delete-namespace api id)))))

;; D9 Public Functions ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn connect! [& [opts]]
  (let [host     (env/require "REDISCACHEHOSTNAME")
        port     (env/require "REDISCACHEPORT")
        password (env/require "REDISCACHEKEY")]
    (debug "Initializing redis client.")
    (mkclient
      (merge opts
        {:host host :port port :password password}))))
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
