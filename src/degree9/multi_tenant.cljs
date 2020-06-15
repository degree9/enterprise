(ns degree9.multi-tenant
  (:require [degree9.object :as obj]
            [degree9.debug :as dbg]
            [degree9.env :as env]
            [degree9.hooks :as hooks]))

(dbg/defdebug debug "degree9:enterprise:multi-tenant")

(defn current-tenant [field]
  (fn [hook]
    (if-let [tenant (env/get "APP_TENANT_ID")]
      (-> hook
        (obj/set-in [:data field] tenant)
        (obj/set-in [:params :query field] tenant))
      (throw (js/Error. "Tenant ID has not been configured.")))))

(defn default-tenant [field]
  (fn [hook]
    (let [tenant  (env/get "APP_TENANT_ID")
          current (obj/get-in hook [:params :query field])]
      (if current hook
        (obj/set-in hook [:params :query field] tenant)))))
