(ns create-cljs-app.utils-spec
  (:require
    [create-cljs-app.utils :refer [has-binary-on-PATH?]]
    [cljs.test :refer-macros [deftest is]]))

(deftest binary-detection
  (is (= (has-binary-on-PATH? "node") true))
  (is (= (has-binary-on-PATH? "not-node") false)))
