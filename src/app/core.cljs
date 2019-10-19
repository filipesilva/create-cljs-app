(ns app.core
  (:require [reagent.core :as r]))

(defn app
  []
  [:div
    "cljs-app is running!"])

(defn ^:dev/after-load start
  []
  (r/render
   [app]
   (.getElementById js/document "app")))

(defn ^:export main
  []
  (start))
