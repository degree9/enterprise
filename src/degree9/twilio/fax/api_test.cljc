(ns degree9.twilio.fax.api-test
 (:require
  degree9.twilio
  degree9.twilio.fax.spec
  degree9.twilio.fax.api
  degree9.twilio.fax.data
  degree9.twilio.fax.fixture
  [cljs.spec.alpha :as spec]
  [cljs.test :refer [deftest async]]))

#?(:node
   (defn live-test!
    "
    hits a live fax endpoint with test data and prns the result
    don't do this too much as it costs money
    put the responses in fixture.cljs and mock out tests that way
    "
    [done]
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
        error)))
     (.finally fax-promise done))))

; uncomment to run the live test (costs money)
; (deftest ??live-test!
;  (async done
;   (live-test! done)))
