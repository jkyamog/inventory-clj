(ns inventory.test.inventory.test.models.product
  (:use inventory.models.product
        clojure.test
        korma.core [korma.db :only (defdb)]))

(defentity transactionlogs)

(deftest product
  (testing "should create a new product")
    (let [new-product (create-product {:name "test product", :description "test description" :quantity 10})]
      (is (= (dissoc new-product :id) {:name "test product", :description "test description" :quantity 10})))


  (testing "should insert transaction logs when creating a new product")
  (let [new-product (create-product {:name "test product", :description "test description" :quantity 10})
        id {:id new-product}
        transactionlog (select transactionlogs (where {:id id}))]
    (is (= transaction log {:name "test product", :description "test description" :quantity 10})))

)


