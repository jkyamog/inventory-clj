(ns inventory.models.product
  (:use korma.core
        [korma.db :only [defdb transaction]])
  (:require [inventory.models.schema :as schema]
            [inventory.models.transactionlog :as tlog]))

(defdb db schema/db-spec)

(defentity products)

(defn get-products []
  (select products))

(defn create-product [product]
  (transaction
      (tlog/insert-transactionlogs (assoc product :type "create-product"))
      (insert products (values product))))

(defn retrieve-product [id]
  (select products
    (where {:id (Integer/parseInt id)})))

(defn update-product [id product]
  (update products
    (set-fields product)
    (where {:id (Integer/parseInt id)})))

(defn delete-product [id]
  (delete products
    (where {:id (Integer/parseInt id)})))