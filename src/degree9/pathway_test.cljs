(ns degree9.pathway-test
  (:require [degree9.pathway :as pathway]
            [degree9.pathway.spec :as path]
            [clojure.spec.alpha :as spec]
            [clojure.test :refer [deftest is]]))


;; TESTS ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(deftest ??valid-paths
  ;; single path
  (is (spec/valid? ::path/pathway ["/" :root]))
  (is (spec/valid? ::path/pathway ["/" "root"]))
  (is (spec/valid? ::path/pathway [#{"" "/"} "root"]))
  ;; unordered paths
  (is (spec/valid? ::path/pathway {"/" :root}))
  (is (spec/valid? ::path/pathway {"/" "root"}))
  (is (spec/valid? ::path/pathway {#{"" "/"} "root"}))
  ;; ordered paths
  (is (spec/valid? ::path/pathway [["/" :root]]))
  (is (spec/valid? ::path/pathway [["/" "root"]]))
  (is (spec/valid? ::path/pathway [[#{"" "/"} "root"]])))

(deftest ??invalid-paths
  ;; nil as path
  (is (not (spec/valid? ::path/pathway nil)))
  ;; empty hashmap
  (is (not (spec/valid? ::path/pathway {})))
  ;; empty vector
  (is (not (spec/valid? ::path/pathway []))))

(deftest ??nested-paths
  ;; single path
  (is (spec/valid? ::path/pathway ["/" ["sub" :sub]]))
  ;; unordered paths
  (is (spec/valid? ::path/pathway {"/" {"sub" :sub}}))
  ;; ordered paths
  (is (spec/valid? ::path/pathway [["/" ["sub" :sub]]])))

(deftest ??match-paths
  ;; single path
  (is (= ["/" :root] (pathway/match-route ["/" :root] "/")))
  (is (= [#"/" :root] (pathway/match-route [#"/" :root] "/"))))
