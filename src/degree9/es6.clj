(ns degree9.es6
  (:refer-clojure :exclude [class])
  (:require [cljs.analyzer :as ana]
            [cljs.compiler :as compiler]))

;; Native ES6 Class compiler extension ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(alter-var-root #'ana/specials #(conj % 'class*))

(defmethod ana/parse 'class*
  [op env [_ sym & exprs :as form] _ _]
  (let [extends    (and (= (first exprs) :extends) (second exprs))
        implements (and (= (first exprs) :implements) (second exprs))
        exprs      (if (or extends implements) (drop 2 exprs) exprs)
        methods    (map (fn [[method & body]] {:method method :body body}) exprs)]
    {:env env
     :op :class
     :children [:methods]
     :methods (ana/disallowing-recur
                 (->> (rest exprs)
                      (mapv #(ana/analyze (assoc env :context :method) %))))
     :ctor (first exprs)
     :extends extends
     :implements implements
     :form form
     :class sym}))

(defmethod compiler/emit* :class
  [{:keys [ctor methods class extends implements]}]
  (let [[_ args & ctor] ctor
        extends    (when extends (str " extends " extends))
        implements (when implements (str " implements " implements))]
    (compiler/emitln "class " class extends implements)
    (compiler/emitln " {")
    (compiler/emitln "constructor( " (interpose "," (map compiler/munge args)) ") {")
    (doseq [c ctor]
      (compiler/emitln c))
    (compiler/emits "}")
    ; (doseq [m methods]
    ;   (compiler/emitln m))
    (compiler/emits "}")))
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; Public Async/Await API ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defmacro defclass
  "Define a native es6 class."
  [name & body]
  `(def ~name (~'class* ~name ~@body)))
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
