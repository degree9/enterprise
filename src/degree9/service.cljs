(ns degree9.service
 (:require ["socket.io-client" :as io]
           ["@feathersjs/feathers" :as feathers]
           ["@feathersjs/socketio-client" :as socketio]
           [goog.object :as obj]
           [meta.server :as server]
           [degree9.debug :as dbg]))

(dbg/defdebug debug "degree9:enterprise:service")

(defn mksocket
  "Create a websocket connection to remote microservice."
  [client uri]
  (debug "Create websocket connection to remote microservice at %s" uri)
  (let [socket (io uri)]
    (doto client
      (.configure (socketio socket)))))

(defn µservice [uri service & [opts]]
  (debug "Initializing Remote Microservice at %s/%s" uri service)
  (let [client (:client opts (feathers))
        id (:id opts "id")]
    (reify
      Object
      (id [this] id)
      (setup [this app path]
        (obj/set this "proxy"
          (.service (mksocket client uri) service)))
      (find [this params]
        (prn "µService FIND" params)
        (-> (.find (obj/get this "proxy") params)
          (.then prn)
          (.catch prn)))
      (get [this id params]
        (.get (obj/get this "proxy") id params))
      (create [this data params]
        (.create (obj/get this "proxy") data params))
      (update [this id data params]
        (.update (obj/get this "proxy") id data params))
      (patch [this id data params]
        (.patch (obj/get this "proxy") id data params))
      (remove [this id params]
        (.remove (obj/get this "proxy") id params)))))

(defn api
  "Mount a remote Microservice to a local endpoint."
  [app path uri service hooks]
  (debug "Mount Remote Microservice at %s" path)
  (server/api app path (µservice uri service) hooks))
