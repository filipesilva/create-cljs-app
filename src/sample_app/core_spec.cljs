(ns sample-app.core-spec
  (:require [sample-app.core :refer [app]]
            [clojure.string :refer [includes?]]
            [cljs.test :refer-macros [deftest is]]))

(deftest app-has-running
  (is (some #(when (string? %) (includes? % "running")) (app))))



