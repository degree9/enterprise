(ns degree9.hellosign.signature
  (:require [degree9.hellosign.core :as hello]))

(def signature hello/signature-request)

(defn get-signature [id]
  (.get signature id))

(defn list-signature []
  (.list signature))

(defn send-signature [id & [opts]]
  (.send signature id (clj->js opts)))

(defn send-template [id & [opts]]
  (.sendWithTemplate signature id (clj->js opts)))

(defn send-reminder [id & [opts]]
  (.remind signature id (clj->js opts)))

(defn update-signature [id & [opts]]
  (.update signature id (clj->js opts)))

(defn cancel-incomplete-signature [id & [opts]]
  (.cancel signature id (clj->js opts)))

(defn remove-signature-access [id & [opts]]
  (.remove signature id (clj->js opts)))

(defn get-files [id]
  (.files signature id))

(defn create-embedded [id & [opts]]
  (.createEmbedded signature id (cljs->js opts)))

(defn create-embedded-template [id & [opts]]
  (.createEmbeddedWithTemplate signature id (cljs->js opts)))

(defn release-hold [id & [opts]]
  (.releaseHold signature id (clj->js opts)))

(defn download [id & [opts]]
  (.download signature id (clj->js opts)))
