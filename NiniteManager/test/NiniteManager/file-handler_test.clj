(ns NiniteManager.file-handler-test
  (:use clojure.test
        NiniteManager.file-handler
        clojure.java.io)
  (:import java.io.File))

(deftest frm-load-test
  (let [f (doto (java.io.File/createTempFile "temp" ".txt") .deleteOnExit)
        prgs '(["cat" "app"] ["cat2" "app2"])]
    (frm-save f prgs)
    (is (= prgs (frm-load f)))))

(deftest download-test
  (let [f (doto (java.io.File/createTempFile "temp" ".txt") .deleteOnExit)
        link "https://ninite.com/chrome/ninite.exe"]
    (download link f)
    (is (= 8 (.length "temp.txt")))))
  
(clojure.test/run-tests)