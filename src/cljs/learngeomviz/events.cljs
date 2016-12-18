(ns learngeomviz.events
    (:require [re-frame.core :as re-frame]
              [learngeomviz.db :as db]))

(re-frame/reg-event-db
 :initialize-db
 (fn  [_ _]
   db/default-db))
