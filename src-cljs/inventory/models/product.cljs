(ns inventory.models.product
  (:require [goog.object :as o] inventory.app)
  (:use-macros [purnam.core :only [obj arr ! def.n]]
               [gyr.core :only [def.factory def.controller]]))

(def.factory app.Product [$resource]
  ($resource "/products/:id" (obj :id "@id")))

(def.controller app.ProductController [$scope Product]
  (! $scope.products (. Product query))
  (! $scope.updateProduct (fn [product]
                            (. product $save)))
  (! $scope.deleteProduct (fn [product]
                            (. product $remove
                              (fn []
                                ($scope.products.splice ($scope.products.indexOf product) 1))))))

(def.controller app.ProductCreateController [$scope Product]
  (! $scope.save (fn []
                   (let [product (new Product (obj :name $scope.product.name
                                                :description $scope.product.description
                                                :quantity (js/parseInt $scope.product.quantity)))]
                     (. product $save (fn [data]
                                        ($scope.products.push data)
                                        (! $scope.product nil)))))))



