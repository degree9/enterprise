(ns degree9.routing
  (:require [hoplon.history :as history]
            [javelin.core :as j]
            [clojure.string :as string]
            [clojure.spec.alpha :as spec])
  (:require-macros degree9.routing))

(def route (history/history-cell))

(defn- split-route [route]
  (string/split route "/"))

(defn- join-route [parent route]
  (string/join "/" [parent route]))

(defn local-route
  ([] (local-route route))
  ([route] (local-route route (j/cell nil)))
  ([route local] (j/cell= (or local route) (partial reset! local))))

(defn spec-route
  ([spec] (spec-route route spec))
  ([route spec] (j/cell= (when (spec/valid? spec (split-route route)) route))))

(defn route=
  ([r] (route= route r))
  ([route r] (j/cell= (= route r))))

(defn route!
  ([path] (route! route path))
  ([route path] (reset! route path)))

(defn sub-route!
  ([path] (sub-route! route path))
  ([route path] (swap! route #(join-route % path))))
