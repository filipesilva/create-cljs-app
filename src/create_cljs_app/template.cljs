(ns create-cljs-app.template
  (:require [clojure.string :refer [replace]]
            ["path" :refer [dirname join]]
            ["fs" :refer
             [copyFileSync mkdirSync readdirSync readFileSync statSync writeFileSync]]))

(def template-dir
  "Path there template files are."
  (join js/__dirname ".." "template"))

(def template-ignores
  "UNIX-style paths from the template dir to ignore. Mostly leftovers from template development."
  #{".shadow-cljs" "node_modules" "out" "yarn.lock" "public/js"})

(defn get-template-values-map
  "List of string replacements to perform in files while copying.
Will likely need to be replaced with a proper templating library."
  [name commands]
  {"package.json" [{:from "__NAME__" :to name}]
   "public/index.html" [{:from "__NAME__" :to name}]
   "src/app/core.cljs" [{:from "__NAME__" :to name}]
   "README.md" [{:from "__START__" :to (:start commands)}
                {:from "__TEST__" :to (:test commands)}
                {:from "__TEST:ONCE__" :to (:test:once commands)}
                {:from "__E2E__" :to (:e2e commands)}
                {:from "__LINT__" :to (:lint commands)}
                {:from "__FORMAT__" :to (:format commands)}
                {:from "__BUILD__" :to (:build commands)}]})

(defn- join-partial
  [partial fragment]
  (if (= partial "")
    fragment
    ; Always join partials with UNIX separators to ensure matches with
    ; ignores.
    (str partial "/" fragment)))

(defn- list-files-helper
  [from ignore partial]
  (mapcat
    #(let [curr-partial (join-partial partial %)]
      (if (.isFile (statSync (join from curr-partial)))
        [curr-partial]
        (list-files-helper from ignore curr-partial)))
    (filter #(not (contains? ignore (join-partial partial %)))
            (set (readdirSync (join from partial))))))

(defn list-files
  "Recursively lists all files in a directory, ignoring some paths along the way."
  [from ignore]
  (list-files-helper from ignore ""))

(defn copy-template
  "Copy a file while using it as a template with replacements."
  [from-abs to-abs template-values]
  (writeFileSync to-abs (reduce #(replace %1 (:from %2) (:to %2))
                                (readFileSync from-abs "utf-8")
                                template-values)))

(defn- copy-file
  [from to path template-values]
  (let [from-abs (join from path)
        to-abs (join to path)]
    (mkdirSync (dirname to-abs) #js {:recursive true})
    (if template-values
      (copy-template from-abs to-abs template-values)
      (copyFileSync from-abs to-abs))))

(defn copy-files
  "Copy files from one directory to another, preserving folder structure."
  [files from to get-template-values-map]
  (doall (map #(copy-file from to % (get get-template-values-map %)) files)))

(defn use-template
  "Create an app from a template into."
  [app-path name commands]
  (copy-files (list-files template-dir template-ignores)
              template-dir
              app-path
              (get-template-values-map name commands)))