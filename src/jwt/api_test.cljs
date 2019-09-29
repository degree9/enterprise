(ns jwt.api-test
 (:require
  jwt.api
  jwt.examples
  [cljs.test :refer-macros [deftest is async]]))

(deftest ??decode
 (is
  (=
   [jwt.examples/jwt-valid-header jwt.examples/jwt-valid-payload]
   (jwt.api/decode jwt.examples/jwt-valid))))
