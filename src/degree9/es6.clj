(ns degree9.es6
  (:refer-clojure :exclude [class])
  (:require [cljs.analyzer :as analyzer]
            [cljs.compiler :as compiler]))

;; Extends Analyzer Special Forms ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(alter-var-root #'analyzer/specials #(conj % 'js-class* 'js-method* 'js-super*))
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; Native ES6 Class Super ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defmacro ^:private js-super []
  `('js* "super"))

(defmacro super-as
  "Defines a scope where JavaScript's implicit \"super\" is bound to the name provided."
  [name & body]
  `(let [~name (js-super)]
     ~@body))

(defmethod analyzer/parse 'js-super*
  [op env [_ & params :as form] _ _]
  (analyzer/disallowing-recur
    {:env env
     :op :js-super
     :form form
     :params (when params (mapv (partial analyzer/analyze (assoc env :context :expr)) params))}))

(defmethod compiler/emit* :js-super
  [{:keys [params]}]
  (if-not params
    (compiler/emits "super")
    (compiler/emits "super(" (interpose "," params) ")")))

(defmacro super [& params]
  `(~'js-super* ~@params))
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; Native ES6 Class compiler extension ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn ^:private analyze-with-locals [{:keys [locals] :as env} params expr]
  (let [[locals _] (reduce (analyzer/analyze-fn-method-param env)
                           [locals []]
                           (map-indexed vector params))]
    (analyzer/analyze (assoc env :locals locals) expr)))

(defmethod analyzer/parse 'js-method*
  [op env [_ method params & exprs :as form] _ _]
  (let [params (remove '#{&} params)
        statements (->> (butlast exprs)
                        (mapv (partial analyze-with-locals
                                      (assoc env :context :statement)
                                      (vec params))))
        ret        (->> (last exprs)
                        (analyze-with-locals (assoc env :context :return)
                                             (vec params)))]
    (analyzer/disallowing-recur
      {:env env
       :op :js-method
       :children (if statements [:statements :ret] [:ret])
       :form form
       :method method
       :params params
       :statements statements
       :ret ret})))
       
(defmethod compiler/emit* :js-method
  [{:keys [method params statement ret]}]
  (compiler/emitln method "(" (interpose "," params) "){")
  (doseq [s statement]
    (compiler/emitln s))
  (when ret (compiler/emitln ret))
  (compiler/emitln "}"))

(defn ^:private methodize [[method params & body]]
  `(~'js-method* ~method ~params ~@body))

(defmethod analyzer/parse 'js-class*
  [op env [_ class extends & methods :as form] _ _]
  (analyzer/disallowing-recur
    {:env env
     :op :js-class
     :children [:methods]
     :form form
     :class class
     :extends (when extends (analyzer/analyze-symbol env extends))
     :methods (mapv (partial analyzer/analyze env) methods)}))

(defmethod compiler/emit* :js-class
  [{:keys [env class extends methods]}]
  (when (= :return (:context env))
    (compiler/emits "return "))
  (compiler/emits "class " class)
  (when extends
    (compiler/emits " extends " extends))
  (compiler/emitln " {")
  (doseq [m methods]
    (compiler/emitln m))
  (compiler/emits "}"))

(defmacro ^:private class
  "Create a named or unnamed javascript class. (es6+)"
  [& [name & [extends :as methods] :as body]]
  (let [name    (when (symbol? name) name)
        extends (when (symbol? extends) extends)
        methods (if name methods body)
        methods (if extends (rest methods) methods)]
   `(~'js-class* ~name ~extends ~@(map methodize methods))))

(defmacro defclass
  "Define a named javascript class. (es6+)"
  [name & [extends :as methods]]
  (let [extends (when (symbol? extends) extends)
        methods (if extends (rest methods) methods)]
    `(def ~name (class ~name ~extends ~@methods))))
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
