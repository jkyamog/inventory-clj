(ns inventory.handler
  (:require [compojure.core :refer [defroutes]]
            [inventory.routes.home :refer [home-routes]]
            [inventory.middleware :as middleware]
            [noir.util.middleware :refer [app-handler]]
            [compojure.route :as route]
            [taoensso.timbre :as timbre]
            [taoensso.timbre.appenders.rotor :as rotor]
            [selmer.parser :as parser]
            [environ.core :refer [env]]
            [inventory.routes.cljsexample :refer [cljs-routes]]
            [inventory.routes.product :refer [product-routes]]))

(defroutes
  app-routes
  (route/resources "/")
  (route/not-found "Not Found"))

(defn init
  "init will be called once when
   app is deployed as a servlet on
   an app server such as Tomcat
   put any initialization code here"
  []
  (timbre/set-config!
    [:appenders :rotor]
    {:min-level :info,
     :enabled? true,
     :async? false,
     :max-message-per-msecs nil,
     :fn rotor/appender-fn})
  (timbre/set-config!
    [:shared-appender-config :rotor]
    {:path "inventory.log", :max-size (* 512 1024), :backlog 10})
  (if (env :dev) (parser/cache-off!))
  (timbre/info "inventory started successfully"))

(defn destroy
  "destroy will be called when your application
   shuts down, put any clean up code here"
  []
  (timbre/info "inventory is shutting down..."))

(def app
 (app-handler
   [cljs-routes home-routes product-routes app-routes]
   :middleware
   [middleware/template-error-page middleware/log-request]
   :access-rules
   []
   :formats
   [:json-kw :edn]))

