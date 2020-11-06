(ns degree9.proxy
  "Provides methods for proxying http requests to another remote service via REST."
  (:require
    [degree9.debug :as dbg]
    [meta.server :as server]
    [meta.remote :as remote]))

(dbg/defdebug debug "degree9:enterprise:proxy")

(defn api
  "Mount a remote service to a local endpoint."
  [app path uri hooks]
  (debug "Mount remote service %s at %s" uri path)
  (server/api app path (remote/service path {:uri uri}) hooks))
