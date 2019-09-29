(ns degree9.env-test
 (:require
  #?(:node [degree9.env :as env])
  [clojure.test :refer [deftest is]]))

;; TESTS ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

#?(:node
   (deftest ??dir
    (doseq [e [:pwd :home :user]]
     (is ((set (env/keys)) e)))))

#?(:node
   (deftest ??get
    (is (clojure.string/includes? (env/get :pwd) "enterprise"))
    (is (clojure.string/includes? (env/get "PWD") "enterprise"))
    ; no fallback yields nil for no match
    (is (= nil (env/get :no-match)))
    ; should fallback to foo with no match
    (is (= "foo" (env/get :no-match "foo")))))
