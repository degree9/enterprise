(ns degree9.env
  "Load configuration variables from process.env or .env file."
  (:refer-clojure :exclude [require get keys])
  (:require ["fs" :as fs]
            ["path" :as path]
            [degree9.string :as cstr]
            [degree9.debug :as dbg]
            [goog.object :as obj]
            [goog.string :as gstring]
            [goog.string.format]))

(dbg/defdebug debug "degree9:enterprise:env")

;; Env Helpers ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn- read-file [path]
  (debug "Reading file from path %s" path)
  (.readFileSync fs path #js{:encoding "utf8"}))

(defn- env-file [dir]
  (debug "Resolving path to .env file")
  (.resolve path dir ".env"))

(defn- split-kv [kvstr]
  (debug "Split key/value pair from %s" kvstr)
  (cstr/split kvstr #"=" 2))

(defn- split-config [config]
  (debug "Split lines from .env")
  (map split-kv (cstr/split-lines config)))

(defn- dot-env [path]
  (debug "Checking for .env file within %s" path)
  (let [env (env-file path)]
    (when (.existsSync fs env)
      (debug "Loading .env file from %s" env)
      (->> (read-file env)
           (split-config)
           (into {})))))

(defn- node-env [env]
  (debug "Loading node environment" env)
  (->> (js-keys env)
       (map (fn [key] [key (obj/get env key)]))
       (into {})))

(defn- populate-env! [env]
  (debug "Populate the node environment with %s" env)
  (doseq [[k v] (into [] env)]
    (obj/set js/process.env k v)))
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; Env Public API ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn env-vars
  "Return a map of all environment variables."
  ([] (env-vars js/process.env))
  ([env] (node-env env)))

(defn get
  "Return the value for `key` in environment or `default`."
  ([key] (get key nil))
  ([key default] (get js/process.env key default))
  ([env key default] (obj/get env key default)))

(defn require
  "Return the value for `key` in environment or throw `message`."
  ([key] (require key (gstring/format "Environment Variable is missing. (%s)" key)))
  ([key message] (require js/process.env key message))
  ([env key message] (if-let [val (obj/get env key)] val (throw (js/Error. message key)))))

(defn keys
  "Return all keys from the environment."
  ([] (keys js/process.env))
  ([env] (js->clj (js-keys env))))

(defn init!
  "Initialize environment with variables from .env file."
  ([] (init! {:path (.cwd js/process) :env js/process.env}))
  ([{:keys [path env]}]
   (populate-env!
     (merge (dot-env path)
            (node-env env)))))
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
