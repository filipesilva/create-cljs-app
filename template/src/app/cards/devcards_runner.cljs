(ns app.cards.devcards-runner
  (:require [cljsjs.react]
            [cljsjs.react.dom]
            ; devcards needs cljsjs.react and cljsjs.react.dom to be imported
            ; separately for shadow-cljs to add shims.
            [devcards.core :refer [start-devcard-ui!]]
            ; Import all namespaces with cards here to load them.
            [app.hello-cards]))

(defn ^:export main
  "Start the devcards UI."
  []
  ; Add a special class to the body to signal we're in devcards mode.
  ; We want to mostly use the same styles as the app, but might need to make
  ; some exceptions.
  (js/document.body.classList.add "using-devcards")
  ; Start the devcards UI.
  (start-devcard-ui!))
