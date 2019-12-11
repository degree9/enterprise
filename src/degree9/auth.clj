(ns degree9.auth)

(defmacro if-auth [& body]
  `(hoplon.core/if-tpl degree9.auth/authentication
    ~@body))
