(defproject NiniteManager "0.1.0-SNAPSHOT"
  :description "This is a simple program to keep track of your ninite applications"
  :url "https://code.google.com/p/ninite-manager"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.4.0"]
                 [seesaw "1.4.1"]]
  :main NiniteManager.gui
  :launch4j-config-file "resources/config.xml"
  :jar-name "NiniteManager.jar"
  :uberjar-name "NiniteManager-standalone.jar")

