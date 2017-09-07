(ns personal-site.core
    (:require [reagent.core :as reagent :refer [atom]]
              [secretary.core :as secretary :include-macros true]
              [accountant.core :as accountant]))

;; -------------------------
;; Views

(defn section [content style]
  [:div.section {:style style} content])

(defn home-page []
  [:div.container
   [:div.header
    [:div.brand "Andrew Beach"]
    [:div.brand-logo "AB"]
    [:div.navbar
     [:div.navlink.active "Intro"]
     [:div.navlink "Interests"]
     [:div.navlink "Projects"]]]
   [:div.sections
    [section "Section 1" {:background-color "rgb(66, 185, 244)"}]
    [section "Section 2" {:background-color "white"}]
    [section "Section 3" {:background-color "rgb(87, 249, 114)"}]]])

(defn about-page []
  [:div [:h2 "About personal-site"]
   [:div [:a {:href "/"} "go to the home page"]]])

;; -------------------------
;; Routes

(def page (atom #'home-page))

(defn current-page []
  [:div [@page]])

(secretary/defroute "/" []
  (reset! page #'home-page))

(secretary/defroute "/about" []
  (reset! page #'about-page))

;; -------------------------
;; Initialize app

(defn mount-root []
  (reagent/render [current-page] (.getElementById js/document "app")))

(defn init! []
  (accountant/configure-navigation!
    {:nav-handler
     (fn [path]
       (secretary/dispatch! path))
     :path-exists?
     (fn [path]
       (secretary/locate-route path))})
  (accountant/dispatch-current!)
  (mount-root))
