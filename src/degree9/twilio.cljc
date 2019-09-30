(ns degree9.twilio
 (:require
  #?(:node ["twilio" :as twilio])
  degree9.twilio.auth.data))

#?(:node
   (defn -client!
    ([]
     (-client!
      (degree9.twilio.auth.data/account-sid)
      (degree9.twilio.auth.data/auth-token)))
    ([account-sid auth-token]
     (twilio. account-sid auth-token))))

#?(:node
   (def client! (memoize -client!)))
