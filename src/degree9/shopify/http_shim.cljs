; https://gist.github.com/moea/63d7a882e4ad333f3b5a
(ns degree9.shopify.http-shim
  (:import
   [goog.net XmlHttp XmlHttpFactory])
  (:require
   [cljs.nodejs :as nodejs]
   ["xmlhttprequest" :as xmlhttprequest]))

(def xml-http-request
  (-> xmlhttprequest
      (.. -XMLHttpRequest)))

(defn NodeXhrFactory []
  (this-as this (.call XmlHttpFactory this)))

(goog/inherits NodeXhrFactory XmlHttpFactory)

(set!
 (.. NodeXhrFactory -prototype -createInstance)
 #(xml-http-request.))

(set!
 (.. NodeXhrFactory -prototype -internalGetOptions)
 (constantly #js {}))

(defn set-global-xhr-factory! []
  (.setGlobalFactory XmlHttp (NodeXhrFactory.)))
