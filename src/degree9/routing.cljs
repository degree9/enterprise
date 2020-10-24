(ns degree9.routing
  (:require [goog.Uri :as uri]
            [goog.Uri.QueryData :as qd]
            [javelin.core :as j]
            [hoplon.history :as h]
            [degree9.string :as str])
            ;[degree9.pathway :as pw])
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
(defn- clj->query [data]
  (qd/createFromMap (clj->js data)))

(j/defc= query (.getQueryData uri) #(reset! uri (.setQueryData @uri (clj->query %))))

(defn query-cell [key & [default]]
  (j/cell= (.get query (name key) default) #(reset! query (.set @query (name key) %))))

(defn query! [q]
  (reset! query q))
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; App Route State ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn- path->kw [path]
  (mapv keyword (remove empty? (str/split path "/"))))

(defn- kw->path [& korks]
  (str/join "/" (mapv name (flatten korks))))

(j/defc= route (path->kw path) #(reset! path (kw->path %)))

; (defn router
;   ([routes] (router routes nil))
;   ([routes default] (j/cell= (pw/match-route routes path default))))

(defn route!
  ([path] (route! path {}))
  ([path query]
   (j/dosync
     (path! path)
     (query! query))))
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
