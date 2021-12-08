(ns leiningen.new.shadowy
  (:require
    [clojure.string :as string]
    [leiningen.new.templates :as tmpl]
    [leiningen.core.main :as main]))

(def render (tmpl/renderer "shadowy"))

(defn name-to-ns
  "Create a namespace from a name"
  [n]
  (let [parts (string/split n #"[\-_\s]+")
        parts' (map string/lower-case parts)
        ns-name (string/join "." parts')]
    ns-name))

(defn path-to-ns
  "Create a namespace from a path"
  [path]
  (let [parts (string/split path #"/")
        ns-name (string/join "." parts)]
    ns-name))

(defn shadowy
  "Will generate a spankin' new React app, using Shadow"
  [name]
  (let [path (tmpl/name-to-path name)
        data {:name name
              :sanitized path
              :ns (path-to-ns path)}]
    (main/info "Generating fresh 'lein new' davber/shadowy project.")
    (tmpl/->files data
                  ["package.json" (render "package.json" data)]
                  ["project.clj" (render "project.clj" data)]
                  ["src/{{sanitized}}/server.clj" (render "server.clj" data)]
                  ["resources/public/index.html" (render "index.html" data)])))
