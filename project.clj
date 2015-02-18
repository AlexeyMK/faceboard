(defproject faceboard "0.1.0-SNAPSHOT"
  :description "FIXME: write this!"
  :url "http://example.com/FIXME"

  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.clojure/clojurescript "0.0-2850"]
                 [org.clojure/core.async "0.1.346.0-17112a-alpha"]
                 [org.omcljs/om "0.8.8"]
                 [prismatic/om-tools "0.3.10"]
                 [com.binaryage/devtools "0.0-SNAPSHOT"]
                 [figwheel "0.2.3-SNAPSHOT"] ]

  :plugins [[lein-cljsbuild "1.0.4"]
            [lein-figwheel "0.2.3-SNAPSHOT"]]

  :source-paths ["src" "target/classes"]

  :clean-targets ["resources/public/_generated"]

  :figwheel {
             :http-server-root "public" ;; this will be in resources/
             :server-port 3449
             :css-dirs ["resources/public/css"]
             ;:ring-handler example.server/handler
             ; :open-file-command "myfile-opener"
             ;; :repl false
             ;; :server-logfile "tmp/logs/figwheel-logfile.log"
             }

  :cljsbuild {
    :builds [{:id "faceboard"
              :source-paths ["src" "checkouts/cljs-devtools/src"]
              :compiler {
                :output-to "resources/public/_generated/faceboard.js"
                :output-dir "resources/public/_generated"
                :optimizations :none
                :cache-analysis true
                :source-map true}}]})
