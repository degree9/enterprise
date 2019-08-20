(ns degree9.twilio.fax.spec
 (:require
  [cljs.spec.alpha :as spec]))

(spec/def ::from string?)
(spec/def ::to string?)
(spec/def ::mediaUrl string?)

(spec/def ::fax
 (spec/keys
  :req-un
  [
   ::from
   ::to
   ::mediaUrl]))
