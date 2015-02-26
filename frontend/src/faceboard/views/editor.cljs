(ns faceboard.views.editor
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require [cljs.core.async :refer [put! <! chan]]
            [clojure.set :refer [subset?]]
            [om.core :as om]
            [om-tools.core :refer-macros [defcomponent]]
            [om-tools.dom :as dom]
            [faceboard.utils :refer [model->json]]
            [phalanges.core :as phalanges]
            [faceboard.env :refer [mac?]]
            [faceboard.logging :refer [log, log-err, log-warn]]
            [faceboard.controller :as controller :refer [perform!]]))

(def ^:dynamic *codemirror*)

(defn- codemirror-value []
  (when-not (nil? *codemirror*)
    (.call (aget *codemirror* "getValue") *codemirror*)))

(defn- set-codemirror-value! [value]
  (when-not (nil? *codemirror*)
    (.call (aget *codemirror* "setValue") *codemirror* value)))

(defn- apply-model [event]
  (perform! :apply-model (codemirror-value))
  (.preventDefault event))

(def action-table
  [[#{:meta} #{:ctrl} 83 apply-model]                       ; CMD+S on Mac, CTRL+S elsewhere
   ])

(defn select-action [keycode modifiers]
  (loop [table action-table]
    (when-not (empty? table)
      (let [record (first table)]
        (if (and
              (or
                (and mac? (subset? (nth record 0) modifiers))
                (and (not mac?) (subset? (nth record 1) modifiers)))
              (= keycode (nth record 2)))                   ; workaround https://github.com/spellhouse/phalanges/issues/4
          (nth record 3)
          (recur (rest table)))))))

(defn handle-codemirror-key [event]
  (if-let [action (select-action (phalanges/key-code event) (phalanges/modifier-set event))]
    (action event)))

(defcomponent editor-component [data owner]
  (render [_]
    (let [new-value (model->json data)
          old-value (if (nil? *codemirror*) new-value (codemirror-value))
          not-in-sync (not (= old-value new-value))]
      (dom/div {:class "editor"}
        (dom/div {:class       "editor-host"
                  :ref         "host"
                  :on-key-down #(handle-codemirror-key %)})
        (dom/div {:class    "hint"
                  :title    "Save model and update the app."
                  :on-click (fn [e] (if not-in-sync
                                      (when (js/confirm "Really overwrite external changes?")
                                        (apply-model e))
                                      (apply-model e)))}
          (if mac? "CMD+S to save" "CTRL+S to save"))
        (when not-in-sync
          (dom/div {:class    "refresh"
                    :title    "The model has been modified by someone else. You are editing old data."
                    :on-click (fn [_]
                                (set-codemirror-value! new-value)
                                (om/refresh! owner))} 
            "discard & reload")))))
  (did-mount [_]
    (set! *codemirror* (js/CodeMirror (om/get-node owner "host")
                         #js {:mode              #js {:name "javascript" :json true}
                              :value             (model->json data)
                              :matchBrackets     true
                              :autoCloseBrackets true
                              :styleActiveLine   true
                              :lint              true
                              :smartIndent       true
                              :lineNumbers       true
                              :viewportMargin:   Infinity}))))