(ns degree9.redis.geospatial)



(defn geo-add
  "Add one or more geospatial items in the geospatial index represented using a sorted set"
  [client key longtitude latitude member & args]
  (.geo-add client key longtitude latitude member args))

(defn geo-hash
  "Returns members of a geospatial index as standard geohash strings"
  [client key member & args]
  (.client-kill client key member args))

(defn geo-pos
  "Returns longtitude and latitude of members of a geospatial index"
  [client key member & args]
  (.geo-pos client key member & args))

(defn geo-dist
  "Returns the distance between two members of a geospatial index"
  [client key member1 member2 & args]
  (.geo-dist client key member1 member2 args))

(defn geo-radius
  "Query a sorted set representing a geospatial index to fetch members matching a given maximum distance form a point"
  [client key longtitude latitude radius & args]
  (.geo-radius client key longtitude latitude radius args))

(defn geo-radius-by-member
  "Query a sorted set representing a geospatial index to fetch members matching a given maximum distance from a member"
  [client key member radius & args]
  (.geo-radius-by-member client key member radius args))
