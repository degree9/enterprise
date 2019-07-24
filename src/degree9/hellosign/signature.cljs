(ns degree9.hellosign.signature
  (:require [degree9.hellosign.core :as hello]))

(def signature hello/signature-request)

(defn create-embedded-request [opts]
  (.createEmbeddedWithTemplate signature (clj->js opts)))

(defn get-embedded-request [id]
  (.get signature id))

(defn get-files [id & [opts]]
  (.download signature id opts))
