(ns degree9.server
 (:require
   ["debug" :as dbg]
   [meta.server :as server]
   [degree9.env :as env]))

(def ^:private debug (dbg "degree9:enterprise:server"))

(defn app [& opts]
 (debug "Starting enterprise server")
 (let [opts (set opts)]
   (cond-> (server/app)
     (:default    opts) (server/with-defaults)
     (:rest       opts) (server/with-rest)
     (:socket     opts) (server/with-socketio)
     (:auth       opts) (server/with-authentication))))
     ; (:local      opts) (server/with-authentication-local))))

(defn start! [app]
  (let [port (env/get "APP_PORT")]
    (debug "Server listening on port %s" port)
    (server/listen app port)))
