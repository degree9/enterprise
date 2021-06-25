(ns degree9.channels
  (:require [goog.object :as obj]
            [meta.server :as server]
            [feathers.channels :as chan]
            [degree9.debug :as dbg]))

(dbg/defdebug debug "degree9:enterprise:channels")

(defn join-authenticated
  "Joins an authenticated connection to the `authenticated` channel, also leaves the
   `anonymous` channel."
  [app]
  (chan/login app
    #(when-let [conn (obj/get %2 "connection")]
       (debug "Joining Channel: Authenticated" conn)
       (when conn
         (chan/leave (chan/channel app "anonymous") conn)
         (chan/join (chan/channel app "authenticated") conn)))))

(defn publish-events
  "Publishes events to a channel."
  [app channel]
  (debug "Registering event publishing channel:" channel)
  (.publish app
    (fn [data ctx]
      (chan/channel app channel))))

(defn publish-authenticated
  "Publishes events to the `authenticated` channel."
  [app]
  (debug "Publish events to 'authenticated' channel")
  (publish-events app "authenticated"))

(defn- anonymous-handler [app conn]
  (debug "Joining connection to anonymous channel:" conn)
  (chan/join (chan/channel app "anonymous") conn))

(defn join-anonymous
  "Joins an anonymous connection to the `anonymous` channel."
  [app]
  (chan/connection app (partial anonymous-handler app)))

(defn with-channels
  "Initializes channels api and provides basic anonymous/authenticated channels with publishing."
  [app]
  (-> app
    (server/with-channels)
    (publish-authenticated)
    (join-authenticated)))
