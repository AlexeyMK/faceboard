(ns faceboard.css.page
  (:use [faceboard.lib.constants])
  (:require [faceboard.lib.helpers :refer [>> mv px]]))

(def styles
  [(>> :#app
     [:display "block !important"
      :font-size (px 14)
      :line-height 1.4
      :font-family base-font
      :background-color selected-tab-color])
   (>> page
     (>> loading-indicator
       [:position :absolute
        :z-index 11
        :color "#bbb"
        :top (px 1)
        :left (px 5)
        :opacity 0
        :transition "opacity .3s ease-in-out"]
       (>> &.visible
         [:opacity 1]))
     (>> banner
       [:position :fixed
        :z-index 1000
        :bottom (px 0)
        :right (px 0)
        :color "#888"
        :font-size (px 10)
        :background-color "#fff64c"
        :opacity 0.5
        :padding (px 1 10)]
       (>> :a
         [:color "#888"]
         (>> &:hover
           [:color "blue"]))))
   (>> standard-page
     [:margin (px 20)]
     (>> button
       [:display "inline-block"
        :cursor :pointer
        :padding (px 4 20)
        :min-width (px 100)
        :width (px 200)
        :background-color signature-color
        :color "#ccc"
        :border-radius (px 2)
        :border "2px solid #3a4d81"
        :transition "color .3s ease-in-out"
        :margin-left (px 10)
        :box-shadow "0px 0px 10px 0px rgba(0,0,0,0.5)"]
       (>> &.disabled
         [:cursor :default
          :opacity 0.5])
       (>> &:hover
         [:color "#fff"
          :border-color signature-color])))
   (>> slim-layout
     [:width (px 600)
      :margin-left :auto
      :margin-right :auto])])