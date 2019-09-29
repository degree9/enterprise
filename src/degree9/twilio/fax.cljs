(ns degree9.twilio.fax
 (:require
  degree9.twilio.core
  degree9.twilio.fax.spec
  degree9.twilio.fax.api
  degree9.twilio.fax.data
  [cljs.spec.alpha :as spec]))

(defn live-test!
 "
 hits a live fax endpoint with test data and prns the result
 don't do this too much as it costs money
 put the responses in fixture.cljs and mock out tests that way
 "
 []
 (let [fax-promise
       (degree9.twilio.fax.api/fax!
        (degree9.twilio.fax.fixture/simple-fax-request))]
  (.then fax-promise
   (fn [fax-instance]
    (prn
     "success"
     (degree9.twilio.fax.data/fax-instance->clj fax-instance))))
  (.catch fax-promise
   (fn [error]
    (prn
     "error"
     error)))))
