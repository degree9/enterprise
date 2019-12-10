(ns degree9.server
 (:require
   [degree9.debug :as dbg]
   [meta.server :as server]
   [degree9.env :as env]
   [degree9.channels :as chan]
   [degree9.roles :as roles]))

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
     (:auth       opts) (server/with-authentication)
     (:roles      opts) (roles/with-authorization))))

(defn start! [app]
  (let [port (env/get "APP_PORT")]
    (debug "Server listening on port %s" port)
    (server/listen app port)))
