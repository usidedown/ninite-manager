(ns NiniteManager.gui
  (:use seesaw.core
        [NiniteManager.core :exclude [-main]]
        [NiniteManager.file-handler :include [make-prog]])
  (:import [java.awt.event WindowAdapter]
           [System])
  (:gen-class))

(defn add-tab [panel tab]
     (config! panel :tabs (conj (:tabs panel) tab)))

(defn get-checkbox [cat prog-name]
  (let [c (checkbox :text prog-name 
                    :selected? (contains? *programs* [cat prog-name]))]
    (listen c :action (fn [e] 
                        (if (config c :selected?)
                          (add-program cat prog-name)
                          (remove-program cat prog-name))))
    c))

(defn get-jpanel [cat prgs]
  (top-bottom-split (label cat) 
                    (vertical-panel :items (map (fn [prog-name]
                                                  (get-checkbox cat prog-name))
                                                (keys prgs)))))

(defn add-cat [panel cat prgs]
     (config! panel :items (conj (config panel :items) (get-jpanel cat prgs))))

(defn cat-panel [cat-seq]
  (let [ret (horizontal-panel)]
    (doseq [keyval cat-seq]
      (add-cat ret (key keyval) (val keyval)))
    (config! ret :items (reverse (config ret :items)))))

;TODO refactor
(defn display []
  (let [par (split-at (/ (count categories) 2) categories)
        t (vertical-panel :items (map cat-panel par))
        f (frame :title "NiniteManager" :content t :on-close :dispose)]
    (.addWindowListener f (proxy [WindowAdapter] []
                            (windowClosing [e] (save-programs)
                                           (System/exit 0))))
    (-> f pack! show!)))

(defn display-remote []
  (let [f (frame :title "NiniteManager Remote" :on-close :dispose
                 :content (flow-panel :items [(button :text "Download"
                                                          :listen [:action (fn [e] (download-exe))] )
                                                  (button :text "Ninite Website"
                                                          :listen [:action (fn [e] (browse-select))])
                                                  (button :text "Ninite Download Page"
                                                          :listen [:action (fn [e] (browse-download))])]))]
    
    (-> f pack! show!)))
    

(defn display-tabbed []
  (let [t (tabbed-panel :placement :top :overflow :wrap)
        f (frame :title "NiniteManager" :content t)]
    
    (doseq [keyval categories]
      (add-tab t {:title (key keyval) 
                  :content (get-jpanel (key keyval) (val keyval))}))
    (-> f pack! show!)))

(defn -main [& args]
  (make-prog)
  (load-programs)
  (display)
  (display-remote))