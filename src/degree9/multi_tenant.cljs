(ns degree9.multi-tenant
  (:require [goog.object :as obj]
            [degree9.env :as env]
            [degree9.hooks :as hooks]))

(defn current-tenant [field]
  (fn [hook]
    (let [tenant (env/get "APP_TENANT_ID")
          params (hooks/params hook)]
      (doto hook
        (hooks/params!
          (assoc-in params ["query" field] tenant))))))
