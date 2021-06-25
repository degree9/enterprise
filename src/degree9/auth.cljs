(ns degree9.auth
  (:require
   [javelin.core :as j]
   [meta.client :as client]
   [meta.promise :as prom])
  (:require-macros degree9.auth))

;; Authentication State ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(def authentication (j/cell nil))

(defn user-cell []
  (j/cell= (:user authentication)))

(defn token-cell []
  (j/cell= (:accessToken authentication)))
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; Helper Functions ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn- auth-handler [auth]
  (-> auth
    (prom/then #(js->clj % :keywordize-keys true))
    (prom/then #(reset! authentication %))))
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; Authentication Functions ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn auth! [app]
  (auth-handler (client/auth! app)))

(defn login! [app strategy & [opts]]
  (auth-handler (client/login! app strategy opts)))

(defn logout! [app]
  (-> (client/logout! app)
    (prom/then #(reset! authentication nil))))
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
