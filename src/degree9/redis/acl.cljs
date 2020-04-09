(ns degree9.redis.acl)




(defn acl-load
  "Reload the ACLs from the configured ACL file"
  [client]
  (.acl-load client))

(defn acl-save
  "Save the current ACL rules in the configured ACL file"
  [client]
  (.acl-save client))

(defn acl-list
  "List the current ACL rules in ACL config file format"
  [client]
  (.acl-list client))

(defn acl-users
  "List the username of all the configured ACL rules"
  [client]
  (.acl-users client))

(defn acl-set-users
  "Modify or create the rules for a specific ACL user"
  [client rule & args]
  (.acl-set-users client rule args))

(defn acl-del-users
  "Remove the specified ACL users and the associated rules"
  [client username & args]
  (.acl-del-users client username args))

(defn acl-cat
  "List the ACL categories users and the associated rules"
  [client categoryname]
  (.acl-cat client categoryname))

(defn acl-genpass
  "Generate a pseudorandom secure passowrd to use for ACL users"
  [client]
  (.acl-genpass client))

(defn acl-whoami
  "Return the name of the user associated to the current connection"
  [client]
  (.acl-whoami client))

(defn acl-log
  "List latest events denied because of ACLs in place"
  [client & args]
  (.acl-log client args))
