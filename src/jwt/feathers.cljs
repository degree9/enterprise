(ns jwt.feathers
 (:require
  oops.core))

; https://docs.feathersjs.com/guides/auth/recipe.customize-jwt-payload.html
(defn merge-payload-hook
 "Merge cljs data into the JWT payload. Use with `authentication` service."
 [to-merge]
 (fn [context]
  (let [current-payload (js->clj (oops.core/oget context "?params.?payload"))]
   (oops.core/oset! context "!params.!payload" (clj->js (merge current-payload to-merge)))
   context)))
