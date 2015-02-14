(defproject vupt/kit.async "0.1.0-SNAPSHOT"
  
  :dependencies
    [[org.clojure/clojure "1.6.0"]
     [org.clojure/clojurescript "0.0-2665"]
     [org.clojure/core.async "0.1.338.0-5c5012-alpha"]
     [com.cemerick/piggieback "0.1.5"]
     [mocha-latte "0.1.2"]
     [chai-latte  "0.2.0"]]
  
  :node-dependencies 
    [[source-map-support "0.2.8"]
     [mocha "2.1.0"]
     [chai "1.10.0"]]
  
  :plugins
    [[lein-cljsbuild "1.0.4"]
     [lein-npm "0.4.0"]]
  
  :repl-options {:nrepl-middleware [cemerick.piggieback/wrap-cljs-repl]}

  :source-paths ["src" "target/classes"]

  :cljsbuild {
    :builds
      [{:id "dev"
        :source-paths ["src"]
        :compiler {
          :output-dir     "target"
          :output-to      "kit.async.repl.js"
          :optimizations  :none
          :cache-analysis true
          :source-map     true}}
       {:id "test"
        :source-paths ["src" "test"]
        :compiler {
          :output-dir     "target/test"
          :output-to      "target/test/test.js"
          :optimizations  :simple
          :cache-analysis true
          :source-map     "target/test/test.map"
          :target         :nodejs}}]})
