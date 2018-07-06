(defn read-file   [file] (read-string (slurp file)))
(defn shadow-cljs-edn [] (read-file "./shadow-cljs.edn"))
(defn get-deps    []     (:dependencies (shadow-cljs-edn)))

(set-env!
 :dependencies (get-deps)
 :resource-paths #{"src"})

(require
 '[degree9.boot-semver :refer :all]
 '[crisptrutski.boot-cljs-test :refer [test-cljs]])

(task-options!
 pom    {:project 'degree9/enterprise
         :description "Degree9 Enterprise Platform."
         :url "http://github.com/degree9/enterprise"
         :scm {:url "http://github.com/degree9/enterprise"}})

(deftask deploy
  "Build project for deployment to clojars."
  []
  (comp
    (version)
    (build-jar)
    (push-release)))

(deftask develop
  "Build project for local development."
  []
  (comp
    (version :develop true
             :pre-release 'snapshot)
    (watch)
    (build-jar)))

(def test-cljs-options {:process-shim false})

(replace-task!
 [t test-cljs]
 (fn [& xs]
  (apply t
   ; @TODO extend this to all namespaces safely
   ; @see https://github.com/degree9/enterprise/issues/4
   :namespaces #{#"degree9\.shopify.*"}
   :cljs-opts test-cljs-options
   ; exit? isn't compatible with watch
   :exit? false
   xs)))
