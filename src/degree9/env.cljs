(ns degree9.env
  "Load configuration variables from process.env or .env file."
  (:refer-clojure :exclude [get keys])
  (:require ["fs" :as fs]
            ["path" :as path]
            [goog.object :as obj]
            [clojure.string :as cstr]
            [degree9.debug :as dbg]))


(dbg/defdebug debug "degree9:enterprise:env")

;; Env Helpers ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn- read-file [path]
  (when (.existsSync fs path)
    (.readFileSync fs path #js{:encoding "utf8"})))

(defn- env-file [dir]
  (.resolve path dir ".env"))

(defn- split-kv [kvstr]
  (cstr/split kvstr #"=" 2))

(defn- split-config [config]
  (->> (cstr/split-lines config)
       (map split-kv)
       (into {})))

(defn- dot-env [path]
  (-> (env-file path)
      (read-file)
      (split-config)))

(defn- node-env [env]
  (->> (js-keys env)
       (map (fn [key] [key (obj/get env key)]))
       (into {})))

(defn- populate-env! [env]
  (doseq [[k v] env]
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
