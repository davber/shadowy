(ns {{ns}}.web.events.handlers
  "The Reframe handlers of events")

(def DEFAULT-DB {:counter 0})

(defn db-init
  "The initial Reframe DB"
  [_coeffects _event]
  {:db DEFAULT-DB})

(defn inc-counter
  "Increment counter"
  [{:keys [db]} _event]
  {:db (update db :counter inc)})

(defn delayed-set-counter
  "Set counter, with a twist, by using effect to delay it"
  [_coeffects [_ value]]
  ;; NOTE: it might be good to use the namespace 'fx' for effect IDs
  {:fx/delayed-set-counter value})

(defn set-counter
  "Set counter"
  [{:keys [db]} [_ value]]
  {:db (assoc db :counter value)})
