(ns degree9.browser
  (:require [degree9.object :as obj]))

(defn get-window []
  js/window)

(defn get-document []
  (:document (get-window)))

(defn get-location []
  (:location (get-window)))

(defn get-history []
  (:history (get-window)))

(defn local-storage []
  (:localStorage (get-window)))

(defn session-storage []
  (:sessionStorage (get-window)))
