(ns degree9.es6
  (:refer-clojure :exclude [class])
  (:require [cljs.analyzer :as analyzer]
            [cljs.compiler :as compiler]))

(def ^:private ecmascript-unsupported
  #{:ecmascript3 :ecmascript5 :ecmascript5-strict})

(defn ^:private ecmascript-in []
  (get-in @cljs.env/*compiler* [:options :language-in]))

(def ^:dynamic *extends* nil)

;; Extends Analyzer Special Forms ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(alter-var-root #'analyzer/specials #(conj % 'class* 'method* 'super*))
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; Native ES6 Class Super compiler extension ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defmethod analyzer/parse 'super*
  [op env [_  & params :as form] _ _]
  (when-not *extends*
    (throw (analyzer/error env "Can't use super() unless you are extending a class.")))
  {:env env
   :op :super
   :form form
   :params (analyzer/disallowing-recur
            (mapv #(analyzer/analyze env %) params))})

(defmethod compiler/emit* :super
  [{:keys [params env]}]
  (compiler/emits "super(" (interpose "," params) ")"))

(defmacro super
  "Call the parent class from a new extended class method. (es6+)"
  [& params]
  `(~'super* ~@params))
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; Native ES6 Class Method compiler extension ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defmethod analyzer/parse 'method*
  [op env [_ method [params & exprs] :as form] _ _]
  {:env env
   :op :method
   :children [:exprs]
   :form form
   :method method
   :params params
   :exprs (analyzer/disallowing-recur
            (mapv #(analyzer/analyze env %) exprs))})

(defmethod compiler/emit* :method
  [{:keys [exprs method params env]}]
  (compiler/emits method "(" (interpose "," params) "){")
  (doseq [expr exprs]
    (compiler/emitln expr))
  (compiler/emits "}"))

(defmacro ^:private method
  "Create a javascript class method. (es6+)"
  [name & body]
  `(~'method* ~name ~@body))
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; Native ES6 Class compiler extension ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defmethod analyzer/parse 'class*
  [op env [_ class extends & methods :as form] _ _]
  (when (ecmascript-unsupported (ecmascript-in))
    (throw (analyzer/error env "Can't use defclass when compiler :language-in lower than :ecmascript-2015")))
  {:env env
   :op :class
   :children [:methods]
   :form form
   :class class
   :extends (when extends
              (analyzer/disallowing-recur
                (analyzer/analyze-symbol env extends)))
   :methods (binding [*extends* extends]
              (analyzer/disallowing-recur
                (mapv #(analyzer/analyze env %) methods)))})

(defmethod compiler/emit* :class
  [{:keys [class extends methods]}]
  (compiler/emits "class " class)
  (when extends (compiler/emits " extends " extends))
  (compiler/emitln " {")
  (doseq [m methods]
    (compiler/emitln m))
  (compiler/emits "}"))

(defn- methodize [[fname & body]]
  (let [name (symbol (name fname))]
    `(method ~name ~@body)))

(defmacro ^:private class
  "Create a named or unnamed javascript class. (es6+)"
  ([method-map] (class nil method-map))
  ([name method-map] (class name nil method-map))
  ([name extends method-map]
   `(~'class* ~name ~extends ~@(map methodize method-map))))

(defmacro defclass
  "Define a named javascript class. (es6+)"
  [name & [extends & methods :as body]]
  (let [extends (when (symbol? extends) extends)
        methods (->> (if (symbol? extends) methods body)
                     (map (fn [[m & b]] [(keyword m) b]))
                     (into {}))]
    `(def ~name (class ~name ~extends ~methods))))
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
