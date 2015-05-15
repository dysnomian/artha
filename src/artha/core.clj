(ns artha.core
  (:require [clojure.string :as str]
            [clojure.java.io :as io]
            [artha.luhn :as luhn]))

(defn -main [filename]
  (with-open [input-file (reader filename)]
    (map (str/split-lines input-file) (operation))))

(defn split-file [& filename]
  (map filename (str/split-lines (slurp filename))))

(defn op-type [input-line]
  (first input-line))

(defn amount [input-line]
  (last input-line))

(defn account [input-line]
  (second input-line))

(defn card-number [input-line]
  (if (= (op-type input-line) "Add")
    (nth input-line 2)))

(defrecord Operation [op-type account card-number type])
(defrecord Card [account card-number valid])
(defprotocol Validate-Card (luhn/validate [_]))

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

(defmulti apply-operation
  "Calculate the area of a shape"
  :type)

(defmethod apply-operation :add [transaction register]
  (if (luhn.valid (:card-number transaction))
    (assoc register {:account (:account transaction) :balance (:amount transaction)))))

(defmethod apply-operation :credit [transaction register]
  (update-in (get register [:account ] {:balance (:amount transaction)})))

(defmethod apply-operation :charge [transaction register]
  (update-in (get register [:account ]{:account (:account transaction) :balance (:amount transaction)))
