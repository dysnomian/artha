(ns artha.luhn-test
  (:require [artha.luhn :refer :all]
            [clojure.test :refer :all]))

(deftest test-digits
  (let [number 12345
        sequence (\1 \2 \3 \4 \5)]
    (= (digits number) sequence)))
