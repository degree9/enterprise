(ns degree9.twilio.client
 (:require
  #?(:node ["twilio" :as twilio])))

#?(:node
   (defn -client! [account-sid auth-token]
     (twilio. account-sid auth-token)))

#?(:node
   (def client! (memoize -client!)))
