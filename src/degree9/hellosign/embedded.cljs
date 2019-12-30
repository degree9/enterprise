(ns degree9.hellosign.embedded
  (:require [degree9.hellosign.core :as hello]))

(def embedded hello/embedded)

(defn get-edit-url [id]
  (.getEditUrl embedded id))

(defn get-sign-url [id]
  (.getSignUrl embedded id))
