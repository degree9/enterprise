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

(h/defelem simple-wizard [{:keys [steps previous next cancel complete] :as attr} kids]
  (let [current   (j/cell 0)
        step      (j/cell= (get steps current))
        id        (:id attr (gensym))]
    (modal/modal :id id
      (modal/dialog attr
        (modal/header
          (modal/title (:title attr "Simple Wizard")))
        (modal/body ::section/muted true ::padding/small true
          (grid/grid ::width/width-1-1 true
            (grid/cell ::width/width-auto true
              (tab/tab :left true ::width/child-width-auto true
                (h/for-tpl [[key val] (j/cell= (map-indexed vector steps))]
                  (tab/item ::util/active (j/cell= (= val step))
                    :click #(reset! current @key)
                    (h/a (h/text "~{(:title val)}"))))))
            (grid/cell ::width/width-expand true
              (j/cell= (get kids current)))))
        (modal/footer
          (grid/grid :collapse true ::flex/flex true ::flex/middle true
            (grid/cell ::text/left true ::width/width-auto true
              (h/text "~{(str (inc current) \" / \" (count steps))}"))
            (grid/cell ::text/right true ::width/width-expand true
              (button/group ::width/child-width-auto true
                (h/when-tpl (j/cell= (:cancel? step (:cancel step (:cancel? attr))))
                  (button/button
                    :default true
                    ::modal/close true
                    :disabled (j/cell= (get-in step [:cancel :disabled]))
                    (j/cell= (get-in step [:cancel :text] (get cancel :text cancel)))))
                (h/when-tpl (j/cell= (:previous? step (:previous step (:previous? attr))))
                  (button/button
                    :default true
                    :click (:previous! attr #(swap! current dec))
                    :disabled (j/cell= (get-in step [:previous :disabled]))
                    (j/cell= (get-in step [:previous :text] (get previous :text previous)))))
                (h/when-tpl (j/cell= (:next? step (:next step (:next? attr))))
                  (button/button
                    :default true
                    :click (:next! attr #(swap! current inc))
                    :disabled (j/cell= (get-in step [:next :disabled]))
                    (j/cell= (get-in step [:next :text] (get next :text next)))))
                (h/when-tpl (j/cell= (:complete? step (:complete step (:complete? attr))))
                  (button/button
                    :primary true
                    :click (:submit! attr)
                    :disabled (j/cell= (get-in step [:complete :disabled]))
                    (j/cell= (get-in step [:complete :text] (get complete :text complete)))))))))))))
