(ns inventory.routes.product
  (:use compojure.core)
  (:use inventory.models.product))

(defroutes product-routes
  (context "/products" []
    (GET "/" [] {:body (get-products)})
    (POST "/" {product :body-params} {:body (create-product product)})
    (context "/:id" [id]
      (GET "/" [] {:body (retrieve-product id)})
      (POST "/" {product :body-params} {:body (update-product id product)})
      (DELETE "/" [] (delete-product id)))))
