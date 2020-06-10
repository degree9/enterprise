(ns degree9.es6
  (:refer-clojure :exclude [class])
  (:require [cljs.analyzer :as ana]
            [cljs.compiler :as compiler]))

;; Native ES6 Class Method compiler extension ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(alter-var-root #'ana/specials #(conj % 'class* 'method* 'super*))

(defmethod ana/parse 'method*
  [op env [_ method params & exprs :as form] _ _]
  {:env env
   :op :method
   :children [:exprs]
   :form form
   :method method
   :params params
   :exprs (ana/disallowing-recur
            (mapv #(ana/analyze (assoc env :context :statement) %) exprs))})

(defmethod compiler/emit* :method
  [{:keys [method params exprs]}]
  (compiler/emitln method "(" (interpose "," (map compiler/munge params)) "){")
  (doseq [e exprs]
    (compiler/emitln e))
  (compiler/emits "}"))

(defmacro ^:private method
  "Create a javascript class method. (es6+)

   Javascript DOES NOT support overloading methods, since we are creating native
   method blocks, we also do not support overloading methods."
  [name & body]
  `(~'method* ~name ~@body))
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; Native ES6 Class compiler extension ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defmethod ana/parse 'class*
  [op env [_ class extends & methods :as form] _ _]
  {:env env
   :op :class
   :children [:methods]
   :form form
   :class class
   :extends extends
   :methods (ana/disallowing-recur
              (mapv #(ana/analyze (assoc env :context :method) %) methods))})

(defmethod compiler/emit* :class
  [{:keys [class extends methods]}]
  (let [extends (when extends (str " extends " extends))]
    (compiler/emitln "class " class extends " {")
    (doseq [m methods]
      (compiler/emitln m))
    (compiler/emits "}")))

(defn parse-args [[extends & methods :as body]]
  (let [extends (when (symbol? extends) extends)
        methods (if extends methods body)]
    [extends methods]))

(defn- methodize [[fname & body]]
  (let [name (symbol (name fname))]
    `(~'method ~name ~@body)))

(defmacro ^:private class
  "Create a named or unnamed javascript class. (es6+)"
  ([method-map] (class nil method-map))
  ([name method-map] (class name nil method-map))
  ([name extends method-map]
   `(~'class* ~name ~extends ~@(map methodize method-map))))

(defmacro defclass
  "Define a named javascript class. (es6+)"
  ([name & [extends & methods :as body]]
   (prn name extends methods)
   (let [extends (when (symbol? extends) extends)
         methods (->> (when extends methods body)
                      (reduce (fn [i [m & b]] (assoc i (keyword m) b)) {}))]
     `(def ~name (class ~name ~extends ~methods)))))
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
