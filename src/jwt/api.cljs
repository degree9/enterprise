(ns jwt.api
 (:require
  goog.crypt.base64))

(defn decode
 "Returns [header payload] for the given JWT. ASSUMES valid JWT."
 [jwt]
 (let [[header payload sig] (clojure.string/split jwt ".")
       decode-part (fn [part]
                    (js->clj
                     (.parse js/JSON
                      (goog.crypt.base64/decodeString
                       part))
                     :keywordize-keys true))]
  (map decode-part [header payload])))
