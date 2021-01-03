(ns degree9.service
  "A service endpoint via websockets."
  (:require [degree9.socket-io :as io]
            ["@feathersjs/feathers" :as feathers]
            ["@feathersjs/socketio-client" :as socketio]
            [goog.object :as obj]
            [meta.server :as server]
            [degree9.debug :as dbg]))

(dbg/defdebug debug "degree9:enterprise:service")

(def io io/io)

(defn with-websockets
  "Configure feathers app for websockets."
  [client socket]
  (debug "Configure feathers socket.io client")
  (doto client
    (.configure (socketio socket))))

(defn client [socket]
  (with-websockets (feathers) socket))

(defn µservice [client service]
  (reify
    Object
    (setup [this app path]
      (debug "Setup proxy to remote service %s" service)
      (obj/set this :io (.service client service)))
    (find [this params]
      (debug "Proxy find to remote service %s with %s" service params)
      (.find (obj/get this :io) params))
    (get [this id params]
      (debug "Proxy get to remote service %s/%s with %s" service id params)
      (.get (obj/get this :io) id params))
    (create [this data params]
      (debug "Proxy create to remote service %s with %s" service params)
      (.create (obj/get this :io) data params))
    (update [this id data params]
      (debug "Proxy update to remote service %s with %s" service params)
      (.update (obj/get this :io) id data params))
    (patch [this id data params]
      (debug "Proxy patch to remote service %s with %s" service params)
      (.patch (obj/get this :io) id data params))
    (remove [this id params]
      (debug "Proxy remove to remote service %s with %s" service params)
      (.remove (obj/get this :io) id params))))

(defn api
  "Mount a remote Microservice to a local endpoint."
  ([app path client service hooks]
   (debug "Mount remote microservice at %s" path)
   (server/api app path (µservice client service) hooks)))

;; Multi-Service ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn- api-service [app [prefix client services] hooks]
  (reduce (fn [app service] (api app (str prefix service) client service hooks)) app services))

(defn- reduce-apis [app services hooks]
  (reduce (fn [app service] (api-service app service hooks)) app services))

(defn multi-service
  "Mount multiple remote services from different servers."
  [app specs & [hooks]]
  (reduce-apis app specs hooks))
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
