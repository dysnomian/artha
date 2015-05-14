(ns artha.core
  (:gen-class))

(require '[clojure.string :as str])

(defn -main
  (println "Hello, World!"))

(defn split-file [filename]
  ((clojure.string/split-lines (slurp filename))))

(defn op-type [input-line]
  (first input-line))

(defn amount [input-line]
  (last input-line))

(defn account [input-line]
  (second input-line))

(defn card-number [input-line]
  (if
    (= (op-type input-line) "Add")
    (nth input-line 2)))

(defn operation [input-line]
  (hash-map
    :account (account input-line)
    :amount (amount input-line)
    :type (op-type input-line)
    :card-number (card-number input-line)))

(defn print-operation [operation]
  (println (str "Operation type: " (get operation :type)))
  (println (str "Account: " (get operation :account)))
  (println (str "Amount: " (get operation :amount)))
  (if (= (get operation :type) "Add")
    (println (str "Credit card number: " (get operation :card-number)))))

(defn add-account [operation accounts]
  (assoc accounts (get operation :account) (get operation :amount)))

(def example-accounts ["Soos" 0])
(def example-input-line ["Add" "Soos" "4111111111111111" "$25"])
(def example-operation (operation example-input-line))
