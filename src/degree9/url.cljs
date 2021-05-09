(ns degree9.url
  (:require [degree9.object :as obj]
            [degree9.browser.location :as loc]))


(defn create-url
  ([] (create-url (loc/location)))
  ([location] (js/URL. location)))

(defn create-search-params
  ([] (create-search-params (:search (loc/location))))
  ([search] (js/URLSearchParams. search)))

(defn search->clj
  ([search] (search->clj search :keywordize-keys false))
  ([search & {:keys [keywordize-keys]}]
   (let [entries (.entries search)]
     (js->clj (obj/from-entries entries) :keywordize-keys keywordize-keys))))

(defn clj->search [data]
  (js/URLSearchParams. (clj->js data)))

;; URLSearchParams Protocols ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(extend-type js/URLSearchParams
  ILookup
  (-lookup
   ([o k] (.get o (name k)))
   ([o k default] (or (.get o (name k)) default))))
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
