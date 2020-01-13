(ns degree9.hellosign.embedded
  (:require [degree9.hellosign.core :as hello]))

(defn get-edit-url [emb id]
  (.getEditUrl emb id))

(defn get-sign-url [emb id]
  (.getSignUrl emb id))
