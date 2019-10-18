(ns e2e.test
  (:require [cljs.test :refer-macros [deftest is async]]
            [promesa.core :refer [then finally]]
            ["taiko" :refer [openBrowser goto closeBrowser text]]))

; Pass to openBrowser to see the browser performing actions.
(def taiko-debug-opts #js {:headless true :observe false}) 

(deftest app-works
  (let [test-string "sample-app is running!"]
    (async done
           (-> (openBrowser)
             (then #(goto "http://localhost:5000"))
             (then #(.exists (text test-string)))
             (then #(is % (str "Text '" test-string "' should exist in page")))
             (finally #(closeBrowser))
             (then #(done))))))
