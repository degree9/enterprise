(ns degree9.hellosign.embedded
  (:require [degree9.hellosign.core :as hello]))

(def embedded hello/embedded)

(defn edit-url [id]
  (.getEditUrl embedded id))
