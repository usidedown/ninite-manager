(ns NiniteManager.file-handler(:use clojure.java.io)
  (:import java.io.File
           java.io.PushbackReader
           java.io.FileReader
           java.io.FileWriter))

(def filename ".progs")

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
  (frm-save (as-file filename) programs))

(defn make-prog []
  (when-not (.exists (as-file filename))
    (with-open [w (writer filename)]
      (.write w "#{}\n"))))
  

(defn load-apps []
  (frm-load (as-file filename)))

(defn download  [src-url out-file]
  (copy (input-stream src-url) (as-file out-file) :encoding "ASCII"))
  