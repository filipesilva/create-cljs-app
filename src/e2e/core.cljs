(ns e2e.core
  (:require [clojure.string :refer [includes?]]
            [cljs.test :refer-macros [deftest is use-fixtures testing]]
            ["fs" :refer [existsSync readFileSync]]
            ["shelljs" :refer [exec rm]]))

(defn silent-exec
  "Run a command silently. Flip silent to false to see output."
  [cmd]
  (exec cmd #js {:silent true}))

; Clean existing test app, if any.
(use-fixtures :once {:before #(rm "-rf" "test-app")})

(defn fileIncludes
  "Returns true if file includes a string match."
  [path match]
  (includes? (readFileSync path "utf-8") match))

(deftest lib-works
  (testing "Creates project"
    (let [create-result (silent-exec "node bin/create-cljs-app.js test-app")
          output (.-stdout create-result)]
      (is (= (.-code create-result) 0) "Should exit create command with code 0")
      (testing "Logs messages"
        (is (includes? output "Creating a new CLJS app"))
        (is (includes? output "Installing packages"))
        (is (includes? output "Initialized a git repository"))
        (is (includes? output "Success!"))
        (is (not (includes? output "Java is needed to build."))))
      (is (existsSync "./test-app") "Should create test-app folder")
      (.chdir js/process "test-app")
      (testing "Did not copy ignored template files"
        (is (not (existsSync "./.shadow-cljs")))
        (is (not (existsSync "./out")))
        (is (not (existsSync "./public/js"))))
      (testing "Used template values"
        (is (not (fileIncludes "./package.json" "__NAME__")))
        (is (not (fileIncludes "./public/index.html" "__NAME__")))
        (is (not (fileIncludes "./src/app/core.cljs" "__NAME__")))
        (is (not (fileIncludes "./README.md" "__START__")))
        (is (not (fileIncludes "./README.md" "__SERVER__")))
        (is (not (fileIncludes "./README.md" "__TEST__")))
        (is (not (fileIncludes "./README.md" "__TEST:ONCE__")))
        (is (not (fileIncludes "./README.md" "__E2E__")))
        (is (not (fileIncludes "./README.md" "__BUILD__")))
        (is (not (fileIncludes "./README.md" "__LINT__")))
        (is (not (fileIncludes "./README.md" "__FORMAT__"))))
      (testing "Commands"
        (is (= (.-code (silent-exec "yarn sc start")) 0) "Should start background server")
        (is (= (.-code (silent-exec "yarn test:once")) 0) "Should test")
        (is (= (.-code (silent-exec "yarn build")) 0) "Should build")
        (is (existsSync "./public/js/main.js"))
        "Should output public/js/main.js")
      (is (= (.-code (silent-exec "yarn e2e")) 0) "Should e2e")
      (is (= (.-code (silent-exec "yarn lint")) 0) "Should lint")
      (is (= (.-code (silent-exec "yarn format")) 0) "Should format"))))
