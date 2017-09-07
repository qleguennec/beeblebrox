(ns api42.components
  (:require [re-frame.core :as rf]
            [reagent.core :as r]))

(defn text-input [label submit]
  (let [input (r/atom "")]
    [:div {:class "text-input"}
     [:span label]
     [:input {:type "text"
              :on-change #(reset! input (-> % .-target .-value))}]
     [:button {:type "submit"
               :on-click #(submit @input)} "submit"]]))
