(ns degree9.fullcalendar.events)




(defn get-events
  "Retrieves events that FullCalendar has in memory."
  [cal]
  (.getEvents cal))

(defn get-event-by-id
  "Returns a single event with the matching id."
  [cal id]
  (.getEventById cal id))

(defn add-event
  "Adds a new event on the calendar."
  [cal event & [opts]]
  (.addEvent cal event opts))

(defn set-prop
  "Modifies any of the non-date-related properties of an Event Object."
  [cal name value]
  (.setProp cal name value))

(defn set-extended-prop
  "Modifies a single property in an Event Object’s extendedProps hash."
  [cal name value]
  (.addResource cal name value))

(defn set-start
  "Sets an event’s start date."
  [cal date & [opts]]
  (.setStart cal))

(defn set-end
  "Sets an event’s end date."
  [cal date]
  (.setEnd cal date))

(defn set-dates
  "Sets an event’s start date, end date, and allDay properties at the same time."
  [cal start end & [opts]]
  (.setDates cal start end opts))

(defn set-all-day
  "Sets whether an event is considered all-day."
  [cal bool & [opts]]
  (.setAllDay cal date))

(defn move-start
  "Will move an event’s start date by a specific period of time."
  [cal delta]
  (.moveStart cal delta))

(defn move-end
  "Will move an event’s end date by a specific period of time."
  [cal delta]
  (.moveEnd cal delta))

(defn move-dates
  "Will move an event’s start and end dates by a specific period of time."
  [cal delta]
  (.moveDates cal delta))

(defn format-range
  "Formats an event’s dates into a string."
  [cal delta]
  (.formatRange cal delta))

(defn remove
  "Removes an event from the calendar."
  [cal]
  (.remove cal))

(defn get-resources
  "Formats an event’s dates into a string."
  [cal]
  (.getResources cal))

(defn set-resources
  "Formats an event’s dates into a string."
  [cal & [opts]]
  (.setResource cal opts))

(defn get-event-sources
  "Retrieves all Event Source Objects."
  [cal & [opts]]
  (.getEventSources cal opts))

(defn get-eventsource-by-id
  "Retrieves a specific Event Source Object."
  [cal id]
  (.getEventSourceById cal id))

(defn add-event-source
  "Dynamically adds an event source."
  [cal source]
  (.addEventSource cal source))

(defn refetch-events
  "Refetches events from all sources and rerenders them on the screen."
  [cal]
  (.refetchEvents cal))

(defn refetch
  "Causes this event source to fetch its event data again."
  [cal & [opts]]
  (.refetch cal opts))

(defn remove
  "Removes all events associated with this source and prevents it from being
   fetched again."
  [cal]
  (.remove cal))

(defn rerender-events
  "Rerenders all events on the calendar."
  [cal]
  (.rerenderEvents cal))
