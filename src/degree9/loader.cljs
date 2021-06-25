(ns degree9.loader
  (:require-macros [degree9.loader])
  (:require [shadow.lazy :as lazy]
            [javelin.core :as j]
            [hoplon.core :as h]))

(defn- async-element []
  (.createElement js/document "async"))

(defn- replace-async [async]
  (fn [elem]
    (.appendChild async (elem))))

(defn load-component [component]
  (j/with-let [elem (async-element)]
    (-> (lazy/load component)
        (.then (replace-async elem)))))
