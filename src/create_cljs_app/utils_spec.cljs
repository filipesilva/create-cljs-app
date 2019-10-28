(ns create-cljs-app.utils-spec
  (:require
    [create-cljs-app.utils :refer [has-binary-on-PATH? is-supported-node?]]
    [cljs.test :refer-macros [deftest is]]))

(deftest binary-detection
  (is (= (has-binary-on-PATH? "node") true))
  (is (= (has-binary-on-PATH? "not-node") false)))

(deftest supported-node
  (is (= (is-supported-node? "v6.1.1") false))
  (is (= (is-supported-node? "v8.1.1") false))
  (is (= (is-supported-node? "v10.1.1") false))
  (is (= (is-supported-node? "v10.12.1") true))
  (is (= (is-supported-node? "v12.1.1") true))
  (is (= (is-supported-node? (.-version js/process)) true)))
