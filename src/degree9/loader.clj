(ns degree9.loader
  (:require [shadow.lazy :as lazy]))

; (defmacro component [name]
;   `(lazy/loadable ~name))

(defmacro async-component [component & args]
  `(let [comp# (lazy/loadable ~component)]
     (j/with-let [async# (async-element)]
       (-> (lazy/load comp#)
           (.then
             (fn [elem#]
               (.appendChild async# (elem# ~@args))))))))
