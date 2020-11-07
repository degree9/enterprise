(ns degree9.events
  (:require [degree9.events.target :as target]))

;; Event Types ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(def types
  {:popstate "popstate"})
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; Event Target API ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn listen [target type listener]
  (target/addEventListener target (get types type) listener))

(defn unlisten [target type listener]
  (target/removeEventListener target (get types type) listener))

(defn dispatch! [target event]
  (target/dispatchEvent target event))
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; Event Objects ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn create-event
  ([type init] (create-event js/Event type init))
  ([event type init] (event. (get types type) (clj->js init))))

(defn popstate [state]
  (create-event js/PopStateEvent :popstate {:state state}))
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
