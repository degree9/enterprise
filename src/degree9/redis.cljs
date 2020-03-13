(ns degree9.redis
  (:require ["redis" :as rs]
            [clojure.string :as s]))

(defn- redis-url [& {:keys [host port password db]}]
  (s/join
    (cond-> ["rediss://"]
      password (conj (str ":" password "@"))
      host     (conj (str host))
      port     (conj (str ":" port))
      db       (conj (str "/" db)))))

(defn- mkclient [& [opts]]
  (.client rs (redis-url opts)))

(defn redis [& [opts]]
  (let [host     (env/get "REDIS_HOST")
        port     (env/get "REDIS_PORT")
        password (env/get "REDIS_PASSWORD")
        db       (env/get "REDIS_DB")
        client   (:client opts (mkclient {:host host :port port :password password :db db}))]
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
