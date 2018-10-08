(ns degree9.server
 (:require
  [meta.server :as server]
  [degree9.env :as env]
  [degree9.security :as sec]))

(def app server/app)

(-> app
    server/with-defaults
    server/with-rest
    server/with-socketio
    server/with-authentication
    sec/with-security)

(defn- main []
 (server/listen app (env/get "APP_PORT")))

(defn start! []
  (server/init! main))
