(ns faceboard.data.sample_board
  (:require [faceboard.schema :as schema]))

; just some sample data until we get editing working...

; country codes: http://en.wikipedia.org/wiki/ISO_3166-1_alpha-2

(def hackerparadise-people
  {:groups [{:id    "new"
             :label "new"
             :title "new people"
             :list  ["tony" "nicole"]}
            {:id    "present"
             :label "present"
             :list  ["tony" "nicole" "alexey"]}
            {:id    "left"
             :label "left"
             :list  ["casey"]}]
   :people [{:id "mr.blank"}
            {:id     "tony"
             :bio    {:name      "Tony"
                      :about     "I'm a developer. I hack Mac apps in <a href='http://binaryage.com'>binaryage</a> for living. I do web development and other experiments for fun. I consider myself being a <a href='http://www.startupsfortherestofus.com/'>micropreneur</a>. And I'm a passionate bitcoiner."
                      :photo     {:url "https://media.licdn.com/mpr/mpr/shrink_200_200/p/3/000/000/340/25abdea.jpg"}
                      :full-name "Antonin Hildebrand"
                      :email     "antonin@hildebrand.cz"
                      :phone     "+84 0935 765830"
                      :country   "cz"}
             :tags   ["objective-c"
                      "c++"
                      "clojure"
                      "coffeescript"
                      "ruby"
                      "bitcoin"
                      "totalfinder"
                      "binaryage"]
             :social ["facebook|antonin.hildebrand"
                      "linkedin|hildebrand"
                      "github|darwin"
                      "twitter|dr_win"
                      "reddit|dr_win"
                      "hacker-news|dr_win"
                      "flickr|woid"
                      "lastfm|woid"
                      "skype:antonin.hildebrand"
                      "http://about.me/darwin/"
                      "http://www.binaryage.com/"]}
            {:id   "alexey"
             :bio  {:name      "Alexey"
                    :full-name "Alexey Komissarouk"
                    :photo     {:url "https://dl.dropboxusercontent.com/u/559047/alexey.png"}
                    :country   "ua"}
             :tags ["python" "ruby" "coffeescript"]}
            {:id   "casey"
             :bio  {:name  "Casey"
                    :photo {:url "https://some-invalid-url"}}
             :tags ["go"]}
            {:id   "nicole"
             :bio  {:name    "Nicole"
                    :photo   {:url "https://fbcdn-profile-a.akamaihd.net/hprofile-ak-xfa1/v/t1.0-1/p320x320/10881546_10152973877694314_4257642747410385209_n.jpg?oh=b8d2a194c71b1a1717fd95d2ed58c0d9&oe=5588B5AD&__gda__=1435535261_13840be1f86414a6fe6b67ff99c84839"}
                    :country "us"}
             :tags ["design"]}]})

(defn sample-board []
  (schema/upgrade-schema-if-needed
    {:version 3
     :board   {:name "hacker paradise"}
     :tabs    [{:id      "people"
                :label   "People"
                :kind    "people"
                :content hackerparadise-people}
               {:id      "sample-generic"
                :label   "Generic"
                :content "<b>hello world</b><br>this is a generic HTML content"}
               {:id      "docs"
                :label   "Docs"
                :kind    "iframe"
                :content {:url "https://drive.google.com/embeddedfolderview?id=0B008sEIaoPmvfjd6T3RxeUR4My0zOGtVbVNOeG9YcGhWRHl3Qmlia1VLOFdPOGwwQXJIYmc#grid"}}
               {:id      "calendar"
                :label   "Calendar"
                :kind    "iframe"
                :content {:url "https://www.google.com/calendar/embed?mode=AGENDA&showTitle=0&showPrint=0&showTabs=0&showTz=0&height=750&wkst=2&bgcolor=%23eee&src=uomn8jge32pvacda11fsrqgqqs%40group.calendar.google.com&ctz=Asia/Saigon"}}
               {:id      "sample-gist"
                :label   "Gist"
                :kind    "gist"
                :content {:gist-id "e04bd2ed2da938a2ff48"}}
               {:id             "styled-tab"
                :label          "Styled"
                :style          {:background-color "darkred"}
                :selected-style {:background-color "yellow"}
                :kind           "gist"
                :content        {:gist-id "e04bd2ed2da938a2ff48"}}
               {:id      "sample-inkpad"
                :label   "Inkpad"
                :kind    "inkpad"
                :content {:inkpad-id "aqBdkShhea"}}
               {:id      "more"
                :label   "More"
                :kind    "webget"
                :content {:url "https://www.dropbox.com/"}}]}))