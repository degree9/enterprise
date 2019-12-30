(ns degree9.hellosign.template
  (:require [degree9.hellosign.core :as hello]))

(def template hello/template)

(defn create-embedded-draft [opts]
  (.createEmbeddedDraft template (clj->js opts)))

(defn list-template []
  (.list template))

(defn get-template [id]
  (.get template id))
