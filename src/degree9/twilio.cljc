(ns degree9.twilio
 (:require
  degree9.twilio.auth.data
  degree9.twilio.client
  [degree9.twilio.fax.api :as efax]))

#?(:node
   (defn efax [& [opts]]
     (let [account-id (:account-id opts (degree9.twilio.auth.data/account-sid))
           auth-token (:auth-token opts (degree9.twilio.auth.data/auth-token))
           client (degree9.twilio.client/client! account-id auth-token)]
       (reify
         Object
         (find [this params]
           (efax/list! client))
         (get [this id params]
           (efax/fetch! client id))
         (create [this data params]
           (efax/fax! client data))))))
