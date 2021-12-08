(ns {{ns}}.web.core
  "This is the entry point of the React app"
  (:require
    [taoensso.timbre :as timbre]
    [reagent.dom :as reagent]
    [re-frame.core :as rf]
    [{{sanitized}}.web.views :as views]))

(def ASSERTING "Should we assert on specs? NB: this is quite slow" (boolean js/goog.DEBUG))

(defn mount-root
  []
  (timbre/info "mount-root")
  (reagent/render
    [views/home]
    (.getElementById js/document "app")))

(defn ^:export run
  [& {:keys [init-db] :or {init-db true}}]
  (println "goog.DEBUG = " js/goog.DEBUG)
  (timbre/set-level! (if js/goog.DEBUG :trace :info))
  (s/check-asserts ASSERTING)
  (println "run with init-db = " init-db)
  (when init-db
    (rf/dispatch-sync [:db/init]))
  (mount-root))

(defn ^:export on-jsload
  []
  (timbre/info "on-jsload")
  (run))

(defn ^:dev/after-load clear-cache-and-render!
  []
  (timbre/info "clear-cache-end-render!")
  ;; The `:dev/after-load` metadata causes this function to be called
  ;; after shadow-cljs hot-reloads code. We force a UI update by clearing
  ;; the Reframe subscription cache.
  (rf/clear-subscription-cache!)
  (run :init-db false))

(comment "To avoid warnings, we refer to these functions here" on-jsload clear-cache-and-render!)

