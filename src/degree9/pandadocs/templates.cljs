(ns degree9.pandadocs.templates
  :require [degree9.pandadoc.core :as pd])



;; Templates API ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn template-details [data]
  (pd/get "/templates/" id "/details"))

(defn delete-template [id]
  (pd/delete (str "/templates/" id)))


(defn template [& [opts]]
  (let [conf (merge {:key (env/get "PANDADOC_API_KEY")} opts)]
       [id (merge {:key (env/get "ACCOUNT_ID")} opts)]
    (debug "")
    (reify
      Object
      (get [this id data & [params]]
          (template-details id))
      (remove [this id data & [params]]
          (delete-template id)))))
