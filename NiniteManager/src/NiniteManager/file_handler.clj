(ns NiniteManager.file-handler(:use clojure.java.io)
  (:import java.io.File
           java.io.PushbackReader
           java.io.FileReader
           java.io.FileWriter))

(def progs-filename ".progs")
(def categories-filename ".categories")

(defn frm-save
 "Save a clojure form to file."
  [#^java.io.File file form]
  (with-open [w (java.io.FileWriter. file)]
    (binding [*out* w *print-dup* true] (prn form))))

(defn frm-load
  "Load a clojure form from file."
  [#^java.io.File file]
  (with-open [r (java.io.PushbackReader.
     (java.io.FileReader. file))]
     (let [rec (read r)]
      rec)))

(defn save-apps [programs]
  (frm-save (as-file progs-filename) programs))

(defn save-categories [categories]
  (frm-save (as-file categories-filename) categories))

(defn make-prog []
  (when-not (.exists (as-file progs-filename))
    (with-open [w (writer progs-filename)]
      (.write w "#{}\n"))))
  
(defn make-categories [categories]
  (when-not (.exists (as-file categories-filename))
              (save-categories categories)))

(defn load-progs-from-file []
  (frm-load (as-file progs-filename)))

(defn load-categories-from-file []
  (frm-load (as-file categories-filename)))

(defn download  [src-url out-file]
  (copy (input-stream src-url) (as-file out-file) :encoding "ASCII"))
  