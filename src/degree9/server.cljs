(ns degree9.server
 (:require
   ["debug" :as dbg]
   [meta.server :as server]
   [degree9.env :as env]
   [degree9.channels :as chan]))

(def ^:private debug (dbg "degree9:enterprise:server"))

(defn app [& opts]
 (debug "Starting enterprise server")
 (let [opts (set opts)]
   (cond-> (server/app)
     (:default    opts) (server/with-defaults)
     (:rest       opts) (server/with-rest)
     ;(:session    opts) (server/with-session)
     (:socket     opts) (server/with-socketio)
     (:channels   opts) (chan/with-channels)
     (:auth       opts) (server/with-authentication))))

(defn start!
 ([]
  (start!
   (app
    :default
    :rest
    :socket
    :channels)))
    ; :auth

 ([app]
  (let [port (env/get "APP_PORT")]
    (debug "Server listening on port %s" port)
    (server/listen app port))))
