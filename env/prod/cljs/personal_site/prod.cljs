(ns personal-site.prod
  (:require [personal-site.core :as core]))

;;ignore println statements in prod
(set! *print-fn* (fn [& _]))

(core/init!)
