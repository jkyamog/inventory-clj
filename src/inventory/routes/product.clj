(ns inventory.routes.product
  (:use compojure.core)
  (:use inventory.models.product)
  (:require [inventory.views.layout :as layout]))

(defroutes product-routes
  (context "/products" []
    (GET "/" [] {:body (get-products)})
    (GET "/index.html" [] (layout/render "product.html"))
    (POST "/" {product :body-params} {:body (create-product product)})
    (context "/:id" [id]
      (GET "/" [] {:body (retrieve-product id)})
      (POST "/" {product :body-params} {:body (update-product id product)})
      (DELETE "/" [] (delete-product id)))))