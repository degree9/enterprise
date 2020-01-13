(ns degree9.hellosign.templates
  (:require [degree9.hellosign.core :as hello]))

(defn create-embedded-draft [tpl opts]
  (.createEmbeddedDraft tpl (clj->js opts)))

(defn list-templates [tpl]
  (.list tpl))

(defn get-template [tpl id]
  (.get tpl id))

(defn delete-template [tpl id]
  (.delete tpl id))

(defn get-template-files [tpl id]
  (.files tpl id))

(defn update-template-files [tpl id]
  (.update tpl id))
