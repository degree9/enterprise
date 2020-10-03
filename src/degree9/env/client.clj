(ns degree9.env.client)


(defmacro define [name & [default]]
  `('goog-define ~name ~default))
