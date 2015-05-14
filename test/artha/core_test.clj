(ns artha.core-test
  (:require [clojure.test :refer :all]
            [artha.core :refer :all]))

(deftest test-split-file
  (let [filename "foo.txt"
        output ["something" "else"]]
    (is (= (split-file filename) output))))
