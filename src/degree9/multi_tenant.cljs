(ns degree9.multi-tenant
  (:require [degree9.object :as obj]
            [degree9.debug :as dbg]
            [degree9.env :as env]
            [degree9.hooks :as hooks]))

(dbg/defdebug debug "degree9:enterprise:multi-tenant")

(defn current-tenant [field]
  (fn [context]
    (if-let [tenant (env/get "APP_TENANT_ID")]
      (-> context
        (obj/set-in [field] tenant)
        (obj/set-in [:data field] tenant)
        (obj/set-in [:params :query field] tenant))
      (throw (js/Error. "Tenant ID has not been configured.")))))

(defn default-tenant [field]
  (fn [context]
    (let [tenant  (env/get "APP_TENANT_ID")
          current (obj/get-in context [:params :query field])]
      (if current context
        (obj/set-in context [:params :query field] tenant)))))
