(ns degree9.pandadocs.documents
  :require [degree9.pandadoc.core :as pd])



;; Documents API ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn create-document [data]
  (pd/post "/documents" data))

(defn document-status [id]
  (pd/get (str "/documents/" id)))

(defn document-details [id]
  (pd/get (str "/documents/" id "/details")))

(defn send-document [id data]
  (pd/post (str "/documents/" id "/send") data))

(defn create-document-link [id data]
  (pd/post (str "/documents/" id "/session") data))

(defn download-documents [id]
  (pd/get (str "/documents/" id "/download")))

(defn delete-documents [id]
  (pd/delete (str "/documents/" id)))

(defn download-protected-document [id]
  (pd/get (str "/documents/" id "/download-protected")))


(defn document [& [opts]]
  (let [conf (merge {:key (env/get "PANDADOC_API_KEY")} opts)]
       [id (merge {:key (env/get "ACCOUNT_ID")} opts)]
    (debug "")
    (reify
      Object
      (create [this data & [params]]
          (create-document data))
      (get [this id & [params]]
          (document-status id))
      (get [this id & [params]]
          (document-details id))
      (create [this data & [params]]
          (send-document data))
      (create [this data & [params]]
          (create-document-link data))
      (get [this id & [params]]
          (download-documents id))
      (remove [this id & [params]]
          (delete-document id)))))
