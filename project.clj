(defproject clockets "0.1.0-SNAPSHOT"
    :description "A basic Clojure application using web-sockets and Clojurescript"
    :url "http://example.com/FIXME"
    :license {:name "Eclipse Public License"
              :url  "http://www.eclipse.org/legal/epl-v10.html"}

    ;; Add the source paths for clj & cljs
    ;:source-paths ["src/clj"]

    ;; Beware - if you change the version numbers backwards you may get some irritating dependency issues
    :dependencies [[org.clojure/clojure "1.7.0-RC1"]
                   [org.clojure/clojurescript "0.0-3308"]
                   [compojure "1.3.4"]
                   [ring/ring-core "1.4.0-RC1"]
                   [http-kit "2.1.18"]
                   [com.taoensso/sente "1.4.1"]
                   [ring/ring-jetty-adapter "1.4.0-RC1"]]

    ;; Add the leiningen plugin build tool for cljs
    :plugins [[lein-cljsbuild "1.0.6"]
              [lein-ring "0.9.4"]]

    :ring {:handler clj.clockets.core/handler}

    ;; This bit is the config for the cljs build tool
    :cljsbuild {:builds
              [{;; CLJS source code path
                :source-paths ["src/cljs"]

                ;; Google Closure (CLS) options configuration
                :compiler {;; CLS generated JS script filename
                           :output-to "resources/public/js/clockets.js"

                           ;; minimal JS optimization directive
                           :optimizations :whitespace

                           ;; generated JS code prettyfication
                           :pretty-print true}}]}

    :main clj.clockets.core
    :target-path "target/%s"
    ;:profiles {:uberjar {:aot :all}})
)
