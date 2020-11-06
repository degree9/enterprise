(ns degree9.url
  (:require [degree9.browser :as bom]
            [degree9.object :as obj]))

(defn create-url
  ([] (create-url (bom/get-location)))
  ([location] (js/URL. location)))

(defn create-search-params
  ([] (create-search-params (:search (bom/get-location))))
  ([search] (js/URLSearchParams. search)))

(defn search->clj [search]
  (let [entries (:entries search)]
    (js->clj (obj/from-entries entries))))

(defn clj->search [data]
  (js/URLSearchParams. (clj->js data)))
