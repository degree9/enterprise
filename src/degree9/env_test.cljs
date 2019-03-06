(ns degree9.env-test
  (:require [degree9.env :as env]
            [clojure.test :refer [deftest is]]))


;; TESTS ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(deftest ??dir
 (doseq [e [:pwd :home :user]]
  (is (contains? (env/keys) e))))

(deftest ??get
 (is (clojure.string/includes? (env/get :pwd) "enterprise"))
 (is (clojure.string/includes? (env/get "PWD") "enterprise"))
 ; no fallback yields nil for no match
 (is (= nil (env/get :no-match)))
 ; should fallback to foo with no match
 (is (= "foo" (env/get :no-match "foo"))))
