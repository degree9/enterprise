(ns degree9.string
  (:refer-clojure :exclude [replace])
  (:require [clojure.string :as cstr]
            [goog.string    :as gstr]
            [goog.string.format]))

(def format gstr/format)

(def join cstr/join)

(def replace cstr/replace)

(def split cstr/split)

(def split-lines cstr/split-lines)

(defn prepend [s affix]
  (str affix s))

(defn append [s affix]
  (str s affix))

(defn blank? [s]
  (cstr/blank? s))

(defn not-blank? [s]
  (not (cstr/blank? s)))

(defn pad-start
  ([s length] (.padStart (str s) length))
  ([s length pad] (.padStart (str s) length pad)))
