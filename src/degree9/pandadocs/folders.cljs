(ns degree9.pandadocs.folders
  :require [degree9.pandadoc.core :as pd])



;; Folders for templates and documents API ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn create-documents-folder [data]
  (pd/post "/documents/folders" data))

(defn rename-documents-folder [data]
  (pd/put (str "/documents/folders/documents_folder_uuid" data)))

(defn create-templates-folder [id data]
  (pd/post (str "/templates/folders" id "/send") data))

(defn rename-templates-folder [data]
  (pd/put (str "/templates/folders/templates_folder_uuid" data)))

(defn folder [& [opts]]
  (let [conf (merge {:key (env/get "PANDADOC_API_KEY")} opts)]
       [id (merge {:key (env/get "ACCOUNT_ID")} opts)]
    (debug "")
    (reify
      Object
      (create [this data & [params]]
          (create-documents-folder data))
      (update [this id & [params]]
          (rename-documents-folder id))
      (create [this data & [params]]
          (create-templates-folder data))
      (update [this data & [params]]
          (rename-templates-folder data)))))
