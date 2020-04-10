(ns degree9.promise)

(defmacro promise* [& body]
  `(promise
    (fn [~'resolve ~'reject]
      ~@body)))

(defmacro promise [& body]
  `(~'promise* ~@body))
