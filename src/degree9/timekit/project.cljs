(ns degree9.timekit.project
  (:require [degree9.timekit.core :as tk]))


(defn get-projects [client]
  (.getProjects client))

(defn get-projects [client id]
  (.getProject client id))

(defn get-hosted-projects [client slug]
  (.getHostedProject client slug))

(defn get-embed-projects [client id]
  (.getEmbedProject client id))

(defn create-project [client & args]
  (.createProject client args))

(defn update-project [client id & args]
  (.updateProject client id args))

(defn delete-project [client id]
  (.deleteProject client id))

(defn get-project-resources [client id]
  (.getProjectResources client id))

(defn add-project-resource [client id & args]
  (.addProjectResource client id args))

(defn set-project-resources [client id resources]
  (.setProjectResources client id resources))

(defn remove-project-resource [client id resourceid]
  (.removeProjectResource client id resourceid))
