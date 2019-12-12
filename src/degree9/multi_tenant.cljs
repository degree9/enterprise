(ns degree9.multi-tenant
  (:require [goog.object :as obj]
            [degree9.debug :as dbg]
            [degree9.env :as env]
            [degree9.hooks :as hooks]))

(dbg/defdebug debug "degree9:enterprise:multi-tenant")

(defn current-tenant [field]
  (fn [hook]
    (if-let [tenant (env/get "APP_TENANT_ID")]
      (doto hook
        (hooks/data!
          (assoc (hooks/data hook) field tenant))
        (hooks/params!
          (assoc-in (hooks/params hook) ["query" field] tenant)))
      (throw (js/Error. "Tenant ID has not been configured.")))))

(defn default-tenant [field]
  (fn [hook]
    (let [tenant  (env/get "APP_TENANT_ID")
          params  (hooks/params hook)
          current (get-in params ["query" field])]
      (if current hook
        (doto hook
          (hooks/params!
            (assoc-in params ["query" field] tenant)))))))
