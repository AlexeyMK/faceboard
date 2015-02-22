(ns faceboard.state
  (:require [faceboard.logging :refer [log, log-err, log-warn]]
            [faceboard.data.sample_board :refer [sample-board]]))

(defonce app-state
  (atom
    {:model sample-board
     :ui    {:view            :welcome
             :view-params     nil
             :selected-tab-id :people
             :tabs            [{:id :people :label "People"}
                               {:id :places :label "Places"}]
             :editing?        false
             :model-editing?  false
             :loading?        false
             :extended-set    #{}
             }
     }
    ))