(ns degree9.twilio.auth.spec
 (:require
  [cljs.spec.alpha :as spec]))

(spec/def :degree9.twilio.auth/account-sid string?)
(spec/def :degree9.twilio.auth/auth-token string?)
