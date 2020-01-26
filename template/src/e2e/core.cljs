(ns e2e.core
  "This namespace contains your e2e tests and is the entrypoint for 'yarn e2e'."
  (:require [cljs.test :refer-macros [deftest is async use-fixtures]]
            ["http" :as http]
            ["serve-handler" :as serve-handler]
            ["taiko" :refer [openBrowser goto closeBrowser text diagnostics]]))

; Serve public/ on a static server.
(use-fixtures
  :once
  (let [server (.createServer http #(serve-handler %1 %2 #js {:public "public/"}))]
    {:before #(.listen server 5000)
     :after #(.close server)}))

; Change debug to true to see the browser performing actions.
(def debug false)
(def browser-opts (if debug
                    #js {:headless false :observe true}
                    #js {}))

(deftest app-works
  (let [test-string "is running!"]
    (async done
           (->
             (openBrowser browser-opts)
             (.then #(.logConsoleInfo diagnostics))
             (.then #(.on %1 "logEntry" (fn [log] (is (not (= (.-level log) "error"))
                                                      (str "Should not log errors: "
                                                           (js/JSON.stringify log))))))
             (.then #(goto "http://localhost:5000"))
             (.then #(.exists (text test-string)))
             (.then #(is % (str "Text '" test-string "' should exist in page")))
             (.catch #(is false "Should not have thrown errors"))
             (.finally #(closeBrowser))
             (.then #(done))))))
