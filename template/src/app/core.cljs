(ns app.core
  "This namespace contains your application and is the entrypoint for 'yarn start'."
  (:require [reagent.dom :as rd]
            [app.hello :refer [hello]]))

(defn ^:dev/after-load render
  "Render the toplevel component for this app."
  []
  (rd/render [hello] (.getElementById js/document "app")))

(defn ^:export main
  "Run application startup logic."
  []
  (render))
