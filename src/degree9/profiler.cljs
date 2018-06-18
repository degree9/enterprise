(ns degree9.profiler
  (:require [taoensso.tufte :as tufte :refer-macros (defnp p profiled profile)]))

(tufte/add-basic-println-handler! {})

(defn run-once [app]
  (profile {}
    (dotimes [_ 1]
      (p :app app))))
