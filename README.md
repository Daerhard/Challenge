# Challenge  

# Der Code bezieht sich auf folgende Coding Challenge:
[wanted exercise.pdf](https://github.com/Daerhard/Challenge/files/8239763/wanted.exercise.pdf)

Outline 
In this small exercise, we want to build a small command line (CLI) or browser-based 
application that generates a “Wild West” style DEAD OR ALIVE poster from the 
public FBI Wanted API.

Resources 
- API: https://www.fbi.gov/wanted/api
- Font: “Amasis MT Pro Black“ (installed on Windows by default)

Expected Result 
(For the CLI version) Upon running a command like
java -jar wanted.jar or ts-node index.ts ...

(For a browser-based app) When opening the app …
the program should select a random criminal from the FBI API and generate an (at 
least somewhat) visually appealing poster, including the mugshot. It could be saved
as a viewable file (e.g. HTML, PDF) or presented in the browser

# Entwicklungsumgebung
Der Code wurde in der Entwicklungsumgebung Eclipse (JavaSE-17) entwickelt.
Es wird eine zusätzliche Bibliothek java-json.jar verwendet.

# Aufbau und Funktion
1. Klasse APIaccess - öffnet eine Verbindung zur API der FBI Website und übermittelt die Daten.
2. Klasse JPImage - erzeugt das Wanted Bild mit dem Hintergrund.
3. Klasse OutputFrame - öffnet das erzeugte Bild in einem JFrame, das Bild kann dann über eine Methode gespeichert werden.
4. Klasse Start - startet die Anwenung.
5. Klass JUnitTest - wurde im Nachhinein hinzugefügt und testet die Methoden der Klasse APIaccess.  

Aus dem Code kann eine funktionierende jar Datei erstellt werden, die über die Eingabeaufforderung aufgerufen werden kann.

