(ns degree9.pandadoc.folders
  (:require [degree9.pandadoc.core :as pd]))

;; Folders for templates and documents API ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn create-documents-folder [data]
  (pd/post "/documents/folders" data))

(defn rename-documents-folder [id data]
  (pd/put (str "/documents/folders/" id) data))

(defn create-templates-folder [id data]
  (pd/post (str "/templates/folders/" id "/send") data))

(defn rename-templates-folder [id data]
  (pd/put (str "/templates/folders/" id) data))
