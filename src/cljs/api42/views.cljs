(ns api42.views
  (:require [re-frame.core :as rf]
            [api42.components :refer [text-input]]))

(defn project-list []
  (let [projects (rf/subscribe [:projects/list])]
    [:div {:class "project-list"}]
    [:h2 (str (count @projects) " projects")]
    [:ul
     (for [p @projects
           :let [name (:name p)]]
       ^{:key name} [:li name])]))

(defn main-panel []
  (let [name (rf/subscribe [:name])]
    [:div {:class "main-panel"}
     [:h1 {:class "title"} @name]
     [text-input "filter projects: " #(rf/dispatch [:text-input %])]
     [project-list]]))
