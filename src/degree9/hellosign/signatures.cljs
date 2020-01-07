(ns degree9.hellosign.signatures
  (:require [degree9.hellosign.core :as hello]))

(defn get-signature [sig id]
  (.get sig id))

(defn list-signature [sig]
  (.list sig))

(defn send-signature [sig id & [opts]]
  (.send sig id (clj->js opts)))

(defn send-template [sig id & [opts]]
  (.sendWithTemplate sig id (clj->js opts)))

(defn send-reminder [sig id & [opts]]
  (.remind sig id (clj->js opts)))

(defn update-signature [sig id & [opts]]
  (.update sig id (clj->js opts)))

(defn cancel-incomplete-signature [sig id & [opts]]
  (.cancel sig id (clj->js opts)))

(defn remove-signature-access [sig id & [opts]]
  (.remove sig id (clj->js opts)))

(defn get-files [sig id]
  (.files sig id))

(defn create-embedded [sig & [opts]]
  (.createEmbedded sig (clj->js opts)))

(defn create-embedded-template [sig & [opts]]
  (.createEmbeddedWithTemplate sig (clj->js opts)))

(defn release-hold [sig id & [opts]]
  (.releaseHold sig id (clj->js opts)))

(defn download [sig id & [opts]]
  (.download sig id (clj->js opts)))
