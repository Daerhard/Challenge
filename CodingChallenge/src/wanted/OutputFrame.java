package wanted;

import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.JFileChooser;
//Importe
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.filechooser.FileNameExtensionFilter;

public class OutputFrame extends JFrame {
	// Automatisch erzeugt von Java
	private static final long serialVersionUID = -6715995057904774776L;
	private JPopupMenu context;
	private Action saveAct;
	private JPanel image;

	public OutputFrame(JPImage jpImage, int sizeWidth, int sizeHeight) {
		image = jpImage;
		// Bild dem JFrame hinzufügen,
		add(image);

		// JFrame Einstellungen festlegen
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(sizeWidth, sizeHeight);
		setLocationRelativeTo(null);
		setResizable(false);

		// Action speichern erzeugen
		saveAct = new Action("speichern");
		// Kontextmenü erzeugen
		context = new JPopupMenu();
		// Dem Kontextmenü die Action speichern hinzufügen
		context.add(saveAct);
		// Listener hinzufügen
		addMouseListener(new MyContextListener());
	}

	// eine innere Klasse mit dem Listener für das Kontextmenü
	// sie ist von der Adapterklasse MouseAdapter abgeleitet
	private class MyContextListener extends MouseAdapter {

		@Override
		public void mouseReleased(MouseEvent e) {
			// die Methode der übergeordneten Klasse aufrufen
			super.mouseReleased(e);
			// wurde die Maustaste benutzt, die für das Anzeigen des Kontext-Menüs
			// festgelegt ist?
			if (e.isPopupTrigger())
				// dann das Kontext-Menü anzeigen
				context.show(e.getComponent(), e.getX(), e.getY());
		}
	}

	// eine innere Klasse für die Action
	private class Action extends AbstractAction {
		// Automatisch erzeugt von Java
		private static final long serialVersionUID = 1307540592273280138L;

		// der Konstruktor
		public Action(String text) {
			// den Konstruktor der Übergeordneten Klasse mit dem Text und dem Icon aufrufen
			super(text);

			// das ActionCommand
			putValue(ACTION_COMMAND_KEY, text);
		}

		@Override
		public void actionPerformed(ActionEvent evt) {
			if (evt.getActionCommand().equals("speichern"))
				save();
		}
	}

	// Methode für das Speichern
	private void save() {
		// JFILEChooser erzeugen
		JFileChooser saveChooser = new JFileChooser();
		// Filter auf jpg setzen
		FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG", "jpg");
		saveChooser.setAcceptAllFileFilterUsed(false);
		saveChooser.setFileFilter(filter);

		// Variablen
		int returnVal = saveChooser.showSaveDialog(null);
		String dataType = "";
		String path = "";

		if (returnVal == JFileChooser.APPROVE_OPTION) {
			path = saveChooser.getSelectedFile().toString();
			dataType = path.substring(path.length() - 4);
		} else {
			JOptionPane.showMessageDialog(null, "Bitte geben Sie einen Dateinamen ein.", "Meldung",
					JOptionPane.INFORMATION_MESSAGE);
		}

		// Wenn der richtige Datentyp eingetragen wurde kann das Bild gespeichert
		// werden, anonsten wird eine Meldung ausgegeben.
		if (dataType.equals(".jpg")) {
			BufferedImage bufImage = new BufferedImage(image.getSize().width, image.getSize().height,
					BufferedImage.TYPE_INT_RGB);
			image.paint(bufImage.createGraphics());
			File imageFile = new File(path);
			try {
				imageFile.createNewFile();
				ImageIO.write(bufImage, "jpg", imageFile);
				JOptionPane.showMessageDialog(null, "Das Bild wurde gespeichert.", "Meldung",
						JOptionPane.INFORMATION_MESSAGE);
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, "Das Bild konnte nicht gespeichert werden.", "Meldung",
						JOptionPane.INFORMATION_MESSAGE);
			}
		} else
			JOptionPane.showMessageDialog(null, "Falscher Datentyp, nur jpg ist erlaubt.", "Meldung",
					JOptionPane.INFORMATION_MESSAGE);
	}
}
