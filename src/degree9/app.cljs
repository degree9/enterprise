(ns degree9.app
  (:require [javelin.core :as j]))

(j/defc context {})

(def context! (partial swap! context))
