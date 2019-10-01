(ns jwt.feathers-test
 (:require
  jwt.feathers
  [cljs.test :refer-macros [deftest is]]))

(deftest ??merge-payload-hook
 ; no payload
 (let [f (jwt.feathers/merge-payload-hook {:foo :bar})
       ctx (clj->js {})]
  (is
   (=
    {"params" {"payload" {"foo" "bar"}}}
    (js->clj (f ctx))))))
