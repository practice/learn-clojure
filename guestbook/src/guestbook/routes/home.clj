(ns guestbook.routes.home
  (:require [compojure.core :refer :all]
            [guestbook.views.layout :as layout]))

(defn home []
  (layout/common
    [:h1 "Hello Clojure ! It's fun !!"]))

(defroutes home-routes
  (GET "/" [] (home)))
