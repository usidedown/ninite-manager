(ns NiniteManager.core-test
  (:use clojure.test
        NiniteManager.core))

(deftest get-printable-programs-test
  (testing "printable programs"
           (is (= '("iTunes") (get-printable-programs [["Media" "iTunes"]])))
  (is (= '("foxit") (get-printable-programs [["Documents" "Foxit Reader"]])))
  (is (= '("notepadplusplus" "googleearth") (get-printable-programs [["Other" "Google Earth"] ["Developer Tools" "Notepad++"]])))))

(deftest get-ninite-download-link-test
  (testing "download link"
           (is (= "https://ninite.com/itunes/" (get-ninite-download-link [["Media" "iTunes"]])))
           (is (= "https://ninite.com/firefox-.net/" (get-ninite-download-link [["Web Browsers" "Firefox"] ["Runtimes" ".NET"]])))))

(deftest get-ninite-select-link-test
  (testing "select link"
           (is (= "https://ninite.com/?select=itunes/" (get-ninite-select-link [["Media" "iTunes"]])))
           (is (= "https://ninite.com/?select=firefox-.net/" (get-ninite-select-link [["Web Browsers" "Firefox"] ["Runtimes" ".NET"]])))))

(clojure.test/run-tests)