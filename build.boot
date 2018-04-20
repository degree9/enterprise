(defn read-file   [file] (read-string (slurp file)))
(defn get-deps    []     (read-file "./dependencies.edn"))

(set-env!
 :dependencies   (get-deps)
 :resource-paths #{"src"})

(require
 '[degree9.boot-semver :refer :all])

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
