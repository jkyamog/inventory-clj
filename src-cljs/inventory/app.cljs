(ns inventory.app
  (:use-macros [gyr.core :only [def.module def.config]]))

(def.module app [ngResource])

(def.config app [$interpolateProvider]
  (doto $interpolateProvider (.startSymbol "[["))
  (doto $interpolateProvider (.endSymbol "]]")))
