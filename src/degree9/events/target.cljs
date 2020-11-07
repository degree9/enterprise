(ns degree9.events.target)

;; EventTarget Protocol ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defprotocol IEventTarget
  (addEventListener
    [this type listener] [this type listener options]
    "Registers an event handler of a specific event type on the target.")
  (removeEventListener
    [this type listener] [this type listener options]
    "Removes an event listener from the target.")
  (dispatchEvent
    [this event]
    "Dispatches an event to this target."))
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(extend-type js/EventTarget
  IEventTarget
  (addEventListener
    ([this type listener] (.addEventListener this type listener))
    ([this type listener options] (.addEventListener this type listener options)))
  (removeEventListener
    ([this type listener] (.removeEventListener this type listener))
    ([this type listener options] (.removeEventListener this type listener options)))
  (dispatchEvent
    ([this event] (.dispatchEvent this event))))
