(ns degree9.twilio
 (:require
  #?(:node ["twilio" :as twilio])
  [degree9.twilio.fax.api :as efax]))

#?(:node
   (defn efax [& [opts]]
     (let [account-id (:account-id opts (degree9.env/get :twilio-account-sid))
           auth-token (:auth-token opts (degree9.env/get :twilio-auth-token))
           client (client! account-id auth-token)]
       (reify
         Object
         (find [this params]
           (efax/list! client))
         (get [this id params]
           (efax/fetch! client id))
         (create [this data params]
           (efax/fax! client data))))))
