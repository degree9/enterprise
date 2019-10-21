(ns degree9.hooks
  (:require [goog.object :as obj]))

;; Hook Logging ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn- log-hook [hook]
  (.log js/console hook) hook)
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; Block Transports ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn block-transport [& transports]
  (fn [hook]
    (let [method   (obj/get hook "method")
          provider (get-in (js->clj hook) ["params" "provider"])]
      (if-not (or (contains? (set transports) provider) (empty? transports)) hook
        (throw (str "Transport (" provider ") cannot call method (" method "). (block-transport)"))))))

(defn block-external []
  (block-transport "external"))

(defn block-internal []
  (block-transport "server"))
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; Basic Getters and Setters ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn hook-set! [hook index val]
  (obj/set hook index (clj->js val)))

(defn hook-get [hook index]
  (js->clj (obj/get hook index)))

(defn params [hook]
  (hook-get hook "params"))

(defn params! [hook data]
  (hook-set! hook "params" data))
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
