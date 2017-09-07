(ns ^:figwheel-no-load personal-site.dev
  (:require
    [personal-site.core :as core]
    [devtools.core :as devtools]))

(devtools/install!)

(enable-console-print!)

(core/init!)
