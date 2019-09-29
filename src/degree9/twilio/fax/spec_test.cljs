(ns degree9.twilio.fax.spec-test
 (:require
  degree9.twilio.fax.spec
  degree9.twilio.fax.fixture
  [cljs.spec.alpha :as spec]
  [cljs.test :refer [deftest is]]))

(deftest ??fax-instance
 (doseq [[s i] [[:degree9.twilio.fax.spec/fax-request
                 (degree9.twilio.fax.fixture/simple-fax-request)]
                [:degree9.twilio.fax.spec/fax-instance
                 (degree9.twilio.fax.fixture/example-fax-instance)]]]
  (is
   (spec/valid? s i)
   (spec/explain-str s i))))
