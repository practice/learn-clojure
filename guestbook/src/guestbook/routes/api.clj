(ns guestbook.routes.api
  (:require [compojure.core :refer :all]
            [liberator.core :refer [defresource resource request-method-in]]
            [cheshire.core :refer [generate-string]]
            [noir.session :as session]))

(defresource home
             :allowed-methods [:get]
             :method-allowed? (request-method-in :get)
             :handle-ok (fn [ctx] (generate-string {:code "xx" :message "Hello World"}))
             ; :etag "fixed-etag"
             :available-media-types ["application/json"]
             :content-type ["application/json; charset=UTF-8"])

(defroutes api-home-routes
           (ANY "/api" request home))