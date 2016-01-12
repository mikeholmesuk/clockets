(ns clj.clockets.core
  ;(:gen-class)
  (:require [compojure.core :refer :all]
            [ring.middleware.params :refer [wrap-params]]
            [compojure.route :as route]
            [org.httpkit.server :refer :all]))

(defn websocket-handler
  [req]
    (with-channel req channel
      (on-close channel (fn [status] (println (str "Channel closed [" status "]"))))   ;; Log out close status
      (on-receive channel (fn [data]
                            (println (str "Received data: " data))
                            (send! channel data)))))     ;; Echo the status back to end client

(defroutes app-routes
  (GET "/" [] "<h2>This is the index page</h2>")
  (GET "/foo" [] "<h2>This is the shizzle</h2>")
  (GET "/ws" [] websocket-handler)
  (route/resources "/")
  (route/not-found "Route not found :("))

(def application
  (-> app-routes
    wrap-params))

;; Server runtime here
(defonce server (atom nil))

(defn stop-server
  "Stopping the http-kit server"
  []
  (when-not (nil? @server)
    (@server :timeout 100)
    (reset! server nil)))

(defn start-server
  "Initialising the http-kit server"
  []
  ;; Do your own logging here as this does bugger all
  (println "Starting server on port 3000 ...")
  (reset! server (run-server #'application {:port 3000 :join? false})))

(defn -main [& args]
  (start-server))
