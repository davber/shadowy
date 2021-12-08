(ns {{ns}}.web.views
  "The main React components are here"
  (:require
    [re-frame.core :as rf]
    {{ns}}.web.events.registry))

(defn home
  "The root React component"
  []
  (let [counter @(rf/subscribe [:{{ns}}/counter])]
    [:div
     [:h3 "Welcome to {{name}}!"]
     [:p "This is where all the interesting UX should be"]
     [:p "Counter is currently " counter]
     [:button {:onClick #(rf/dispatch [:{{ns}}/inc-counter])} "Increment"]
     [:button {:onClick #(rf/dispatch [:{{ns}}/delayed-set-counter 42])} "Set To 42 -- Delayed"]]))
