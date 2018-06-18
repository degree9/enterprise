(ns degree9.routing
  (:require [hoplon.core :as h]
            [hoplon.bidi :as bidi]))

(defmacro route-tpl [routes & clauses]
  `(hoplon.bidi/route-tpl ~routes ~@clauses))
