(ns simple-calc-service.core
  (:require
    [simple-calc-service.parser :as parser]
    [reitit.ring :as reitit]
    [muuntaja.core :as m]
    [ring.adapter.jetty :as jetty]
    [ring.util.http-response :as response]
    [ring.middleware.reload :refer [wrap-reload]]))

(defn wrap-nocache [handler]
  (fn [request]
    (-> request
        handler
        (assoc-in [:headers "Pragma"] "no-cache"))))

(def routes
  [["/:expr" {:get
              (fn [{{:keys [expr]} :path-params}]
                (-> (m/encode "application/json" {:answer (parser/evaluate-wrapped expr)})
                    (response/ok)
                    (response/content-type "application/json")))}]])

(def handler
  (reitit/ring-handler
    (reitit/router routes)))

(defn -main []
  (jetty/run-jetty
    (-> #'handler
        wrap-nocache
        wrap-reload)
    {:port 3000
     :join? false}))
