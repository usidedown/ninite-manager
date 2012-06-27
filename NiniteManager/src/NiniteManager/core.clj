(ns NiniteManager.core (:require clojure.string)
  (:require clojure.contrib.java-utils))

(def categories  { "Web Browsers" {"Chrome" "Chrome"
                                   "Safari" "Safari"
                                   "Opera" "Opera"
                                   "Firefox" "Firefox"}
                  "Messaging" {"Skype" "Skype"
                               "Messenger" "Messenger"
                               "Pidgin" "Pidgin"
                               "Digsby" "Digsby"
                               "Google Talk" "googletalk"
                               "Thunderbird" "Thunderbird"
                               "Trillian" "Trillian"
                               "AIM" "AIM"
                               "Yahoo!" "yahoo"}
                  "Media" {"iTunes" "iTunes"
                           "Songbird" "Songbird"
                           "Hulu" "Hulu"
                           "VLC" "VLC"
                           "KMPlayer" "KMPlayer"
                           "AIMP" "AIMP"
                           "foobar2000" "foobar2000"
                           "Winamp" "Winamp"
                           "Audacity" "Audacity"
                           "K-Lite Codecs" "klitecodecs"
                           "GOM" "GOM"
                           "Spotify" "Spotify"
                           "CCCP" "CCCP"
                           "MediaMonkey" "MediaMonkey"
                           "QuickTime" "QuickTime"}
                  "Runtimes" {"Flash" "Flash"
                              "Flash (IE)" "flashie"
                              "JAVA" "JAVA"
                              ".NET" ".NET"
                              "Silverlight" "Silverlight"
                              "AIR" "AIR"
                              "Shockwave" "Shockwave"}
                  "Imaging" {"Paint.NET" "Paint.NET"
                             "Picasa" "Picasa"
                             "GIMP" "GIMP"
                             "IrfanView" "IrfanView"
                             "XnView" "XnView"
                             "Inkscape" "Inkscape"
                             "FastStone" "FastStone"}
                  "Documents" {"Office" "Office"
                               "OpenOffice" "OpenOffice"
                               "Reader" "Reader"
                               "SumatraPDF" "SumatraPDF"
                               "Foxit Reader" "foxit"
                               "CutePDF" "CutePDF"
                               "LibreOffice" "LibreOffice"
                               "PDFCreator" "PDFCreator"}
                 "Security" {"Essentials" "Essentials"
                             "Avast" "Avast"
                             "AVG" "AVG"
                             "Malwarebytes" "Malwarebytes"
                             "Ad-Aware" "adaware"
                             "Spybot" "Spybot"
                             "Super" "Super"}
                 "File Sharing" {"uTorrent" "uTorrent"
                                 "eMule" "eMule"}
                 "Online Storage" {"Dropbox" "Dropbox"
                                   "Google Drive" "googledrive"
                                   "Mozy" "Mozy"
                                   "SkyDrive" "SkyDrive"}
                 "Other" {"Evernote" "Evernote"
                          "Google Earth" "googleearth"
                          "Steam" "Steam"
                          "KeePass" "KeePass"
                          "Everything" "Everything"}
                 "Utilities" {"TeamViewer" "TeamViewer"
                              "ImgBurn" "ImgBurn"
                              "Auslogics" "Auslogics"
                              "RealVNC" "RealVNC"
                              "TeraCopy" "TeraCopy"
                              "CDBurnerXP" "CDBurnerXP"
                              "TrueCrypt" "TrueCrypt"
                              "Revo" "Revo"
                              "Launchy" "Launchy"
                              "Glary" "Glary"
                              "InfraRecorder" "InfraRecorder"}
                 "Compression" {"7-Zip" "7zip"
                                "PeaZip" "PeaZip"
                                "WinRAR" "WinRAR"}
                 "Developer Tools" {"Python" "Python"
                                    "FileZilla" "FileZilla"
                                    "Notepad++" "notepadplusplus"
                                    "JDK" "JDK"
                                    "WinSCP" "WinSCP"
                                    "PuTTY" "PuTTY"
                                    "WinMerge" "WinMerge"
                                    "Eclipse" "Eclipse"}
                 "Productivity" {"PhraseExpress" "PhraseExpress"
                                 "Belvedere" "Belvedere"}
                 "Lifehacker Utilities" {"SABnzbd" "SABnzbd"
                                         "CrashPlan" "CrashPlan"}
                 "Lifehacker Extended Pack" {"Office Viewers" "officeviewers"
                                             "AutoHotkey" "AutoHotkey"}})

(defn get-printable-programs [programs]
  (loop [acc ()
         [[cat app] prs] [(first programs) (next programs)]]
    (let [calc (conj acc (get (get categories cat) app))]
      (if prs
        (recur calc
               prs)
        calc))))

(defn get-attacher [link]
  (let [attacher
	  (fn [programs]
	  (let [prgs (get-printable-programs programs)]
	    (.toLowerCase (str link 
	         (clojure.string/join (map (fn [prog] (str prog "-")) 
	                                   (next prgs)))
	         (first prgs)
	         "/"))))]
    attacher))

(defn get-ninite-download-link [programs]
  ((get-attacher "https://ninite.com/") programs))
(defn get-ninite-select-link [programs]
  ((get-attacher "https://ninite.com/?select=") programs))
(defn browse-ninite-select [programs]
  (clojure.java.browse/browse-url (get-ninite-select-link programs)))

(defn browse-ninite-download [programs]
  (clojure.java.browse/browse-url (get-ninite-download-link programs)))

(defn save-apps [programs]
  )

(defn frm-save
 "Save a clojure form to file."
  [#^java.io.File file form]
  (with-open [w (java.io.FileWriter. file)]
    (binding [*out* w *print-dup* true] (prn frm))))

(defn frm-load
  "Load a clojure form from file."
  [#^java.io.File file]
  (with-open [r (java.io.PushbackReader.
     (java.io.FileReader. file))]
     (let [rec (read r)]
      rec)))


(defn -main
  "I don't do a whole lot."
  [& args]
  (println "Hello, World!"))
