(ns create-cljs-app.utils
  (:require
    ["chalk" :refer [red]]
    ["shelljs" :refer [which]]))

(defn exit-with-reason
  "Show a message in red in the error stream, then exit with code 1."
  [message]
  (.error js/console (red message))
  (.exit js/process 1))

(defn get-commands
  [use-yarn]
  (if use-yarn
    {:start "yarn start",
     :build "yarn build",
     :test "yarn test",
     :test:once "yarn test:once",
     :e2e "yarn e2e",
     :lint "yarn lint",
     :format "yarn format"}
    {:start "npm start",
     :build "npm run build",
     :test "npm test",
     :test:once "npm run test:once",
     :e2e "npm run e2e",
     :lint "npm run lint",
     :format "npm run format"}))

(defn has-binary-on-PATH? [name] (boolean (which name)))

(defn should-use-yarn? [] (has-binary-on-PATH? "yarn"))

(defn should-use-git? [] (has-binary-on-PATH? "git"))

(defn has-java? [] (has-binary-on-PATH? "java"))
