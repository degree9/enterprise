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

(defn scroll-to-time
  "Programatically scroll the current view to the given time"
  [cal durationInput]
  (.scrollToTime cal durationInput))
