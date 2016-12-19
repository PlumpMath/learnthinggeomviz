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
       (svg/svg {:width 600 :height 600})))


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
       (svg/svg {:width 600 :height 600})))


(defn test-equation
  [t] (let [x (m/mix (- PI) PI t)] [x (* (Math/cos (* 0.5 x)) (Math/sin (* x x x)))]))

(def linearspec
  {:x-axis (viz/linear-axis
             {:domain [(- PI) PI]
              :range  [50 580]
              :major  (/ PI 2)
              :minor  (/ PI 4)
              :pos    250})
   :y-axis (viz/linear-axis
             {:domain      [-1 1]
              :range       [250 20]
              :major       0.2
              :minor       0.1
              :pos         50
              :label-dist  15
              :label-style {:text-anchor "end"}})
   :grid   {:attribs {:stroke "#caa"}
            :minor-y true}
   :data   [{:values  (map test-equation (m/norm-range 200))
             :attribs {:fill "none" :stroke "#0af"}
             :layout  viz/svg-line-plot}]})

(defn linearimage
  [spec]
  (->> spec
       (viz/svg-plot2d-cartesian)
       (svg/svg {:width 600 :height 320})))


(def linearareaspec
  {:x-axis (viz/linear-axis
             {:domain [(- PI) PI]
              :range  [50 580]
              :major  (/ PI 2)
              :minor  (/ PI 4)
              :pos    250})
   :y-axis (viz/linear-axis
             {:domain      [-1 1]
              :range       [250 20]
              :major       0.2
              :minor       0.1
              :pos         50
              :label-dist  15
              :label-style {:text-anchor "end"}})
   :grid   {:attribs {:stroke "#caa"}
            :minor-y true}
   :data   [{:values  (map test-equation (m/norm-range 200))
             :attribs {:fill "#0af"}
             :layout  viz/svg-area-plot}]})

(defn linearareaimage
  [spec]
  (->> spec
       (viz/svg-plot2d-cartesian)
       (svg/svg {:width 600 :height 320})))


(def linepolarspec
  {:x-axis (viz/linear-axis
             {:domain [(- PI) PI]
              :range  [(* 1.1 PI) (* 1.9 PI)]
              :major  (/ PI 2)
              :minor  (/ PI 16)
              :pos    280})
   :y-axis (viz/linear-axis
             {:domain [-1 1]
              :range  [60 280]
              :major  0.5
              :minor  0.25
              :pos    (* 1.1 PI)})
   :origin (v/vec2 300 310)
   :grid   {:attribs {:stroke "#caa" :fill "none"}
            :minor-x true
            :minor-y true}
   :data   [{:values  (map test-equation (m/norm-range 200))
             :attribs {:fill "none" :stroke "#0af"}
             :layout  viz/svg-line-plot}]})

(defn linepolarimage
  [spec]
  (->> spec
       (viz/svg-plot2d-polar)
       (svg/svg {:width 600 :height 320})))

(def areapolarspec
  {:x-axis (viz/linear-axis
             {:domain [(- PI) PI]
              :range  [(* 1.1 PI) (* 1.9 PI)]
              :major  (/ PI 2)
              :minor  (/ PI 16)
              :pos    280})
   :y-axis (viz/linear-axis
             {:domain [-1 1]
              :range  [60 280]
              :major  0.5
              :minor  0.25
              :pos    (* 1.1 PI)})
   :origin (v/vec2 300 310)
   :grid   {:attribs {:stroke "#caa" :fill "none"}
            :minor-x true
            :minor-y true}
   :data   [{:values  (map test-equation (m/norm-range 200))
             :attribs {:fill "#0af"}
             :layout  viz/svg-area-plot}]})

(defn areapolarimage
  [spec]
  (->> spec
       (viz/svg-plot2d-polar)
       (svg/svg {:width 600 :height 320})))

(defn bar-spec
  [num width]
  (fn [idx col]
    {:values     (map (fn [i] [i (m/random 100)]) (range 2000 2016))
     :attribs    {:stroke       col
                  :stroke-width (str (dec width) "px")}
     :layout     viz/svg-bar-plot
     :interleave num
     :bar-width  width
     :offset     idx}))

(def bar-single-spec
  {:x-axis (viz/linear-axis
             {:domain [1999 2016]
              :range  [50 580]
              :major  1
              :pos    280})
   :y-axis (viz/linear-axis
             {:domain      [0 100]
              :range       [280 20]
              :major       10
              :minor       5
              :pos         50
              :label-dist  15
              :label-style {:text-anchor "end"}})
   :grid   {:minor-y true}
   :data [((bar-spec 1 20) 0 "#0af")]})

(defn bar-single-image
  [spec]
  (->> spec
      (viz/svg-plot2d-cartesian)
      (svg/svg {:width 600 :height 320})))

(def bar-multi-spec
  {:x-axis (viz/linear-axis
             {:domain [1999 2016]
              :range  [50 580]
              :major  1
              :pos    280})
   :y-axis (viz/linear-axis
             {:domain      [0 100]
              :range       [280 20]
              :major       10
              :minor       5
              :pos         50
              :label-dist  15
              :label-style {:text-anchor "end"}})
   :grid   {:minor-y true}
   :data (map-indexed (bar-spec 3 6) ["#0af" "#fa0" "#f0a"])})

(defn bar-multi-image
  [spec]
  (->> spec
       (viz/svg-plot2d-cartesian)
       (svg/svg {:width 600 :height 320})))

(defn main-panel []
  (let [name (re-frame/subscribe [:name])]
    (fn []
      [:div
       [loglinearimage loglinearspec]
       [loglogimage loglogspec]
       [linearimage linearspec]
       [linearareaimage linearareaspec]
       [linepolarimage linepolarspec]
       [areapolarimage areapolarspec]
       [bar-single-image bar-single-spec]
       [bar-multi-image bar-multi-spec]])))

