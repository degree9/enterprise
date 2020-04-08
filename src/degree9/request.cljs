(ns degree9.request
  (:require ["node-fetch" :as fetch]
            [degree9.debug :as dbg]))

(dbg/defdebug debug "degree9:enterprise:request")

(defn request [url & {:or {} :as req}]
  (let [defaults {:method "GET"
                  :headers {:accept "application/json"
                            :content-type "application/json"}}
        req (clj->js (merge defaults req))]
    (debug "Constructing request using %s" req)
    (fetch url req)))

(defn get [url & [opts]]
  (request url
    (merge opts
      {:method "GET"})))

(defn clj->json [data]
  (.stringify js/JSON (clj->js data)))

(defn post [url data & [opts]]
  (request url
    (merge opts
      {:method "POST"
       :headers (merge (:headers opts)
                  {:accept "application/json"
                   :content-type "application/json"})
       :body (clj->json data)})))
