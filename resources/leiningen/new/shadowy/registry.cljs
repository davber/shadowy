(ns {{ns}}.web.events.registry
  "The Reframe registry, for all subscriptions, handlers and effects"
  (:require [re-frame.core :as rf]
            [{{ns}}.web.events.handlers :as h]
            [{{ns}}.web.events.effects :as e]))

;;
;; Reframe event handlers
;;

(rf/reg-event-fx :db/init h/db-init)
(rf/reg-event-fx :{{ns}}/inc-counter h/inc-counter)
(rf/reg-event-fx :{{ns}}/set-counter h/set-counter)
(rf/reg-event-fx :{{ns}}/delayed-set-counter h/delayed-set-counter)

;;
;; Reframe subscriptions
;;

(rf/reg-sub :{{ns}}/counter (fn [db & _] (:counter db)))

;;
;; Reframe effects
;;

(rf/reg-fx :fx/delayed-set-counter e/delayed-set-counter)