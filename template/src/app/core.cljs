(ns app.core
  (:require [reagent.core :as r]
            [app.hello :refer [hello]]))

(defn ^:dev/after-load start []
  (r/render [hello] (.getElementById js/document "app")))

(defn ^:export main []
  (start))
