(ns degree9.multipart-form
  (:require [goog.object :as obj]
            [meta.server :as svr]
            ["multer" :as multer]))

;; HelloSign Multipart Forms ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn- multipart [& opts]
  (multer. (clj->js opts)))

(defn none []
  (.none (multipart {})))

(defn- req-files [req]
  (obj/get req "files"))

(defn- req-body [req]
  (obj/get req "body"))

(defn- req-feathers [req]
  (obj/get req "feathers"))

(defn- multipart-files! [fs files]
  (obj/set fs "files" files))

(defn- multipart-body! [fs body]
  (obj/set fs "multipart" body))

(defn- multipart-files [req res next]
  (multipart-files! (req-feathers req) (req-files req))
  (next))

(defn- multipart-body [req res next]
  (multipart-body! (req-feathers req) (req-body req))
  (next))

(defn multipart-service [app path multipart service & [hooks]]
  (let [app (.use app path multipart multipart-body service)]
    (doto (.service app path)
      (.hooks (clj->js hooks)))
    app))

(defn multipart-none [app path service & [hooks]]
  (multipart-service app path (none) service hooks))
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
