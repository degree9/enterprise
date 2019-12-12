(ns degree9.client
 (:refer-clojure :exclude [find get update remove])
 (:require
  #?(:browser [meta.client :as client])
  [degree9.debug :as dbg]))

(dbg/defdebug debug "degree9:enterprise:client")

#?(:browser
   (do
    ;; App Client ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
    (def app
      (-> (client/app)
        (client/with-socketio)
        (client/with-authentication)))
    ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

    ;; Client Service API ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
    (def service client/service)

    (defn find [service & [params]]
      (client/find service (clj->js params)))

    (defn get [service id & [params]]
      (client/get service id (clj->js params)))

    (defn create [service data & [params]]
      (client/create service (clj->js data) (clj->js params)))

    (defn update [service id data & [params]]
      (client/update service id (clj->js data) (clj->js params)))

    (defn patch [service id data & [params]]
      (client/patch service id (clj->js data) (clj->js params)))

    (defn remove [service id & [params]]
      (client/remove service id (clj->js params)))
    ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

    ;; Client Event API ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
    (def on      client/on)

    (def created client/created)

    (def updated client/updated)

    (def patched client/patched)

    (def removed client/removed)))
    ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
