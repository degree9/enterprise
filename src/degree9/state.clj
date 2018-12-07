(ns degree9.state)

;; State Debug ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defmacro debug-state []
  `(javelin.core/cell=
    (prn {:loading  *loading*
          :setup    *setup*
          :finished *finished*
          :success  *success*
          :error    *error*
          :empty    *empty*
          :data     *data*
          :state    *state*})))
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; State Macros ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defmacro local-state
  "Creates a new local state scope based on `data`."
  [data & forms]
  `(let [~'*data*     ~data
         ~'*loading*  (loading-state  ~data)
         ~'*setup*    (setup-state    ~data)
         ~'*finished* (finished-state ~data)
         ~'*success*  (success-state  ~data)
         ~'*error*    (error-state    ~data)
         ~'*empty*    (empty-state    ~data)
         ~'*state*    (current-state  ~'*data* ~'*loading* ~'*setup* ~'*finished*
                                      ~'*success* ~'*error* ~'*empty*)]
      ~@forms))

(defmacro rebind-state
  "Rebinds local state to dynamic variables."
  [& forms]
  `(binding [*data*     ~'*data*
             *loading*  ~'*loading*
             *setup*    ~'*setup*
             *finished* ~'*finished*
             *success*  ~'*success*
             *error*    ~'*error*
             *empty*    ~'*empty*
             *state*    ~'*state*]
     ~@forms))

(defmacro with-state
  "Creates a new state scope using local and dynamic variables."
  [data & forms]
  `(local-state ~data
     (rebind-state ~@forms)))
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; State Templating Macros ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defmacro state-tpl
  "A state based template macro.

  Analogous to:

    (with-state data
      (case-tpl *state* forms))"
  [data & forms]
  `(with-state ~data
    (hoplon.core/case-tpl *state* ~@forms)))
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
