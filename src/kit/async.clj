(ns kit.async)

(defmacro <? [ch]
  `(let [val# (cljs.core.async/<! ~ch)]
     (cond
       (or (instance? js/Error val#)
           (instance? cljs.core.ExceptionInfo val#))
         (throw val#)
       (vector? val#)
         ;; this works around a cljs bug, which causes
         ;; the and operator not to short circuit
         (if (= (first val#) :error)
           (throw (ex-info nil {:error val#}))
           val#)
       :else val#)))

(defmacro go-try [& forms]
  `(go (try ~@forms)))
