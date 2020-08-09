(ns degree9.fullcalendar.date)




(defn format-date
  "A utility function that formats a date into a string"
  [cal date settings]
  (.formatDate cal date settings))

(defn format-range
  "Formats two dates, a start and an end, into a string. A separator string,
   most likely a dash, will be intelligently inserted between the two dates."
  [cal start end settings]
  (.formatDate cal start end settings))

(defn format-iso
  "Formats a date into an ISO8601 string. Outputs a UTC offset appropriate to the calendar it’s called on."
  [cal start end settings]
  (.formatiso cal start end settings))

(defn scroll-to-time
  "Programatically scroll the current view to the given time"
  [cal durationinput]
  (.scrollToTime cal durationinput))
