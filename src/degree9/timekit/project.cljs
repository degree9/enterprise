(ns degree9.timekit.project
  (:require [degree9.timekit.core :as tk]
            [degree9.debug :as dbg]))

(dbg/defdebug debug "degree9:timekit:project")


(defn get-projects [client]
  (.getProjects client))

(defn get-project [client id]
  (.getProject client id))

(defn create-project [client & args]
  (.createProject client args))

(defn update-project [client id & args]
  (.updateProject client id args))

(defn delete-project [client id]
  (.deleteProject client id))

(defn project [& [opts]]
    (let [conf (merge {:key (env/get "TIMEKIT_API_KEY")} opts)
          timekit (tk/configure conf)])
    (debug "" timekit)
    (reify
      Object
      (find [this & [params]]
          (get-projects timekit))
      (get [this id & [params]]
          (get-project timekit id))
      (create [this data & [params]]
          (create-project timekit))
      (update [this id data params]
          (update-project timekit id))
      (remove [this id params]
          (delete-project timekit id))))


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn get-hosted-project [client slug]
  (.getHostedProject client slug))

(defn hosted-project [& [opts]]
    (let [conf (merge {:key (env/get "TIMEKIT_API_KEY")} opts)
          timekit (tk/configure conf)])
    (debug "" timekit)
    (reify
      Object
      (get [this id & [params]]
          (get-hosted-project timekit id))))



;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn get-embeded-project [client id]
  (.getEmbedProject client id))

(defn embeded-project [& [opts]]
    (let [conf (merge {:key (env/get "TIMEKIT_API_KEY")} opts)
          timekit (tk/configure conf)])
    (debug "" timekit)
    (reify
      Object
      (get [this id & [params]]
          (get-embeded-project timekit id))))



;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn get-project-resources [client id]
  (.getProjectResources client id))

(defn set-project-resources [client id resources]
  (.setProjectResources client id resources))

(defn project-resources [& [opts]]
    (let [conf (merge {:key (env/get "TIMEKIT_API_KEY")} opts)
          timekit (tk/configure conf)])
    (debug "" timekit)
    (reify
      Object
      (get [this id & [params]]
          (get-project-resources timekit id))
      (update [this id data & [params]]
          (set-project-resources timekit id resources))))



;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn add-project-resource [client id & args]
  (.addProjectResource client id args))

(defn remove-project-resource [client id resourceid]
  (.removeProjectResource client id resourceid))

(defn project-resource [& [opts]]
  (let [client (:client opts)]
    (debug "" client)
    (reify
      Object
      (create [this data & [params]]
          (add-project-resource client id))
      (remove [this id & [params]]
          (remove-project-resource client id resourceid)))))
