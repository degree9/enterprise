(ns degree9.open-api
  (:require ["feathers-swagger" :as swagger]))

(defn api [app opts]
  (doto app
    (.configure (swagger (clj->js opts)))))
