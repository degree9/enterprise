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
  (target/dispatchEvent target event))
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; Event Objects ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn create-event [event type init]
  (event. (get types type) (clj->js init)))

(defmulti event! (fn [type init] type))

(defmethod event! :default
  [event init]
  (create-event js/Event. event init))

(defmethod event! :popstate
  [event init]
  (create-event js/PopStateEvent. event {:state init}))
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
