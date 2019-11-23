(ns create-cljs-app.utils
  (:require ["chalk" :refer [red]]
            ["semver" :refer [coerce satisfies]]
            ["shelljs" :refer [which]]))

(defn exit-with-reason
  "Show a message in red in the error stream, then exit with code 1."
  [message]
  (.error js/console (red message))
  (.exit js/process 1))

(defn get-commands
  [use-yarn]
  (if use-yarn
    {:start "yarn start"
     :cards "yarn cards"
     :server "yarn server"
     :build "yarn build"
     :test "yarn test"
     :test:watch "yarn test:watch"
     :e2e "yarn e2e"
     :lint "yarn lint"
     :report "yarn report"
     :format "yarn format"}
    {:start "npm start"
     :cards "npm run cards"
     :server "npm run server"
     :build "npm run build"
     :test "npm test"
     :test:watch "npm run test:watch"
     :e2e "npm run e2e"
     :lint "npm run lint"
     :report "npm run report"
     :format "npm run format"}))

(defn has-binary-on-PATH? [name] (boolean (which name)))

(defn should-use-yarn? [] (has-binary-on-PATH? "yarn"))

(defn should-use-git? [] (has-binary-on-PATH? "git"))

(defn has-java? [] (has-binary-on-PATH? "java"))

(defn is-supported-node? [version] (satisfies (coerce version) ">=10.12.0"))
