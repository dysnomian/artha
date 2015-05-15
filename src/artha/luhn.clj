(ns artha.luhn)

(defn luhn-checksum
  [digits]
  (let [[even-nums odd-nums]
        (->> digits
             (map #(list %1 %2) (iterate inc 0))
             (group-by #(even? (first %)))
             vals)]
    (+ (apply + (map #(let [x (* (nth % 1) 2)] (if (> x 9) (- x 9) x)) even-nums))
       (apply + (map #(nth % 1) odd-nums)))))

(defn valid? [checksum]
  (= (rem checksum 10) 0))

(defn digits [number]
  (map #(Character/digit % 10) (seq number)))

(defn even-digits [sequence]
  (flatten (repeat [1 2])))

(def factors (flatten (repeat [1 2])))

(defn double-every-other-number [sequence]
  (map * sequence factors))


