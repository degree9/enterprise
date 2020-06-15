(ns degree9.promise)

(defn all [proms]
  (.all js/Promise proms))

(defn all-settled [proms]
  (.allSettled js/Promise proms))
