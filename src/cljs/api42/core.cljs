(ns api42.core
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require [reagent.core :as reagent]
            [re-frame.core :as rf]
            [api42.effects]
            [api42.events.fx]
            [api42.events.db]
            [api42.subs]
            [api42.views :as views]
            [cljs.core.async :refer [take!]]
            [cljs-http.client :as http]
            [api42.config :as config]))

(defn dev-setup []
  (when config/debug?
    (enable-console-print!)
    (println "dev mode")))

(defn render [comp]
  (reagent/render [views/main-panel]
                  (.getElementById js/document "app")))

(defn mount-root []
  (rf/clear-subscription-cache!)
  (render [views/main-panel]))

(defn ^:export init []
  (rf/dispatch-sync [:boot])
  (dev-setup)
  (render [:h1 "waiting for authentification"])
  (mount-root))
