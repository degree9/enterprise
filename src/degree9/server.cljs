(ns degree9.server
 (:require
   [meta.server :as server]
   [degree9.debug :as dbg]
   [degree9.env :as env]
   [degree9.channels :as chan]
   [degree9.roles :as roles]))

(dbg/defdebug debug "degree9:enterprise:server")

(def api server/api)

(def with-rest server/with-rest)

(def with-socketio server/with-socketio)

(def with-channels chan/with-channels)

(def with-authentication server/with-authentication)

(def with-authentication-local server/with-authentication-local)

(def with-authentication-oauth server/with-authentication-oauth)

(def with-authorization roles/with-authorization)

(def with-errors server/with-error-handler)

(defn app [& opts]
 (debug "Starting enterprise server")
 (let [opts (set opts)]
   (cond-> (server/app)
     (:default    opts) (server/with-defaults)
     (:rest       opts) (with-rest)
     (:socket     opts) (with-socketio)
     (:channels   opts) (with-channels)
     (:auth       opts) (with-authentication)
     (:local      opts) (with-authentication-local)
     (:oauth      opts) (with-authentication-oauth)
     (:roles      opts) (with-authorization)
     (:errors     opts) (with-errors))))

(defn start! [app]
  (let [port (env/get "APP_PORT")]
    (debug "Server listening on port" port)
    (server/listen app port)))
