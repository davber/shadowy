(defproject {{name}} "0.1.0"
            :description "FIXME: add description"
            :url "http://www.mycoolapp.com"
            :license {:name "Whatever-you-want"
                      :url "Link to one of those 'free' licenses, or a commercial one"}
            :sources ["src"]
            :main "{{ns}}.server"
            :dependencies [[org.clojure/clojure "1.10.3"]
                           [org.clojure/tools.cli "1.0.206"]
                           [org.clojure/core.incubator "0.1.4"]
                           [org.clojure/core.match "1.0.0"]
                           [org.clojure/algo.generic "0.1.3"]
                           [org.clojure/core.async "1.4.627"]
                           [org.clojure/java.classpath "1.0.0"]
                           [org.clojure/clojurescript "1.10.893"
                            :exclusions [com.google.javascript/closure-compiler-unshaded
                                         org.clojure/google-closure-library
                                         org.clojure/google-closure-library-third-party]]
                           [io.aviso/pretty "1.1" :exclusions [org.clojure/clojure]]
                           [tick "0.5.0-RC5" :exclusions [cljsjs/js-joda]]
                           [cljsjs/js-joda "1.12.0-0"]
                           [cljs.java-time "0.1.19"]
                           [com.taoensso/encore "3.19.0"]
                           [com.taoensso/timbre "5.1.2" :exclusions [io.aviso/pretty com.taoensso/encore]]
                           [me.raynes/fs "1.4.6"]

                           ;; Jetty and Ring -- if server side is needed

                           [org.eclipse.jetty/jetty-servlets "11.0.6"]
                           [ring-server "0.5.0" :exclusions [org.clojure/core.incubator]]
                           [ring "1.9.4" :exclusions [org.clojure/java.classpath]]
                           [ring/ring-defaults "0.3.3"]
                           [ring/ring-json "0.5.1"]
                           [ring/ring-codec "1.2.0"]
                           [hiccup "2.0.0-alpha2"]

                           ;; Reframe
                           [reagent "1.1.0"]
                           [reagent-utils "0.3.4"]
                           [re-frame "1.2.0"]
                           [day8.re-frame/tracing "0.6.2"]

                           ;; Shadow

                           [thheller/shadow-cljs "2.16.6"]
                           [re-com "2.13.2"]

                           [com.cemerick/url "0.1.1"]
                           [prone "2021-04-23"]
                           [org.clojure/test.check "1.1.0"]
                           [org.clojure/tools.nrepl "0.2.13"]
                           [nrepl/lein-nrepl "0.3.2"]
                           [midje "1.9.9" :exclusions [io.aviso/pretty org.clojure/java.classpath]]
                           [cheshire "5.10.1"]
                           [rewrite-clj "1.0.699-alpha"]
                           [rewrite-cljs "0.4.5"]
                           [binaryage/devtools "1.0.4"]
                           [ring/ring-mock "0.4.0"]
                           [ring/ring-devel "1.9.4" :exclusions [org.clojure/java.classpath]]

                           ;; Remongo -- if needing access to MongoDB

                           [org.clojars.davber/remongo "0.2.4"]

                           ;; Nathan's Specter
                           [com.rpl/specter "1.1.3"]]

            :plugins [[lein-less "1.7.5"]
                      [lein-shell "0.5.0"]]

            :min-lein-version "2.9.0"

            :jvm-opts ["-Xmx1G"]

            :source-paths ["src"]

            :test-paths   ["test/js"]

            :clean-targets ^{:protect false} ["resources/public/js/compiled" "target"
                                              "test/js"]

            :less {:source-paths ["less"]
                   :target-path  "resources/public/css"}

            :shell {:commands {"karma" {:windows         ["cmd" "/c" "karma"]
                                        :default-command "karma"}
                               "open"  {:windows         ["cmd" "/c" "start"]
                                        :macosx          "open"
                                        :linux           "xdg-open"}}}

            :aliases {"dev"          ["do"
                                      ["shell" "echo" "\"DEPRECATED: Please use lein watch instead.\""]
                                      ["watch"]]
                      "watch"        ["with-profile" "dev" "do"
                                      ["shadow" "watch" "app" "browser-test" "karma-test"]]

                      "prod"         ["do"
                                      ["shell" "echo" "\"DEPRECATED: Please use lein release instead.\""]
                                      ["release"]]

                      "release"      ["with-profile" "prod" "do"
                                      ["shadow" "release" "app"]]

                      "build-report" ["with-profile" "prod" "do"
                                      ["shadow" "run" "shadow.cljs.build-report" "app" "target/build-report.html"]
                                      ["shell" "open" "target/build-report.html"]]

                      "karma"        ["do"
                                      ["shell" "echo" "\"DEPRECATED: Please use lein ci instead.\""]
                                      ["ci"]]
                      "ci"           ["with-profile" "prod" "do"
                                      ["shadow" "compile" "karma-test"]
                                      ["shell" "karma" "start" "--single-run" "--reporters" "junit,dots"]]}

            :profiles
            {:dev
             {:dependencies [[binaryage/devtools "1.0.4"]
                             [day8.re-frame/re-frame-10x "1.2.0"]
                             [re-frisk "1.5.2"]]
              :source-paths []}

             :prod {}}

            :prep-tasks [["less" "once"]])
