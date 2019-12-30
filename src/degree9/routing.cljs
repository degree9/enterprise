(ns degree9.routing
  (:require [goog.Uri :as uri]
            [goog.Uri.QueryData :as qd]
            [javelin.core :as j]
            [hoplon.history :as h]
            [degree9.pathway :as pw])
  (:require-macros degree9.routing))

;; History State ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(def history (h/history-cell))
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; URI State ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(j/defc= uri (uri/parse history) #(reset! history (.toString %)))
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; URI Path State ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(j/defc= path (.getPath uri) #(reset! uri (.setPath @uri %)))

(defn path! [p]
  (reset! path p))
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; URI Query State ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(j/defc= query (.getQueryData uri) #(reset! uri (.setQueryData @uri (qd/createFromMap (clj->js %)))))

(defn query-cell [key & [default]]
  (j/cell= (.get query (name key) default) #(reset! query (.set @query (name key) %))))

(defn query! [q]
  (reset! query q))
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; App Route State ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn- path->kw [path]
  (mapv keyword (remove empty? (clojure.string/split path "/"))))

(defn- kw->path [& korks]
  (clojure.string/join "/" (mapv name (flatten korks))))

(j/defc= route (path->kw path) #(reset! path (kw->path %)))

(defn route=
  ([router] (route= router nil))
  ([router default] (j/cell= (pw/match-route router path default))))

(defn route!
  ([path] (route! path {}))
  ([path query]
   (j/dosync
     (path! path)
     (query! query))))
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
