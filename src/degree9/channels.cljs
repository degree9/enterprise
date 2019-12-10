(ns degree9.channels
  (:require [goog.object :as obj]
            [meta.server :as server]
            [feathers.channels :as chan]
            [degree9.debug :as dbg]))

(def ^:private debug (dbg "degree9:enterprise:channels"))

(defn join-authenticated [app]
  (chan/login app
    #(let [conn (obj/get %2 "connection")]
       (debug "Joining Channel: Authenticated" conn)
       (when conn
         (chan/leave (chan/channel app "anonymous") conn)
         (chan/join (chan/channel app "authenticated") conn)))))

(defn publish-events [app channel]
  (debug "Registering event publishing channel:" channel)
  (.publish app
    (fn [data ctx]
      (chan/channel app channel))))

(defn publish-authenticated [app]
  (publish-events app "authenticated"))

(defn join-anonymous [app]
  (chan/connection app #(chan/join (chan/channel app "anonymous") %)))

(defn with-channels [app]
  (-> app
    (server/with-channels)
    (publish-authenticated)
    (join-authenticated)))
