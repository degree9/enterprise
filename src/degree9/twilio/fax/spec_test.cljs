(ns degree9.twilio.fax.spec-test
 (:require
  degree9.twilio.fax.spec
  degree9.twilio.fax.fixture
  [cljs.spec.alpha :as spec]
  [cljs.test :refer [deftest is]]))

(deftest ??fax-instance
 (is
  (spec/valid?
   :degree9.twilio.fax.spec/fax-instance
   (degree9.twilio.fax.fixture/example-fax-instance))
  (spec/explain-str
   :degree9.twilio.fax.spec/fax-instance
   (degree9.twilio.fax.fixture/example-fax-instance))))
