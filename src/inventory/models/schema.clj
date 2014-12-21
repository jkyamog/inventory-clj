(ns inventory.models.schema
  (:require [montoux.gestalt :as gestalt]))

(def db-spec
  {:subprotocol "postgresql"
   :subname (gestalt/get :db-name)
   :user (gestalt/get :db-user)
   :password (gestalt/get :db-pass)})


