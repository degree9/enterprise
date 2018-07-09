(ns degree9.env
 (:require
  cljs.nodejs
  camel-snake-kebab.core
  goog.object
  [cljs.test :refer-macros [deftest is]])
 (:refer-clojure :exclude [get]))

(defn env-obj []
 (.-env js/process))

(defn dir []
 (map
  camel-snake-kebab.core/->kebab-case-keyword
  (js->clj
   (js-keys
    (env-obj)))))

(defn get [k]
 (str
  (goog.object/get
   (env-obj)
   (camel-snake-kebab.core/->SCREAMING_SNAKE_CASE_STRING
    (name k)))))

; TESTS

(deftest ??dir
 (doseq [e [:pwd :home :user]]
  (is
   (contains?
    (set (dir))
    e))))

(deftest ??get
 (is (clojure.string/includes? (get :pwd) "enterprise")))
