(ns degree9.ping
  (:require ["socket.io-client" :as io]))

(defn ping [& [url opts]]
  (io url opts))

(defn connected! [io callback]
  (.on io "connect" callback))

(defn disconnected! [io callback]
  (.on io "disconnect" callback))

(defn error! [io callback]
  (.on io "error" callback))

(defn connection-failed! [io callback]
  (.on io "connect_failed" callback))

(defn connection-error! [io callback]
  (.on io "connect_error" callback))
