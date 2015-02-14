(ns kit.test.async
  (:require
    [cljs.core.async :refer (<!)]
    [kit.async :refer (lift)]
    [latte.chai :refer (expect)])
  (:require-macros
    [cljs.core.async.macros :refer (go)]
    [latte.core :refer (describe it)]))

(describe "lift"
  
  (it "converts a callback into a channel" [done]

    (go
      (let [ch (lift (fn [x f] (f x)) 1)]
        (expect (<! ch) :to.equal 1))
      (done))))
