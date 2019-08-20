(ns degree9.twilio.auth.data
 (:require
  degree9.env))

(def account-sid (partial degree9.env/get :twilio-account-sid))
(def auth-token (partial degree9.env/get :twilio-auth-token))
