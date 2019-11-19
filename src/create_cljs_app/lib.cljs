(ns create-cljs-app.lib
  (:require [create-cljs-app.template :refer [use-template]]
            [create-cljs-app.utils :refer
             [exit-with-reason get-commands has-java? should-use-git? should-use-yarn?
              is-supported-node?]]
            [create-cljs-app.messages :refer
             [begin-msg done-msg init-git-msg install-packages-msg java-warning
              node-error]]
            ["path" :refer [basename join]]
            ["fs" :refer [existsSync]]
            ["shelljs" :refer [exec rm]]))


(defn create
  "Create an app from the template files on the given path."
  [cwd path]
  ; Bail early if the node version is unsupported.
  (when (not (is-supported-node? (.-version js/process)))
    (node-error)
    (.exit js/process 1))
  (let [abs-path (join cwd path)
        name (basename abs-path)
        use-yarn (should-use-yarn?)
        use-git (should-use-git?)
        commands (get-commands use-yarn)]
    (cond
      (= path "") (exit-with-reason "You must provide a name for your app.")
      (existsSync abs-path) (exit-with-reason "A folder with the same name already exists.")
      :else (do
              (begin-msg abs-path)
              (use-template abs-path name commands)
              (.chdir js/process path)
              (install-packages-msg)
              (exec (if use-yarn "yarn" "npm install"))
              (when use-git
                (let [exec-options #js {:silent true :fatal true}]
                  (try
                    (exec "git init" exec-options)
                    (exec "git add -A" exec-options)
                    (exec
                      "git commit -m \"Initial commit from Create CLJS App\""
                      exec-options)
                    (init-git-msg)
                    ; Catch and remove the .git directory to not leave it
                    ; half-done.
                    (catch js/Object _e (rm "-rf" ".git")))))
              (when (not (has-java?)) (java-warning))
              (done-msg name path abs-path commands)))))

(def exports #js {:create create})
