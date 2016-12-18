(ns learngeomviz.views
    (:require [re-frame.core :as re-frame]
              [thi.ng.geom.viz.core :as viz]
              [thi.ng.geom.svg.core :as svg]
              [thi.ng.geom.core.vector :as v]
              [thi.ng.color.core :as col]
              [thi.ng.math.core :as m :refer [PI TWO_PI]]))

(def loglinearspec
  {:x-axis (viz/log-axis
             {:domain [1 201]
              :range  [50 590]
              :pos    550})
   :y-axis (viz/linear-axis
             {:domain      [0.1 100]
              :range       [550 20]
              :major       10
              :minor       5
              :pos         50
              :label-dist  15
              :label-style {:text-anchor "end"}})
   :grid   {:attribs {:stroke "#caa"}
            :minor-x true
            :minor-y true}
   :data   [{:values  (map (juxt identity #(Math/sqrt %)) (range 0 200 2))
             :attribs {:fill "#0af" :stroke "none"}
             :layout  viz/svg-scatter-plot}
            {:values  (map (juxt identity #(m/random %)) (range 0 200 2))
             :attribs {:fill "none" :stroke "#f60"}
             :shape   (viz/svg-triangle-down 6)
             :layout  viz/svg-scatter-plot}]})

(defn loglinearimage
  [spec]
  (->> spec
       (viz/svg-plot2d-cartesian)
       (svg/svg {:width 600 :height 600})
       ))

(def loglogspec
  {:x-axis (viz/log-axis
             {:domain [1 201]
              :range  [50 590]
              :pos    550})
   :y-axis (viz/log-axis
             {:domain      [0.1 101]
              :range       [550 20]
              :pos         50
              :label-dist  15
              :label-style {:text-anchor "end"}})
   :grid   {:attribs {:stroke "#caa"}
            :minor-x true
            :minor-y true}
   :data   [{:values  (map (juxt identity #(Math/sqrt %)) (range 0 200 2))
             :attribs {:fill "#0af" :stroke "none"}
             :layout  viz/svg-scatter-plot}
            {:values  (map (juxt identity #(m/random %)) (range 0 200 2))
             :attribs {:fill "none" :stroke "#f60"}
             :shape   (viz/svg-triangle-down 6)
             :layout  viz/svg-scatter-plot}]})

(defn loglogimage
  [spec]
  (->> spec
       (viz/svg-plot2d-cartesian)
       (svg/svg {:width 600 :height 600})
       ))

(defn main-panel []
  (let [name (re-frame/subscribe [:name])]
    (fn []
      [:div "Hello  " @name
       [loglinearimage loglinearspec]
       [loglogimage loglogspec]])))
