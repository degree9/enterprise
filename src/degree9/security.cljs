(ns degree9.security
 (:require
   [feathers.core :as feathers]
   [feathers.authentication :as auth]))

(defn secure-services
 "Takes a feathers app and adds a hook to enforce a valid JWT on every endpoint"
 [app]
 (feathers/hooks app
   (clj->js {:before {:all [(auth/authenticate "jwt")]}})))

(defn with-security [app]
  (-> app
    (secure-services)))

(defn hook-context->jwt
 "Extracts JWT from hook context. Will only work after secure-services hook."
 [context]
 (some-> (js->clj context :keywordize-keys true)
  :params
  :accessToken))
