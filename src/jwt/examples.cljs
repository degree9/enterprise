(ns jwt.examples)

; https://jwt.io/

(def jwt-valid "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJjZmQzNWM5ODBmOGJmZjU4M2EyODY4NjNjOTUzOGFmNiIsInN1YiI6IjVhYTY3NWNmOTI1MTQxNWU0YjAwODUxYyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.WclPWxpdNCVxIV2fJZnif0G_8U3k2KGgb9TQc4Ty_Fc")
(def jwt-valid-header
 {:typ "JWT"
  :alg "HS256"})
(def jwt-valid-payload
 {:aud "cfd35c980f8bff583a286863c9538af6"
  :sub "5aa675cf9251415e4b00851c"
  :scopes ["api_read"]
  :version 1})

(def jwt-valid-admin "eyJhbGciOiJFUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiYWRtaW4iOnRydWUsImlhdCI6MTUxNjIzOTAyMiwicm9sZXMiOlsiYWRtaW4iLCJlZGl0b3IiXX0.sTuw2amLCtD6KNNH5MR38tg_JNpHfaxIdICdpD4xAgIIXfseSVEhix9rPIU6OaAYRzz6te0Odk_zD1BLI3A75A")
(def jwt-valid-header-admin
 {:typ "JWT"
  :alg "ES256"})
(def jwt-valid-payload-admin
 {
  :sub "1234567890",
  :name "John Doe",
  :admin true,
  :iat 1516239022,
  :roles ["admin" "editor"]})


(def jwt-expired "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJjZmQzNWM5ODBmOGJmZjU4M2EyODY4NjNjOTUzOGFmNiIsInN1YiI6IjVhYTY3NWNmOTI1MTQxNWU0YjAwODUxYyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxLCJleHAiOjF9.8-sp6wzteGivxMIu9FnVh5_ovnxBeatVNrmQlwDWKjM")
(def jwt-not-a-jwt "foo")
; replacing the last letter with "x" invalidates the signature
(def jwt-invalid-signature
 (str
  (apply str (drop-last jwt-valid))
  "x"))
