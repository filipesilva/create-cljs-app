(ns app.core
  "This namespace contains your application and is the entrypoint for 'yarn start'."
  (:require [reagent.core :as r]
            [app.hello :refer [hello]]))

(defn ^:dev/after-load render
  "Render the toplevel component for this app."
  []
  (r/render [hello] (.getElementById js/document "app")))

(defn ^:export main
  "Run application startup logic."
  []
  (render))
