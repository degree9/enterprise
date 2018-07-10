(ns degree9.env
 (:require
  cljs.nodejs
  camel-snake-kebab.core
  goog.object
  [cljs.test :refer-macros [deftest is]]
  ["dotenv" :as dotenv])
 (:refer-clojure :exclude [get]))

(let [config (atom nil)]
 (defn env-obj
  "inits dotenv and returns the JS object where env values can be found"
  []
  (when-not @config
   (reset! config (.config dotenv)))
  (.-env js/process)))

(defn dir
 "returns the set of all available env keywords"
 []
 (set
  (map
   camel-snake-kebab.core/->kebab-case-keyword
   (js->clj
    (js-keys
     (env-obj))))))

(defn get
 "returns the env value string for key, with optional fallback or empty string"
 ([k] (get k nil))
 ([k fallback]
  {:post [(string? %)]}
  (str
   (if-let [v (goog.object/get
               (env-obj)
               (camel-snake-kebab.core/->SCREAMING_SNAKE_CASE_STRING
                (name k)))]
    v fallback))))

; TESTS

(deftest ??dir
 (doseq [e [:pwd :home :user]]
  (is
   (contains?
    (dir)
    e))))

(deftest ??get
 (is (clojure.string/includes? (get :pwd) "enterprise"))
 (is (clojure.string/includes? (get "PWD") "enterprise"))
 ; no fallback yields "" for no match
 (is (= "" (get :no-match)))
 ; should fallback to foo with no match
 (is (= "foo" (get :no-match "foo"))))
