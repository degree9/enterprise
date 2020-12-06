(ns degree9.socket-io
  (:require ["socket.io-client" :as client]
            [degree9.events.emitter :as emitter]))

(defn io
  "Create a socket.io-client instance."
  ([] (client))
  ([url] (client url))
  ([url opts] (client url (clj->js opts))))

(defn connect-error [socket listener]
  (emitter/on socket "connect_error" listener))
