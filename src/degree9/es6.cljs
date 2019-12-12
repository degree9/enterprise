(ns degree9.es6
  (:require [degree9.debug :as dbg])
  (:require-macros degree9.es6))

(dbg/defdebug debug "degree9:enterprise:es6")

; (defn mk-constructor []
;   (fn [& args]
;     (this-as this
;       (when-let [constructor (.-constructor this)]
;         (apply constructor this args))
;       this)))
;
; (defn- set-prototype [class extends]
;   (.setPrototypeOf js/Object class extends))
;
; (defn inherit-class [class extends]
;   (goog/inherits class extends))
;
; (defn- object-assign [class mixin]
;   (.assign js/Object class (cljs.core/clj->js mixin)))
;
; (defn mixin-class [class mixins]
;   (let [class (.create js/Object class)
;         mixins (if (map? mixins) [mixins] mixins)]
;     (doseq [m mixins]
;       (object-assign class m))))
;
; (defn invoke-super [parent method context args]
;   (let [args (into-array args)]
;     (if (= method "constructor")
;       (.apply parent context args)
;       (.apply (aget (.-prototype parent) method) context args))))

(defn- method-fn-name
  [method-name]
  (str "__pylon$method$" method-name))

(defn- pylon-prop? [prop]
  (= "__pylon$" (subs prop 0 8)))

(defn- pylon-parent-proto [p]
  (when-let [parent (aget p "__pylon$superclass")]
    (when-let [proto (.-prototype parent)]
      (when (.hasOwnProperty proto "__pylon$classname")
        proto))))

(defn- find-props [p]
  (let [parent (pylon-parent-proto p)
        props (remove pylon-prop? (.getOwnPropertyNames js/Object p))]
    (if parent
      (concat props (find-props parent))
      props)))

(defn create-ctor []
  (fn ctor [& args]
    (this-as
     this
     (let [p (.getPrototypeOf js/Object this)
           superclass (aget p "__pylon$superclass")]
       (doseq [bind (apply hash-set (find-props p))]
         (let [func (aget this bind)]
           (when (fn? func)
             (aset this bind (goog/bind func this))))))
     (when-let [constructor (.-constructor this)]
       (.apply constructor this (into-array args)))
     this)))

(defn invoke-super [superclass method context args]
  (let [proto (.-prototype superclass)
        foreign? (nil? (aget proto "__pylon$classname"))
        method-name (if foreign? method (method-fn-name method))
        args (if foreign? args (cons context args))
        super-method (aget proto method-name)
        super-fn (if (and (= method "constructor") (not super-method))
                   superclass super-method)
        args (into-array args)]
    (.apply super-fn context args)))

(defn method-wrapper [funcname]
  (fn [& args]
    (this-as this (apply (aget this funcname) (cons this args)))))
