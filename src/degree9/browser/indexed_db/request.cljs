(ns degree9.browser.indexed-db.request)

(defprotocol IDBRequest
  (error [this] "")
  (result [this] "")
  (source [this] "")
  (readyState [this] "")
  (transaction [this] "")
  (onerror [this callback] "Handle errors on the open database request.")
  (onsuccess [this callback] "Handle success on the open database request."))

(extend-type js/IDBRequest
  IDBTransaction
  (error [this]
    (.-error this))
  (result [this]
    (.-result this))
  (source [this]
    (.-source this))
  (readyState [this]
    (.-readyState this))
  (transaction [this]
    (.-transaction this))
  (onerror [this callback]
    (.addEventListener this "error" callback))
  (onsuccess [this callback]
    (.addEventListener this "success" callback)))
