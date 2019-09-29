(ns degree9.documentdb
 (:require
  ; [cljs.nodejs :as node]
  [clojure.string :as s]
  [goog.object :as obj]
  [meta.promise :as p]
  [feathers.services :as svc]
  degree9.env
  ["documentdb" :as documentdb]))

;; DB Definitions ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(def dbclient   (obj/get documentdb "DocumentClient"))
(def urifactory (obj/get documentdb "UriFactory"))
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; DB Helpers ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn- uri*
  ([db] (.createDatabaseUri urifactory db))
  ([db coll] (.createDocumentCollectionUri urifactory db coll))
  ([db coll doc] (.createDocumentUri urifactory db coll doc)))

(defn- get-collection [store db coll query]
  (p/with-callback callback
    (-> store
      (.queryDocuments (uri* db coll) query)
      (.toArray callback))))

(defn- get-database [store query]
  (p/with-callback callback
    (-> store
      (.queryDatabases query)
      (.toArray callback))))

(defn- create-database [store data]
  (p/with-callback callback
    (-> store
      (.createDatabase data callback))))

(defn- create-document [store db coll data]
  (p/with-callback callback
    (-> store
      (.createDocument (uri* db coll) data callback))))

(defn- replace-document [store db coll id data]
  (p/with-callback callback
    (-> store
      (.replaceDocument (uri* db coll id) data callback))))

(defn- remove-document [store db coll doc data]
  (p/with-callback callback
    (-> store
      (.deleteDocument (uri* db coll doc) callback))))
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; SQL Helpers ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn- from* [qmap from]
  (if-not (coll? from)
    (assoc qmap :from from :alias (first from))
    (assoc qmap :from (first from) :alias (last from))))

(defn- prop* [alias prop]
  (if-not (= "*" prop) (str alias "." prop) prop))

(defn- quote* [val]
  (str "\"" val "\""))

(defn- clause* [alias [prop func val]]
  (let [prop (prop* alias prop)
        val  (quote* val)]
    [prop func val]))

(defn- where* [qmap where]
  (cond
    (empty? where) (dissoc qmap :where)
    (map? where) (->> (seq where) (map (partial interpose "=")) (where* qmap))
    :else (->> where (map (partial clause* (:alias qmap))) (assoc qmap :where))))

(defn- select* [qmap select]
  (assoc qmap :select (map (partial prop* (:alias qmap)) (seq select))))

(defn- query* [{:keys [select from alias where] :as qmap}]
  (->>
    (cond-> []
      select (conj "SELECT" select)
      from   (conj "FROM"   from)
      alias  (conj "AS"     alias)
      where  (conj "WHERE"  where))
    (flatten)
    (s/join " ")))

(defn- sql* [{:keys [select from where] :as qmap}]
  (-> qmap
    (from* from)
    (where* where)
    (select* select)
    (query*)))
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; DB Service Methods ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn- db* [store data params]
  (create-database store data))

(defn- find* [store db coll {:strs [query] :as params}]
  (let [query (dissoc query "$limit" "$select")]
    (get-collection store db coll
      (sql* {:select "*" :from coll :where query}))))

(defn- find-database* [store {:strs [query] :as params}]
  (let [query (dissoc query "$limit" "$select")]
    (get-database store
      (sql* {:select "*" :from "root" :where query}))))

(defn- get* [store db coll doc params]
  (get-collection store db coll
    (sql* {:select "*" :from coll :where {"id" doc}})))

(defn- get-database* [store db params]
  (get-database store
    (sql* {:select "*" :from "root" :where {"id" db}})))

(defn- create* [store db coll doc params]
  (create-document store db coll doc))

(defn- create-database* [store db params]
  (create-database store db))

(defn- update* [store db coll id doc params]
  (replace-document store db coll id doc))

(defn- remove* [store db coll doc params]
  (remove-document store db coll doc params))
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; DB Service ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn Database [& [opts]]
  (let [endpoint  (:endpoint  opts (degree9.env/get :documentdb-endpoint))
        masterkey (:masterkey opts (degree9.env/get :documentdb-key))

        id (:idField opts "id")

        store (dbclient. endpoint #js{:masterKey masterkey})]
    (reify
      Object
      (id [this] id)
      (find [this params]
        (find-database* store (js->clj params)))
      (get [this id params]
        (get-database* store id (js->clj params)))
      (create [this data params]
        (create-database* store data (js->clj params))))))
      ;(update [this id data params]
      ;  (update* store database collection id data (js->clj params)))
      ;(patch [this id data params]
      ;  ())
      ;(remove [this id params]
      ;  (remove* store database collection id (js->clj params))))))

(defn Collection [collection & [opts]]
  (let [endpoint  (:endpoint  opts (degree9.env/get :documentdb-endpoint))
        masterkey (:masterkey opts (degree9.env/get :documentdb-key))
        database  (:database  opts (degree9.env/get :documentdb-database))

        id (:idField opts "id")

        store (dbclient. endpoint #js{:masterKey masterkey})]
    (reify
      Object
      (id [this] id)
      (find [this params]
        (find* store database collection (js->clj params)))
      (get [this id params]
        (get* store database collection id (js->clj params)))
      (create [this data params]
        (create* store database collection data (js->clj params)))
      (update [this id data params]
        (update* store database collection id data (js->clj params)))
      ;(patch [this id data params]
      ;  ())
      (remove [this id params]
        (remove* store database collection id (js->clj params))))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
