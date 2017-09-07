(ns api42.effects
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require [re-frame.core :as rf]
            [cljs.core.async :refer [<! take!]]
            [cljs-http.client :as http]))

(rf/reg-fx
 :GET
 (fn [{:keys [url token on-success on-fail]}]
   (take! (http/get url {:with-credentials? false
                         :oauth-token token})
          #(rf/dispatch
            (conj (if (:success %) on-success on-fail) %)))))

(rf/reg-fx
 :POST
 (fn [{:keys [url params on-success on-fail]}]
   (take! (http/post url {:form-params params})
          #(rf/dispatch
            (conj (if (:success %) on-success on-fail) %)))))
