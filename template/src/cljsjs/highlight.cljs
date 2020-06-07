(ns cljsjs.highlight
  (:require ["highlight.js" :as highlight]))

(js/goog.exportSymbol "highlight" highlight)
(js/goog.exportSymbol "DevcardsSyntaxHighlighter" highlight)