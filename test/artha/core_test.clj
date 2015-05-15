(ns artha.core-test
  (:require [clojure.test :refer :all]
            [artha.core :refer :all]))

(deftest test-account
  (let [input-line  ["Add" "Soos" "4111111111111111" "$25"]]
    (= (account input-line "Soos"))))
