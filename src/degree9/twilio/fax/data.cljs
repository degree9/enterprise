(ns degree9.twilio.fax.data
 (:require
  degree9.env))

; the dao de jing
; because we can
(def test-pdf-url "https://www.gutenberg.org/files/49965/49965-pdf.pdf")

(def test-fax-number (partial degree9.env/get :twilio-test-fax-number))

(defn test-fax
 []
 {:from (test-fax-number)
  :to (test-fax-number)
  :mediaUrl test-pdf-url})
