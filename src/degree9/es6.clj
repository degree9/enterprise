(ns degree9.es6)

(defmacro extend-class [class impl]
  `(.assign js/Object (.create js/Object ~class) (cljs.core/clj->js ~impl)))
