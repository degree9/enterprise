(ns degree9.mongodb
  (:require [degree9.object :as obj]
            [degree9.env :as env]
            [degree9.debug :as dbg]
            [meta.server :as server]
            ["mongoose" :as mongoose]
            ["feathers-mongoose" :as mongodb]))

(dbg/defdebug debug "degree9:enterprise:mongodb")

;; Mongoose Schema Types ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(def SchemaTypes (obj/get-in mongoose ["Schema" "Types"]))
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; MongoDB Functions ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn connect
  "Connect to MongoDB. Converts clj->js internally."
  [conn & [opts]]
  (debug "Connecting to MongoDB" conn opts)
  (.connect mongoose conn (clj->js opts)))

(defn mkconnection
  "Create a connection instance to MongoDB."
  [conn & [opts]]
  (debug "Creating connection instance to MongoDB" conn opts)
  (.createConnection mongoose conn (clj->js opts)))

(defn schema
  "Create a Mongoose Schema. Converts clj->js internally."
  [data & [opts]]
  (debug "Creating Mongoose Schema" data opts)
  (let [schema (obj/get mongoose "Schema")]
    (schema. (clj->js data) (clj->js opts))))

(defn model
  "Create a Mongoose Model."
  ([conn name schema]
   (debug "Creating Mongoose Model '%s' with schema:" name schema)
   (.model conn name schema))
  ([name schema]
   (model mongoose name schema)))

(defn api
  "Create a feathers service backed by MongoDB."
  ([app path opts hooks]
   (debug "Initializing MongoDB collection service with options %s" opts)
   (server/api app path (mongodb (clj->js opts)) hooks))
  ([app path db-model db-schema hooks]
   (api app path {:Model (model db-model (schema db-schema))} hooks))
  ([app path db-model db-schema schema-opts hooks]
   (api app path {:Model (model db-model (schema db-schema schema-opts))} hooks))
  ([app path db-conn db-model db-schema schema-opts hooks]
   (api app path {:Model (model db-conn db-model (schema db-schema schema-opts))} hooks)))
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; D9 Public Functions ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn connect! [& [opts]]
  (let [user (env/require "MONGODB_USERNAME")
        pass (env/require "MONGODB_PASSWORD")
        conn (env/require "MONGODB_CONNECTION")]
    (connect conn
      (merge opts
        {:auth {:user user :password pass}
         :useNewUrlParser true
         :useUnifiedTopology true}))))
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
