(ns degree9.browser
  (:require [degree9.object :as obj]
            [degree9.events :as events]))

(defprotocol IWindow
  "Interface for interacting with Browser Window."
  (console        [this] "Returns a reference to the Console object.")
  (customElements [this] "Returns a reference to the CustomElementRegistry object.")
  (document       [this] "Returns a reference to the Document object.")
  (history        [this] "Returns a reference to the History object.")
  (indexedDB      [this] "Returns a reference to the IDBFactory object.")
  (localStorage   [this] "Returns a reference to the Local Storage object.")
  (location       [this] "Returns a reference to the Location object.")
  (navigator      [this] "Returns a reference to the Navigator object.")
  (sessionStorage [this] "Returns a reference to the Session Storage object."))

(extend-protocol IWindow
  js/Window
  (console        [this] (.-console this))
  (customElements [this] (.-customElements this))
  (document       [this] (.-document this))
  (history        [this] (.-history this))
  (indexedDB      [this] (.-indexedDB this))
  (localStorage   [this] (.-localStorage this))
  (location       [this] (.-location this))
  (navigator      [this] (.-navigator this))
  (sessionStorage [this] (.-sessionStorage this)))

(defn window []
  js/window)

(defn listen [type handler]
  (events/listen js/window type handler))

(defn unlisten [type handler]
  (events/unlisten js/window type handler))

(defn dispatch! [event]
  (events/dispatch! js/window event))
