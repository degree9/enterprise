(ns degree9.pandadoc.documents
  (:require [degree9.pandadoc.core :as pd]))



;; Documents API ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn list-documents []
  (pd/get "/documents"))

(defn create-document [data]
  (pd/post "/documents" data))

(defn document-status [id]
  (pd/get (str "/documents/" id)))

(defn delete-document [id]
  (pd/delete (str "/documents/" id)))

(defn document-details [id]
  (pd/get (str "/documents/" id "/details")))

(defn send-document [id data]
  (pd/post (str "/documents/" id "/send") data))

(defn create-document-link [id data]
  (pd/post (str "/documents/" id "/session") data))

(defn download-document [id]
  (pd/get (str "/documents/" id "/download")))

(defn download-protected-document [id]
  (pd/get (str "/documents/" id "/download-protected")))
