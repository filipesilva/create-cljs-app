(ns create-cljs-app.template
  (:require
    [create-cljs-app.utils :refer [get-commands]]
    [clojure.string :refer [replace]]
    ["path" :refer [dirname join]]
    ["fs" :refer
     [copyFileSync mkdirSync readdirSync readFileSync statSync writeFileSync]]))

(def template-dir
  "Path there template files are."
  (join js/__dirname ".." "template"))

(def template-ignores
  "UNIX-style paths from the template dir to ignore. Mostly leftovers from template development."
  #{".shadow-cljs" "node_modules" "out" "yarn.lock" "public/js"})

(defn get-template-values
  "List of string replacements to perform in files while copying.
Will likely need to be replaced with a proper templating library."
  [name commands]
  {"package.json" [{:from "__NAME__", :to name}],
   "public/index.html" [{:from "__NAME__", :to name}],
   "src/app/core.cljs" [{:from "__NAME__", :to name}],
   "README.md"
     [{:from "__START__", :to (:start commands)}
      {:from "__TEST__", :to (:test commands)}
      {:from "__TEST:ONCE__", :to (:test:once commands)}
      {:from "__E2E__", :to (:e2e commands)}
      {:from "__LINT__", :to (:lint commands)}
      {:from "__FORMAT__", :to (:format commands)}
      {:from "__BUILD__", :to (:build commands)}]})

(defn list-files
  "Recursively lists all files in a directory, ignoring some paths along the way."
  [from ignore]
  (defn join-partial
    [partial fragment]
    (if (= partial "")
      fragment
      ; Always join partials with UNIX separators to ensure matches with
      ; ignores.
      (str partial "/" fragment)))
  (defn list-files-helper
    [partial]
    (mapcat
      #(let [curr-partial (join-partial partial %)]
         (if (.isFile (statSync (join from curr-partial)))
           [curr-partial]
           (list-files-helper curr-partial)))
      (filter #(not (contains? ignore (join-partial partial %)))
        (set (readdirSync (join from partial))))))
  (list-files-helper ""))

(defn copy-template
  "Copy a file while using it as a template with replacements."
  [from-abs to-abs values]
  (writeFileSync
    to-abs
    (reduce #(replace %1 (:from %2) (:to %2))
      (readFileSync from-abs "utf-8")
      values)))

(defn copy-files
  "Copy files from one directory to another, preserving folder structure."
  [files from to template-values]
  (defn copy-file
    [path]
    (let [from-abs (join from path)
          to-abs (join to path)]
      (mkdirSync (dirname to-abs) #js {:recursive true})
      (if (contains? template-values path)
        (copy-template from-abs to-abs (get template-values path))
        (copyFileSync from-abs to-abs))))
  (doall (map #(copy-file %) files)))

(defn use-template
  "Create an app from a template into."
  [app-path name commands]
  (copy-files
    (list-files template-dir template-ignores)
    template-dir
    app-path
    (get-template-values name commands)))