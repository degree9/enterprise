(ns degree9.kubernetes.utilities)

;; Kubernetes Helpers ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn- k8s->clj
  "Converts Kubernetes response to ClojureScript."
  [k8s]
  (debug "Converting kubernetes response to cljs" k8s)
  (js->clj k8s :keywordize-keys true))

(defn- k8s-response [res]
  (obj/get res "body"))

(defn k8s-error
  "Converts Kubernetes error response to FeathersJS error object."
  [err]
  (let [{:keys [message data code]} (k8s->clj (k8s-response err))]
    (case code
      404 (error/not-found message data)
      409 (error/conflict message data)
      500 (error/general message data)
      (error/general message data))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
