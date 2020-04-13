(ns degree9.timekit.auth_endpoints
  (:require [degree9.timekit.core :as tk]))


(defn auth [client & args]
  (.auth client args))
