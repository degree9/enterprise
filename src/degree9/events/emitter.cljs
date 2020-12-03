(ns degree9.events.emitter
  (:require ["events" :as e]))

;; EventTarget Protocol ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defprotocol IEventEmitter
  (addListener
    [this event listener]
    "Alias for (emitter/on event listener).")
  (removeListener
    [this event listener]
    "Removes the specified listener from the listener array for the event.")
  (emit
    [this event args]
    "Synchronously calls each of the listeners registered for the event.")
  (on
    [this event listener]
    "Adds the listener function to the end of the listeners array for the event.")
  (off
    [this event listener]
    "Alias for (emitter/removeListener event listener)."))
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(extend-type e/EventEmitter
  IEventEmitter
  (addListener
    [this event listener]
    (.addListener this event listener))
  (removeListener
    [this event listener]
    (.removeListener this event listener))
  (emit
    [this event args]
    (apply this.emit event args))
  (on
    [this event listener]
    (.on this event listener))
  (off
    [this event listener]
    (.off this event listener)))
