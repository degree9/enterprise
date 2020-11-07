(ns degree9.browser.indexed-db.opendbrequest)

(defprotocol IDBOpenDBRequest
  (onblocked [this callback] "Handle when an open connection to a database is blocking a versionchange transaction on the same database.")
  (onupgradeneeded [this callback] "Handle when an attempt was made to open a database with a version number higher than its current version."))

(extend-type js/IDBOpenDBRequest
  IDBOpenDBRequest
  (onblocked [this callback]
    (.addEventListener this "blocked" callback))
  (onupgradeneeded [this callback]
    (.addEventListener this "upgradeneeded" callback)))
