(ns app.cards.test-runner
  {:dev/always true}
  (:require [shadow.test.env :as env]
            [cljs.test :as ct]
            [shadow.test :as st]
            ["jsdom-global" :as jsdom-global]
            ; Import the devcards-runner ns to get all the tests imported by it.
            [app.cards.devcards-runner]))

(defmethod ct/report [::ct/default :end-run-tests] [m]
  (if (ct/successful? m)
    (js/process.exit 0)
    (js/process.exit 1)))

(defn run-tests []
  (-> (env/get-test-data)
      (env/reset-test-data!))
  (st/run-all-tests))

(defn main
  "Run tests in a jsom environment."
  []
  ; Set jsdom to mock a dom environment.
  (jsdom-global)
  ; Run the tests
  (run-tests))