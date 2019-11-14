(ns degree9.roles
  (:require ["debug" :as dbg]
            [goog.object :as obj]
            [degree9.hooks :as hooks]
            [covenant.core :as cov]
            [covenant.rbac]))

(def ^:private debug (dbg "degree9:enterprise:roles"))

(defn- not-authorized []
  (throw (js/Error. "User does not have sufficient authorization.")))

(defn require-roles [roles]
  (fn [hook]
    (debug "Validate required user roles: " roles)
    (let [user (get-in (hooks/params hook) ["user"])]
      (if-not (cov/validate {"roles" (js->clj roles)} (js->clj user)) (not-authorized)
        hook))))

(defn authentication-roles [roles]
  (fn [hook]
    (debug "Authenticate user with roles: " roles)
    (let [user (get-in (hooks/result hook) ["user"])]
      (if-not (cov/validate {"roles" (js->clj roles)} (js->clj user)) (not-authorized)
        hook))))

(defn with-authorization [app]
  (debug "Loading Role Authorization api")
  (let [roles (obj/get (.get app "authorization") "roles")]
    (.hooks (.service app "authentication")
      (clj->js {:after {:all [(authentication-roles roles)]}}))
    app))

(defn merge-roles [roles]
  (fn [hook]
    (debug "Merge roles in to request data: " roles)
    (let [data (hooks/data hook)]
      (doto hook
        (hooks/data! (merge-with into data {:roles roles}))))))
