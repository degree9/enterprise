(ns degree9.hellosign.client
  (:require ["hellosign-embedded" :as hello]))

(defn hellosign [opts]
  (hello. (clj->js opts)))
