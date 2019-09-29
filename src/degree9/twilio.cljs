(ns degree9.twilio
 (:require
  ["twilio" :as twilio]
  degree9.twilio.auth.data))

(defn -client!
 ([]
  (-client!
   (degree9.twilio.auth.data/account-sid)
   (degree9.twilio.auth.data/auth-token)))
 ([account-sid auth-token]
  (twilio. account-sid auth-token)))
(def client! (memoize -client!))
