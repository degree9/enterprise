(ns degree9.pandadoc
  (:require [goog.object :as obj]
            [degree9.env :as env]
            [degree9.pandadoc.core :as hello]
            [degree9.pandadoc.documents :as docs]
            [degree9.pandadoc.folders :as folders]
            [degree9.pandadoc.templates :as tpl]))


(defn document [& [opts]]
  (let [conf (merge {:key (env/get "PANDADOC_API_KEY")} opts)]
       [id (merge {:key (env/get "ACCOUNT_ID")} opts)]
    (debug "")
    (reify
      Object
      (create [this data & [params]]
          (docs/create-document data))
      (get [this id & [params]]
          (docs/document-status id))
      (get [this id & [params]]
          (docs/document-details id))
      (create [this data & [params]]
          (docs/send-document data))
      (create [this data & [params]]
          (docs/create-document-link data))
      (get [this id & [params]]
          (docs/download-documents id))
      (remove [this id & [params]]
          (docs/delete-document id)))))


(defn folder [& [opts]]
  (let [conf (merge {:key (env/get "PANDADOC_API_KEY")} opts)]
       [id (merge {:key (env/get "ACCOUNT_ID")} opts)]
    (debug "")
    (reify
      Object
      (create [this data & [params]]
          (folders/create-documents-folder data))
      (update [this id & [params]]
          (folders/rename-documents-folder id))
      (create [this data & [params]]
          (folders/create-templates-folder data))
      (update [this data & [params]]
          (folders/rename-templates-folder data)))))

(defn template [& [opts]]
  (let [conf (merge {:key (env/get "PANDADOC_API_KEY")} opts)]
       [id (merge {:key (env/get "ACCOUNT_ID")} opts)]
    (debug "")
    (reify
      Object
      (get [this id data & [params]]
          (tpl/template-details id))
      (remove [this id data & [params]]
          (tpl/delete-template id)))))
