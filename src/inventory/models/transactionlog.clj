(ns inventory.models.transactionlog
  (:use korma.core
        [korma.db :only (defdb)])
  (:require [inventory.models.schema :as schema]))

(defmulti transaction-type :type)
(defmethod transaction-type "create-product" [product]
  (dissoc product :id))

(defentity transactionlogs)

(defn insert-transactionlogs [transaction]
  (insert transactionlogs (values (transaction-type transaction))))