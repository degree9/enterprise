(ns degree9.string
  (:require [clojure.string :as cstr]
            [goog.string    :as gstr]
            [goog.string.format]))

(def format gstr/format)

(def join cstr/join)

(def blank? cstr/blank?)

(def not-blank? (comp cstr/blank? not))

(defn pad-start
  ([s length] (.padStart (str s) length))
  ([s length pad] (.padStart (str s) length pad)))
