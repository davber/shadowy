(ns leiningen.new.shadowy
  (:require [leiningen.new.templates :as tmpl]
            [leiningen.core.main :as main]))

(def render (tmpl/renderer "shadowy"))

(defn shadowy
  "FIXME: write documentation"
  [name]
  (let [data {:name name
              :sanitized (tmpl/name-to-path name)}]
    (main/info "Generating fresh 'lein new' davber/shadowy project.")
    (tmpl/->files data
                  ["src/{{sanitized}}/foo.clj" (render "foo.clj" data)])))
