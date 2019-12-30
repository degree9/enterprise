(ns degree9.pathway.spec
  (:require [clojure.spec.alpha :as spec]))

(spec/def ::not-empty
  #(not (empty? %)))

(spec/def ::path
  (spec/tuple (spec/or :str string? :reg regexp? :key keyword? :set set?) ::pathway))

(spec/def ::ordered
  (spec/and vector?
    ::not-empty
    (spec/coll-of ::path)))

(spec/def ::unordered
  (spec/and map?
    ::not-empty
    (spec/coll-of ::path)))

(spec/def ::paths
  (spec/or
    :ordered   ::ordered
    :unordered ::unordered))

(spec/def ::handler
  (spec/or
    :string  string?
    :keyword keyword?))

(spec/def ::pathway
  (spec/or
    :paths   ::paths
    :handler ::handler))
