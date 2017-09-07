(ns api42.subs
  (:require-macros [reagent.ratom :refer [reaction]])
  (:require [re-frame.core :as rf]))

(rf/reg-sub
 :name
 (fn [db]
   (:name db)))

(rf/reg-sub
 :projects/list
 (fn [db]
   (:projects/list db)))

(rf/reg-sub
 :auth/token
 (fn [db]
   (:token db)))
