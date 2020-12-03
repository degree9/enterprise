(ns degree9.events
  (:require [degree9.events.target :as target]))

;; Event Types ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(def types
  {:popstate "popstate"
   :resize   "resize"})
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; Event Target API ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn listen [target event listener]
  (target/addEventListener target (get types event (name event)) listener))

(defn unlisten [target event listener]
  (target/removeEventListener target (get types event (name event)) listener))

(defn dispatch! [target event]
  (target/dispatchEvent target (get types event (name event))))
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; Event Objects ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn create-event
  ([type init] (create-event js/Event type init))
  ([event type init] (event. (get types type) (clj->js init))))

(defn popstate [state]
  (create-event js/PopStateEvent :popstate {:state state}))
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
