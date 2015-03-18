(ns faceboard.page
  (:require [om.core :as om]
            [om-tools.core :refer-macros [defcomponent]]
            [om-tools.dom :as dom]
            [faceboard.logging :refer [log log-err log-warn log-info]]
            [faceboard.views.banner :refer [banner-component]]
            [faceboard.env :as env]))

(defn page-skeleton [& page-content]
  (dom/div {:class "page"}
    (dom/div {:class "page-content"} page-content)
    (om/build banner-component {:git-revision env/git-revision})))