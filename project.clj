(defproject simple-calc-service "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [metosin/muuntaja "0.6.7"]
                 [metosin/reitit "0.5.11"]
                 [metosin/ring-http-response "0.9.1"]
                 [ring "1.8.2"]
                 [instaparse "1.4.10"]
                 [rhizome "0.2.9"]
                 [org.clojure/core.match "1.0.0"]]
  :repl-options {:init-ns simple-calc-service.core}
  :main simple-calc-service.core)
