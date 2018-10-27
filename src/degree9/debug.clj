(ns degree9.debug)

(defmacro defdebug [name module]
  `(def ^:private ~name (debug ~module)))
