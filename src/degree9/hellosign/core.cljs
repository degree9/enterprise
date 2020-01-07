(ns degree9.hellosign.core
  (:require [goog.object :as obj]
            ["hellosign-sdk" :as hello]))

(defn hellosign [& [opts]]
  (hello (clj->js (or opts {}))))

(defn template [hello] (obj/get hello "template"))

(defn signature-request [hello] (obj/get hello "signatureRequest"))

(defn embedded [hello] (obj/get hello "embedded"))
