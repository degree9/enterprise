(ns degree9.pandadoc.folders
  (:require [degree9.pandadoc.core :as pd]))

;; Folders for templates and documents API ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn create-documents-folder [data]
  (pd/post (str "/documents/folders" data)))

(defn rename-documents-folder [id data]
  (pd/put (str "/documents/folders/" id) data))

(defn list-documents-folder []
  (pd/get (str "/documents/folders/")))

(defn create-templates-folder [data]
  (pd/post (str "/templates/folders/" data)))

(defn rename-templates-folder [id data]
  (pd/put (str "/templates/folders/" id) data))

(defn list-templates-folder []
  (pd/get (str "/templates/folders/")))
