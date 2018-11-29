(ns degree9.ui
  (:require [hoplon.core :as h]
            [uikit-hl.navbar :as navbar])
  (:require-macros [degree9.ui]))

(h/defelem navbar [{:keys [left center right] :as attr} kids]
  (let [attr (dissoc attr :left :center :right)]
    (navbar/container attr
      (navbar/navbar
        (navbar/left left)
        (navbar/center center)
        (navbar/right right)))))
