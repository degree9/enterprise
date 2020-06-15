(ns degree9.hooks
  (:require [degree9.object :as obj]
            [degree9.debug :as dbg]))

(dbg/defdebug debug "degree9:enterprise:hooks")

;; Hook Logging ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn- log-hook [hook]
  (.log js/console hook) hook)
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; Hook Return ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn return-context [context]
  (fn [_] context))

(defn then-context [context prom]
  (.then prom (return-context context)))
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

(defn app [hook]
  (hook-get hook "app"))

(defn method [hook]
  (hook-get hook "method"))

(defn params [hook]
  (hook-get hook "params"))

(defn params! [hook data]
  (hook-set! hook "params" data))

(defn data [hook]
  (hook-get hook "data"))

(defn data! [hook data]
  (hook-set! hook "data" data))

(defn result [hook]
  (hook-get hook "result"))

(defn result! [hook data]
  (hook-set! hook "result" data))
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; Common Request Hooks ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn merge-data
  "Merges the `request.data` with a hashmap."
  [data]
  (fn [hook]
    (doto hook
      (data! (merge (hook-get hook "data") data)))))

(defn merge-params
  "Merges the `request.params` with a hashmap."
  [data]
  (fn [hook]
    (doto hook
      (params! (merge (params hook) data)))))
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; Common Request Hooks ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn populate
  "Populates the entity properties with queries to other collections."
  [props]
  (fn [hook]
    (let [params (params hook)]
      (doto hook
        (params! (merge-with merge params {"query" {"$populate" props}}))))))
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; Default Entity Hooks ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn- default-entity
  "Merges `entity` with `context.data`."
  [entity]
  (fn [{:keys [data] :as context}]
    (doto context
      (obj/set :data (obj/merge entity data)))))
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
