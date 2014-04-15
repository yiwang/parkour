(ns parkour.test-helpers
  (:require [pjstadig.scopes :as s]
            [parkour (conf :as conf)]))

(defn config
  "Configure `conf` (or fresh configuration if not provided) for local-mode test
execution."
  ([] (config (conf/ig)))
  ([conf]
     (doto conf
       (conf/local-mr!)
       (conf/local-fs!)
       (conf/assoc! #_conf
         "hadoop.security.authentication" "simple"))))

(defn config-fixture
  "A clojure.test fixture function for running tests under the local-mode test
configuration."
  [f] (s/with-resource-scope (conf/with-default (config) (f))))

(defmacro with-config
  "Execute `body` forms with the default configuration set to a local-mode test
configuration."
  [& body] `(config-fixture (^:once fn* [] ~@body)))
