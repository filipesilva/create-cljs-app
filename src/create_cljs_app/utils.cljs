(ns create-cljs-app.utils
  (:require [cljs.core.async :refer [go chan <! put!]]
            ["chalk" :refer [red]]
            ["semver" :refer [coerce satisfies]]
            ["ora" :as ora]
            ["shelljs" :refer [exec which]]))

(defn exit-with-reason
  "Show a message in red in the error stream, then exit with code 1."
  [message]
  (.error js/console (red message))
  (.exit js/process 1))

(defn get-commands
  [use-yarn]
  (if use-yarn
    {:install "yarn"
     :start "yarn start"
     :cards "yarn cards"
     :server "yarn server"
     :build "yarn build"
     :test "yarn test"
     :test:watch "yarn test:watch"
     :e2e "yarn e2e"
     :lint "yarn lint"
     :report "yarn report"
     :format "yarn format"}
    {:install "npm install"
     :start "npm start"
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

(defn silent-install
  "Asynchronously install npm packages while showing a spinner.
  Returns a channel that contains will receive the exit code.
  Must be asynchronous otherwise the spinner would not spin."
  [commands]
  (let [command (:install commands)
        spinner (.start (ora "Installing packages..."))
        c (chan)]
    ; Note: put! must be used instead of >! because go block analysis stops at
    ; function boundaries
    ; https://github.com/clojure/core.async/wiki/Go-Block-Best-Practices#unsupported-constructs-and-other-limitations-in-go-blocks
    (go (exec command #js {:silent true} #(put! c %))
        (let [code (<! c)]
          (if (= code 0)
            (.succeed spinner "Packages installed successfully.")
            (.fail spinner (str "Package install failed, please run '" command "' in the app folder.")))
          code))))
