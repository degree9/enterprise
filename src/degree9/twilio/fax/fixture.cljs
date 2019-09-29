; data and examples generated by and used for testing
(ns degree9.twilio.fax.fixture
 (:require
  degree9.env))

; the dao de jing
(defn example-pdf-url
 []
 "https://www.gutenberg.org/files/49965/49965-pdf.pdf")

; this needs to be a fax number that is fax enabled in twilio dashboard
(def test-fax-number (partial degree9.env/get :twilio-test-fax-number))

; sends
; the example pdf url
; from the test fax number
; to the test fax number
(defn simple-fax-request
 []
 {:from (test-fax-number)
  :to (test-fax-number)
  :mediaUrl (example-pdf-url)})

(defn example-fax-instance
 []
 {:price-unit nil
  :date-created #inst "2019-09-29T15:53:52.000-00:00"
  :date-updated #inst "2019-09-29T15:53:52.000-00:00"
  :duration nil
  :api-version "v1"
  :status "queued"
  :from "+16474923317"
  :url "https://fax.twilio.com/v1/Faxes/FXae574af690cc1b730af1f42c9ef57876"
  :media-sid nil
  :quality "fine"
  :account-sid "ACba205cc3f907673685d7c125680dd506"
  :media-url nil
  :price nil
  :direction "outbound"
  :links {:media "https://fax.twilio.com/v1/Faxes/FXae574af690cc1b730af1f42c9ef57876/Media"}
  :sid "FXae574af690cc1b730af1f42c9ef57876"
  :num-pages nil
  :to "+16474923317"})
