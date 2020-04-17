(ns degree9.timekit.findtime
  (:require [degree9.timekit.core :as tk]))



(defn find-time [client & args]
  (.findTime client args))

(defn time [& [opts]]
  (let [client (:client opts)]
    (debug "" client)
    (reify
      Object
      (find [this & [params]]
          (find-time client)))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn find-time-bulk [client & args]
  (.findTimeBulk client args))

(defn bulk-time [& [opts]]
  (let [client (:client opts)]
    (debug "" client)
    (reify
      Object
      (find [this & [params]]
          (find-time-bulk client)))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn find-time-team [client & args]
  (.findTimeTeam client args))

(defn team-time [& [opts]]
  (let [client (:client opts)]
    (debug "" client)
    (reify
      Object
      (find [this & [params]]
          (find-time-team client)))))
