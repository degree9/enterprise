(ns degree9.hellosign.callback)

(defn all-signed! [])

(defn hellosign-service [& opts]
  (reify Object
    (create [this & args]
      (let []
        (.log js/console args)
        (js/Promise. (fn [resolve reject] (resolve "Hello API Event Received")))))))

(defn with-callback! [app]
  (.use app "/hellosign/callback"
    (hellosign-service)))
