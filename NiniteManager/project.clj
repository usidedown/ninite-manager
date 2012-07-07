(defproject NiniteManager "0.1.0-SNAPSHOT"
  :description "This is a simple program to keep track of your ninite applications"
  :url "https://code.google.com/p/ninite-manager"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.3.0"]
                 [seesaw "1.4.1"]]
  :main NiniteManager.gui
  :jar-name "NiniteManager.jar"
  :uberjar-name "NiniteManager-standalone.jar")

