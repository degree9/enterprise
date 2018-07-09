(ns degree9.env
 (:require
  cljs.nodejs
  camel-snake-kebab.core
  goog.object
  [cljs.test :refer-macros [deftest is]])
 (:refer-clojure :exclude [get]))

(defn env-obj []
 cljs.nodejs.process.env)

(defn dir []
 (js->clj
  (js-keys
   (env-obj))))

(defn get [k]
 (str
  (goog.object/get
   (env-obj)
   (camel-snake-kebab.core/->SCREAMING_SNAKE_CASE_STRING
    (name k)))))

; TESTS

(deftest ??get
 (prn (dir))
 (is (= "" (get :pwd))))
