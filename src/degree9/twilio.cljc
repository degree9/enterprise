(ns degree9.twilio
 (:require
  #?(:node ["twilio" :as twilio])
  degree9.twilio.auth.data
  [degree9.twilio.fax.api :as efax]))

#?(:node
   (defn -client! [account-sid auth-token]
     (twilio. account-sid auth-token)))

#?(:node
   (def client! (memoize -client!)))

#?(:node
   (defn efax [& [opts]]
     (let [account-id (:account-id opts (degree9.twilio.auth.data/account-sid))
           auth-token (:auth-token opts (degree9.twilio.auth.data/auth-token))
           client (client! account-id auth-token)]
       (reify
         Object
         (find [this params]
           (efax/list!))
         (get [this id params]
           (efax/fetch! id))
         (create [this data params]
           (efax/fax! data))))))
          ; (update [this id data params]
          ;   (update* store database collection id data (js->clj params)))
          ;(patch [this id data params]
          ;  ())
          ; (remove [this id params]
          ;   (remove* store database collection id (js->clj params))))))
