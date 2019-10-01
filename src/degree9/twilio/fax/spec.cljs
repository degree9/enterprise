(ns degree9.twilio.fax.spec
 (:require
  [cljs.spec.alpha :as spec]))

; basic fax keys
(spec/def ::from string?)
(spec/def ::to string?)
(spec/def ::media-url (spec/nilable string?))

; extended fax keys
(spec/def ::url string?)
(spec/def ::media ::url)
(spec/def ::sid (spec/nilable string?))
(spec/def ::account-sid (spec/nilable string?))
(spec/def ::quality (spec/nilable string?))
(spec/def ::media-sid (spec/nilable string?))
(spec/def ::num-pages (spec/nilable string?))
(spec/def ::duration (spec/nilable string?))
(spec/def ::status (spec/nilable string?))
(spec/def ::direction (spec/nilable string?))
(spec/def ::api-version (spec/nilable string?))
(spec/def ::price (spec/nilable string?))
(spec/def ::price-unit (spec/nilable string?))
(spec/def ::date-created inst?)
(spec/def ::date-updated inst?)
(spec/def ::links
 (spec/keys
  :opt-un
  [
   ::media]))

(spec/def ::fax-sid string?)
(spec/def ::content-type #{"application/pdf"})

(spec/def ::mediaUrl ::media-url)

(spec/def ::fax-request
 (spec/keys
  :req-un
  [
   ::from
   ::to
   ::mediaUrl]))

; https://www.twilio.com/docs/fax/api/media#fax-media-instance-resouce
(spec/def ::fax-media
 (spec/keys
  :req-un
  [
   ::sid
   ::account-sid
   ::fax-sid
   ::content-type
   ::date-created
   ::date-updated
   ::url]))

; https://www.twilio.com/docs/fax/api/faxes-resource#fax-properties
(spec/def ::fax-instance
 (spec/keys
  :req-un
  [
   ::from
   ::to
   ::sid
   ::account-sid
   ::date-created
   ::date-updated]

  :opt-un
  [
   ::media-url
   ::quality
   ::media-sid
   ::num-pages
   ::duration
   ::status
   ::direction
   ::api-version
   ::price
   ::price-unit
   ::links
   ::url]))
