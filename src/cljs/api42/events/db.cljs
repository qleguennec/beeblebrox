(ns api42.events.db
  (:require [re-frame.core :as rf]))

(rf/reg-event-db
 :auth/success
 (fn [db [_ resp]]
   (assoc db :token (get-in resp [:body :access_token]))))

(rf/reg-event-db
 :projects/fetch-success
 (fn [db [_ resp]]
   (assoc db :projects/list (get-in resp [:body]))))

