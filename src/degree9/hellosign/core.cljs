(ns degree9.hellosign.core
  (:require [goog.object :as obj]
            [degree9.env :as env]
            ["hellosign-sdk" :as hello]))

(def hellosign (hello #js{:key (env/get "HELLOSIGN_API_KEY")}))

(def template (obj/get hellosign "template"))

(def signature-request (obj/get hellosign "signatureRequest"))

(def embedded (obj/get hellosign "embedded"))
