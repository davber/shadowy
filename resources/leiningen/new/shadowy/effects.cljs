(ns {{ns}}.web.events.effects
  "The Reframe effects"
  (:require [re-frame.core :as rf]))

(defn delayed-set-counter
  "Delaying the setting of the counter"
  [value]
  (js/setTimeout #(rf/dispatch [:{{ns}}/set-counter value]) 4000))
