(ns degree9.hellosign.signature
  (:require [degree9.hellosign.core :as hello]))

(def signature hello/signature-request)

(defn get-signature [id]
  (.get signature id))

(defn list-signature []
  (.list signature))

(defn send-signature [opts]
  (.send signature (clj->js opts)))

(defn send-template [opts]
  (.sendWithTemplate signature id))

(defn send-reminder [id & [opts]]
  (.remind signature id (clj->js opts)))

(defn update-signature [id & [opts]]
  (.update signature id))

(defn cancel-incomplete-signature [id & [opts]]
  (.cancel signature id))

(defn remove-signature-access [id & [opts]]
  (.remove signature id))

(defn get-files [id]
  (.files signature id))

(defn create-embedded [id & [opts]]
  (.createEmbedded signature id (cljs->js opts)))

(defn create-embedded-template [id & [opts]]
  (.createEmbeddedWithTemplate signature id (cljs->js opts)))

(defn release-hold [id & [opts]]
  (.releaseHold signature id))

(defn download [id & [opts]]
  (.download signature id (clj->js opts)))
