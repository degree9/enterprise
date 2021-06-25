(ns degree9.array
  (:refer-clojure :exclude [conj])
  (:require [goog.array :as arr]))

(defn conj [a o]
  (doto a (arr/insert (clj->js o))))

;; JS Object Protocols ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(extend-type array
  ICollection
  (-conj [a o] (conj a o)))
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
