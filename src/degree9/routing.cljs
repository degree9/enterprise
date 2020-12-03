(ns degree9.routing
  (:require [javelin.core :as j]
            [degree9.string :as str]
            [degree9.browser.window :as win]
            [degree9.browser.document :as doc]
            [degree9.browser.history :as history]
            [degree9.browser.location :as loc]
            [degree9.events :as events]
            [degree9.url :as url])
  (:require-macros degree9.routing))

;; HTML5 History State ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn- state-cell []
  (let [history (j/cell (:state (history/history)))]
    (j/with-let [history= (j/cell= history (partial reset! history))]
      (win/listen :popstate
        (fn [event] (reset! history= (:state event)))))))

(def state (state-cell))
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; URL ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn- url-cell []
  (let [url (j/cell (url/create-url))]
    (j/with-let [url= (j/cell= url (partial reset! url))]
      (win/listen :popstate
        (fn [event] (reset! url= (url/create-url)))))))

(def url (url-cell))
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; URLSearchParams ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn- search-cell []
  (let [params (j/cell (url/create-search-params))]
    (j/with-let [params= (j/cell= params (partial reset! params))]
      (win/listen :popstate
        (fn [event] (reset! params= (url/create-search-params)))))))

(def search (search-cell))
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; URL Pathname ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn- path-cell []
  (let [path (j/cell (:pathname (loc/location)))]
    (j/with-let [path= (j/cell= path (partial reset! path))]
      (win/listen :popstate
        (fn [event] (reset! path= (:pathname (loc/location))))))))

(def path (path-cell))
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; Routing Public API ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn route!
  "Provides client (SPA) routing without reloading the page."
  ([path] (route! path nil))
  ([path query] (route! path query @state))
  ([path query state]
   (let [title  (:title (doc/document))
         search (url/clj->search query)
         token  (if query (str/join "?" [path (str search)]) path)]
     (j/dosync
       (history/push-state! state title token)
       (history/popstate! state)))))
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
