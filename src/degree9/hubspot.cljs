(ns degree9.hubspot
  (:require [goog.object :as obj]
            [degree9.env :as env]
            ["@hubspot/api-client" :as hs]))

;; Hubspot  ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn hubspot [opts]
  (let [client (obj/get hs "Client")]
    (client. (clj->js opts))))
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; Hubspot Companies ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn companies [& [opts]]
  (let [conf (merge {:apiKey (env/get "HUBSPOT_API_KEY")} opts)
        client (hubspot conf)
        api (.. client -crm -companies -basicApi)]
    (reify Object
      (find [this & [params]]
        (.getPage api))
      (get [this id & [params]])
        ;(tpl/get-template hello id))
      (create [this data & [params]]
        (.create api data)))))
        ;(tpl/create-embedded-draft hello data)))))
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; Hubspot Contacts ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn contacts [& [opts]]
  (let [conf (merge {:apiKey (env/get "HUBSPOT_API_KEY")} opts)
        hubspot nil]
    (reify Object
      (get [this id & [params]])
        ;(tpl/get-template hello id))
      (create [this data & [params]]))))
        ;(tpl/create-embedded-draft hello data)))))
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; Hubspot Deals ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn deals [& [opts]]
  (let [conf (merge {:apiKey (env/get "HUBSPOT_API_KEY")} opts)
        hubspot nil]
    (reify Object
      (get [this id & [params]])
        ;(tpl/get-template hello id))
      (create [this data & [params]]))))
        ;(tpl/create-embedded-draft hello data)))))
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; Hubspot Line Items ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn lineitem [& [opts]]
  (let [conf (merge {:apiKey (env/get "HUBSPOT_API_KEY")} opts)
        hubspot nil]
    (reify Object
      (get [this id & [params]])
        ;(tpl/get-template hello id))
      (create [this data & [params]]))))
        ;(tpl/create-embedded-draft hello data)))))
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; Hubspot Products ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn products [& [opts]]
  (let [conf (merge {:apiKey (env/get "HUBSPOT_API_KEY")} opts)
        hubspot nil]
    (reify Object
      (get [this id & [params]])
        ;(tpl/get-template hello id))
      (create [this data & [params]]))))
        ;(tpl/create-embedded-draft hello data)))))
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; Hubspot Tickets ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn tickets [& [opts]]
  (let [conf (merge {:apiKey (env/get "HUBSPOT_API_KEY")} opts)
        hubspot nil]
    (reify Object
      (get [this id & [params]])
        ;(tpl/get-template hello id))
      (create [this data & [params]]))))
        ;(tpl/create-embedded-draft hello data)))))
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; Hubspot Quotes ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn quotes [& [opts]]
  (let [conf (merge {:apiKey (env/get "HUBSPOT_API_KEY")} opts)
        hubspot nil]
    (reify Object
      (get [this id & [params]])
        ;(tpl/get-template hello id))
      (create [this data & [params]]))))
        ;(tpl/create-embedded-draft hello data)))))
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
