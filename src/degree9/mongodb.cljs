(ns degree9.mongodb
  (:require [cljs.nodejs :as node]
            [goog.object :as obj]
            [meta.server :as server]))

;; MongoDB ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(def mongoose (node/require "mongoose"))

(def schema (obj/get mongoose "Schema"))

(def mongodb (node/require "feathers-mongoose"))
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; MongoDB Functions ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn connect [conn & [opts]]
  (.connect mongoose conn opts))

(defn mkconnection [conn & [opts]]
  (.createConnection mongoose conn opts))

(defn mkschema [data]
  (schema. data))

(defn model
  ([conn name schema]
   (.model conn name schema))
  ([name schema]
   (model mongoose name schema)))

(defn api [app path model hooks]
  (server/api app path (mongodb #js{:Model model}) hooks))
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
