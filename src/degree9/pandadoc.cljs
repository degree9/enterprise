(ns degree9.pandadoc
  (:require [goog.object :as obj]
            [degree9.env :as env]
            [degree9.debug :as dbg]
            [degree9.pandadoc.core :as hello]
            [degree9.pandadoc.documents :as docs]
            [degree9.pandadoc.folders :as folders]
            [degree9.pandadoc.templates :as tpl]))

(dbg/defdebug debug "degree9:enterprise:pandadoc")

(defn document [& [opts]]
  (let [conf (merge {:key (env/get "PANDADOC_API_KEY")} opts)]
       [id (merge {:key (env/get "ACCOUNT_ID")} opts)]
    (debug "")
    (reify
      Object
      (find [this & [params]]
        (docs/list-documents))
      (get [this id & [params]]
        (docs/document-status id))
      (create [this data & [params]]
        (docs/create-document data))
      (remove [this id & [params]]
        (docs/delete-document id)))))

(defn document-details [& [opts]]
  (let [conf (merge {:key (env/get "PANDADOC_API_KEY")} opts)]
       [id (merge {:key (env/get "ACCOUNT_ID")} opts)]
    (debug "")
    (reify
      Object
      (get [this id & [params]]
        (docs/document-details id)))))

(defn document-send [& [opts]]
  (let [conf (merge {:key (env/get "PANDADOC_API_KEY")} opts)]
       [id (merge {:key (env/get "ACCOUNT_ID")} opts)]
    (debug "")
    (reify
      Object
      (update [this id data & [params]]
        (docs/send-document id data)))))

(defn document-download [& [opts]]
  (let [conf (merge {:key (env/get "PANDADOC_API_KEY")} opts)]
       [id (merge {:key (env/get "ACCOUNT_ID")} opts)]
    (debug "")
    (reify
      Object
      (get [this id & [params]]
        (docs/download-document id)))))

(defn document-protected-download [& [opts]]
  (let [conf (merge {:key (env/get "PANDADOC_API_KEY")} opts)]
       [id (merge {:key (env/get "ACCOUNT_ID")} opts)]
    (debug "")
    (reify
      Object
      (get [this id & [params]]
        (docs/download-protected-document id)))))

(defn document-folder [& [opts]]
  (let [conf (merge {:key (env/get "PANDADOC_API_KEY")} opts)]
       [id (merge {:key (env/get "ACCOUNT_ID")} opts)]
    (debug "")
    (reify
      Object
      (find [this & [params]]
        (folders/list-documents-folder))
      (create [this data & [params]]
        (folders/create-documents-folder data))
      (patch [this id data & [params]]
        (folders/rename-documents-folder id data)))))

(defn template-folder [& [opts]]
  (let [conf (merge {:key (env/get "PANDADOC_API_KEY")} opts)]
       [id (merge {:key (env/get "ACCOUNT_ID")} opts)]
    (debug "")
    (reify
      Object
      (find [this & [params]]
        (folders/list-templates-folder))
      (create [this data & [params]]
        (folders/create-templates-folder data))
      (patch [this id data & [params]]
        (folders/rename-templates-folder id data)))))

(defn template [& [opts]]
  (let [conf (merge {:key (env/get "PANDADOC_API_KEY")} opts)]
       [id (merge {:key (env/get "ACCOUNT_ID")} opts)]
    (debug "")
    (reify
      Object
      (remove [this id data & [params]]
          (tpl/delete-template id)))))

(defn template-details [& [opts]]
  (let [conf (merge {:key (env/get "PANDADOC_API_KEY")} opts)]
       [id (merge {:key (env/get "ACCOUNT_ID")} opts)]
    (debug "")
    (reify
      Object
      (get [this id & [params]]
        (docs/template-details id)))))
