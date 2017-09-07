(ns api42.events.fx
  (:require [re-frame.core :as rf]
            [day8.re-frame.async-flow-fx]
            [api42.db :as db]
            [api42.secret :as secret]))

(rf/reg-event-fx
 :boot
 (fn [_ _]
   {:db db/default-db
    :async-flow {:first-dispatch [:auth]
                 :rules
                 [{:when :seen? :events :auth/success :dispatch [:projects/fetch]}]}}))

(rf/reg-event-fx
 :projects/fetch
 (fn [cofx page]
   (let [token (get-in cofx [:db :token])]
     {:GET {:url "https://api.intra.42.fr/v2/cursus/42/projects"
            :token token
            :on-success [:projects/fetch-success]
            :on-fail [:projects/fetch-fail]}})))

(rf/reg-event-fx
 :auth
 (fn [{:keys [db]} _]
   {:POST
    {:url "https://api.intra.42.fr/oauth/token"
     :on-success [:auth/success]
     :on-fail [:auth/fail]
     :params
     {:grant_type "client_credentials"
      :client_id secret/client-id
      :client_secret secret/client-secret}}}))
