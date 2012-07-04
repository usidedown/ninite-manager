(ns NiniteManager.fileHandler-test
  (:use clojure.test
        NiniteManager.fileHandler))

(deftest frm-load-test
  (let [f (doto (java.io.File/createTempFile "temp" ".txt") .deleteOnExit)
        prgs '(["cat" "app"] ["cat2" "app2"])]
    (frm-save f prgs)
    (is (= prgs (frm-load f)))))
  
(clojure.test/run-tests)