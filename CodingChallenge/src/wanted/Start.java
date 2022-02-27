package wanted;

public class Start {

	public static void main(String[] args) {
		// Verbindung herstellen
		APIaccess fbiDatas = new APIaccess();

		// Wanted Bild mit Mugshot erzeugen
		JPImage imageWanted = new JPImage(fbiDatas.getMugshotUrl());

		// Jframe erzeugen und Bild übergeben
		OutputFrame frame = new OutputFrame(imageWanted, 233, 437);
		// Jframe anzeigen lassen
		frame.setVisible(true);
	}
}
