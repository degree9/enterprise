(ns degree9.hubspot
  (:require [goog.object :as obj]
            [degree9.env :as env]
            [degree9.hubspot.companies]
            [degree9.hubspot.contacts]))

;; Hubspot Companies ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn companies [& [opts]]
  (let [conf (merge {:key (env/get "HUBSPOT_API_KEY")} opts)
        hubspot nil]
    (reify Object
      (get [this id & [params]])
        ;(tpl/get-template hello id))
      (create [this data & [params]]))))
        ;(tpl/create-embedded-draft hello data)))))
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; Hubspot Contacts ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn contacts [& [opts]]
  (let [conf (merge {:key (env/get "HUBSPOT_API_KEY")} opts)
        hubspot nil]
    (reify Object
      (get [this id & [params]])
        ;(tpl/get-template hello id))
      (create [this data & [params]]))))
        ;(tpl/create-embedded-draft hello data)))))
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; Hubspot Deals ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn deals [& [opts]]
  (let [conf (merge {:key (env/get "HUBSPOT_API_KEY")} opts)
        hubspot nil]
    (reify Object
      (get [this id & [params]])
        ;(tpl/get-template hello id))
      (create [this data & [params]]))))
        ;(tpl/create-embedded-draft hello data)))))
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; Hubspot Line Items ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn lineitem [& [opts]]
  (let [conf (merge {:key (env/get "HUBSPOT_API_KEY")} opts)
        hubspot nil]
    (reify Object
      (get [this id & [params]])
        ;(tpl/get-template hello id))
      (create [this data & [params]]))))
        ;(tpl/create-embedded-draft hello data)))))
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; Hubspot Products ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn products [& [opts]]
  (let [conf (merge {:key (env/get "HUBSPOT_API_KEY")} opts)
        hubspot nil]
    (reify Object
      (get [this id & [params]])
        ;(tpl/get-template hello id))
      (create [this data & [params]]))))
        ;(tpl/create-embedded-draft hello data)))))
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; Hubspot Tickets ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn tickets [& [opts]]
  (let [conf (merge {:key (env/get "HUBSPOT_API_KEY")} opts)
        hubspot nil]
    (reify Object
      (get [this id & [params]])
        ;(tpl/get-template hello id))
      (create [this data & [params]]))))
        ;(tpl/create-embedded-draft hello data)))))
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; Hubspot Quotes ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn quotes [& [opts]]
  (let [conf (merge {:key (env/get "HUBSPOT_API_KEY")} opts)
        hubspot nil]
    (reify Object
      (get [this id & [params]])
        ;(tpl/get-template hello id))
      (create [this data & [params]]))))
        ;(tpl/create-embedded-draft hello data)))))
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
