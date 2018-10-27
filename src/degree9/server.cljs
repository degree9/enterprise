(ns degree9.server
 (:require
   ["debug" :as dbg]
   [meta.server :as server]
   [degree9.env :as env]))

(def ^:private debug (dbg "degree9:enterprise:server"))

(defn app []
 (debug "Starting enterprise server")
 (-> (server/app)
     (server/with-defaults)
     (server/with-rest)
     (server/with-socketio)
     (server/with-authentication)))

(defn start! [app]
  (let [port (env/get "APP_PORT")]
    (debug "Server listening on port %s" port)
    (server/listen app port)))
