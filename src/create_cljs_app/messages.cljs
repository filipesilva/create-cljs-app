(ns create-cljs-app.messages
  (:require ["chalk" :refer [blue green red yellow]]))

(defn begin-msg [abs-path]
  (.log js/console (str "\nCreating a new CLJS app in " (green abs-path) ".")))

(defn install-packages-msg []
  (.log js/console "\nInstalling packages. This might take a couple of minutes."))

(defn init-git-msg []
  (.log js/console "\nInitialized a git repository."))

(defn done-msg
  [name path abs-path commands]
  (.log
    js/console
    (str
      "\nSuccess! Created " name " at " abs-path "
Inside that directory, you can run several commands:

  " (blue (:start commands)) "
    Starts the development server.

  " (blue (:build commands)) "
    Bundles the app into static files for production.

  " (blue (:test commands)) "
    Starts the test runner.

We suggest that you begin by typing:

  " (blue (str "cd " path)) "
  " (blue (:start commands)) "

Happy hacking!
")))

(defn java-warning []
  (.log js/console (yellow
                     (str
                       "\nWARNING: Java is needed to build."
                       " Download the HotSpot 8+ version from https://adoptopenjdk.net."))))


(defn node-error []
  (.error js/console (red
                       (str
                         "\nERROR: NodeJS >= 10.12 is needed to run create-cljs-app."
                         " Download a recent NodeJS version from https://nodejs.org/en/download/"))))
