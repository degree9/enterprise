(ns degree9.remote
  "Provides methods for connecting via microservices pattern."
  (:require
    [meta.server :as server]
    [meta.client :as client]))

(defn connection
  "Create a connection to a remote server."
  [uri]
  (-> (client/app)
    (client/with-socketio uri)
    (client/with-authentication nil)))

(defn api
  "Mount a remote service to a local endpoint."
  [app path conn service hooks]
  (server/api app path (client/service conn service) hooks))
