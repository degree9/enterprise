(ns degree9.state
  (:require [hoplon.core :as h]
            [javelin.core :as j]
            [degree9.debug :as dbg])
  (:require-macros degree9.state))

(dbg/defdebug debug "degree9:enterprise:state")

;; State Helpers ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn init-state
  ([] (j/cell nil))
  ([state] (j/cell state)))

(defn- alt-state
  "Returns a lense containing `alt` or `default`."
  [default]
  (let [alt (j/cell nil)]
    (j/cell= (or alt default) #(reset! alt %))))

(defn- keyed-state
  "Returns a lense containing `key` state or `alt`."
  [state key & alt]
  (j/cell= (when (map? state) (get-in state [key] alt))
    #(swap! assoc-in state [key] %)))

(defn- func-state
  "Returns a lense containing `func` applied to state."
  [state func]
  (j/cell= (func state) #(reset! state %)))
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; State Functions ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn- loading-state [data]
  (alt-state (keyed-state data :loading)))

(defn- setup-state [data]
  (alt-state (keyed-state data :setup)))

(defn- finished-state [data]
  (alt-state (keyed-state data :finished)))

(defn- success-state [data]
  (alt-state (keyed-state data :success)))

(defn- error-state [data]
  (alt-state (keyed-state data :error)))

(defn- empty-state [data]
  (alt-state (func-state data empty?)))

(defn- current-state [data loading setup finished success error empty]
  (j/cell=
    (cond
      loading  ::loading
      setup    ::setup
      finished ::finished
      success  ::success
      error    ::error
      empty    ::empty
      data     ::data)))
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; State Cells ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(def ^:dynamic *data*     (init-state))

(def ^:dynamic *loading*  (loading-state *data*))

(def ^:dynamic *setup*    (setup-state *data*))

(def ^:dynamic *finished* (finished-state *data*))

(def ^:dynamic *success*  (success-state *data*))

(def ^:dynamic *error*    (error-state   *data*))

(def ^:dynamic *empty*    (empty-state   *data*))

(def ^:dynamic *state*    (current-state *data* *loading* *setup* *finished*
                                         *success* *error* *empty*))
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; State Debug ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(def ^:dynamic *debug* (atom nil))

(defn debug! []
  (reset! *debug* true))
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
