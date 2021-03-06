(ns guestbook.routes.home
  (:require [compojure.core :refer :all]
            [guestbook.views.layout :as layout]
            [hiccup.form :refer :all]
            [noir.session :as session]))

(defn show-guests []
  [:ul.guests
   (for [{:keys [message name timestamp]}
         [{:message "Howdy" :name "Bob" :timestamp nil}
          {:message "Hello" :name "Bob" :timestamp nil}]]
     [:li
      [:blockquote message]
      [:p "-" [:cite name]]
      [:time timestamp]]
     )])

(defn home [& [name message error]]
  (layout/common
    [:h1 "Guestbook " (session/get :user)]
    [:p "Welcome to my guestbook"]
    [:p error]
    (show-guests)
    [:hr]
    (form-to
      [:post "/"]
      [:p "Name: "]
      (text-field "name" name)
      [:p "Message: "]
      (text-area {:row 10 :cols 60} "message" message)
      [:br]
      (submit-button "comment"))))

(defn save-message [name message]
  (cond
    (empty? name) (home name message "Some dummy forgot to leave a name")
    (empty? message) (home name message "Don't you have something to say")
    :else
    (do
      (println name message)
      (home))))

(defroutes home-routes
  (GET "/" [] (home))
  (POST "/" [name message] (save-message name message)))
