; https://github.com/shadow-cljs/quickstart-browser/blob/master/src/starter/browser.cljs
(ns degree9.repl
 (:require ["swagger-client" :as swagger]))

;; start is called by init and after code reloading finishes
(defn ^:dev/after-load start []
  (js/console.log "start"))

(defn ^:export init []
  ;; init is called ONCE when the page loads
  ;; this is called in the index.html and must be exported
  ;; so it is available even in :advanced release builds
  (js/console.log "init")
  (start))

;; this is called before any code is reloaded
(defn ^:dev/before-load stop []
  (js/console.log "stop"))

;; put dummy code in here to test the REPL itself, e.g. hot code reloading, etc.
(defn foo
 []
 "bazi")


(defn k8s [] (swagger "https://raw.githubusercontent.com/kubernetes/kubernetes/master/api/openapi-spec/swagger.json"))
