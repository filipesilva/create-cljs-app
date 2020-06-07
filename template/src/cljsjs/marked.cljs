(ns cljsjs.marked
  (:require ["marked" :as marked]))

(js/goog.exportSymbol "marked" marked)
(js/goog.exportSymbol "DevcardsMarked" marked)