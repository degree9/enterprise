(ns degree9.wizard
  "Handles state for building a wizard component."
  (:require [hoplon.core :as h]
            [javelin.core :as j]
            [degree9.debug :as dbg]
            [uikit-hl.button :as button]
            [uikit-hl.flex :as flex]
            [uikit-hl.grid :as grid]
            [uikit-hl.modal :as modal]
            [uikit-hl.padding :as padding]
            [uikit-hl.position :as position]
            [uikit-hl.section :as section]
            [uikit-hl.tab :as tab]
            [uikit-hl.text :as text]
            [uikit-hl.utility :as util]
            [uikit-hl.width :as width]))

(dbg/defdebug debug "degree9::experience::wizard")

(defn- current-step [needle haystack]
  (first (keep-indexed #(when (= %2 needle) %1) haystack)))

(h/defelem simple-wizard [attr kids]
  (let [current   (j/cell 0)
        steps     (j/cell= (:steps attr))
        step      (j/cell= (get steps current))
        previous! (:previous! attr #(swap! current dec))
        next!     (:next! attr #(swap! current inc))
        submit!   (:submit! attr debug)
        previous? (j/cell= (:previous? step (:previous? attr)))
        next?     (j/cell= (:next? step (:next? attr)))
        complete? (j/cell= (:complete? step (:complete? attr)))]
    (modal/modal
      (modal/dialog
        (modal/header
          (modal/title (:title attr "Simple Wizard")))
        (modal/body ::section/muted true ::padding/small true
          (grid/grid ::width/width-1-1 true
            (grid/cell ::width/width-auto true
              (tab/tab :left true ::width/child-width-auto true
                (h/for-tpl [[i s] (j/cell= (map-indexed (partial vector) steps))]
                  (tab/item ::util/active (j/cell= (= s step))
                    :click #(reset! current @i)
                    (h/a (h/text "~{(:title s)}"))))))
            (grid/cell ::width/width-expand true
              (j/cell= (get kids current)))))
        (modal/footer
          (grid/grid :collapse true ::flex/flex true ::flex/middle true
            (grid/cell ::text/left true ::width/width-auto true
              (h/text "~{(str (inc current) \" / \" (count steps))}"))
            (grid/cell ::text/right true ::width/width-expand true
              (button/group ::width/child-width-auto true
                (h/when-tpl previous?
                  (button/button :default true
                    :click previous!
                    (:previous attr "Previous")))
                (h/when-tpl next?
                  (button/button :default true
                    :click next!
                    (:next attr "Next")))
                (h/when-tpl complete?
                  (button/button :primary true
                    :click submit!
                    (:complete attr "Complete")))))))))))
