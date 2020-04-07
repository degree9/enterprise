(ns degree9.paysafe.auth
 (:require degree9.env))

(def api-key (partial degree9.env/get :paysafe-api-key))
(def api-secret (partial degree9.env/get :paysafe-api-secret))
