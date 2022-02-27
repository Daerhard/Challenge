package wanted;

//Importe
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.Graphics;

public class JPImage extends JPanel {   
	//Automatisch erzeugt von Java
	private static final long serialVersionUID = -1421022785216381941L;
	private BufferedImage wantedBackground;
	private BufferedImage image; 
   
   public JPImage(String url) {	   	   
	   try {
		   URL imgURL = ClassLoader.getSystemResource("Background.jpg");
		    wantedBackground = ImageIO.read(imgURL);
			image = ImageIO.read(new URL(url + ".jpg"));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
	        JOptionPane.showMessageDialog(null, "Die Dateien konnten nicht gelesen werden.", "Meldung", JOptionPane.INFORMATION_MESSAGE);  
		}     
   }
 
   @Override
   protected void paintComponent(Graphics g) {
      super.paintComponent(g);
      if(image != null) { 
    	 g.drawImage(wantedBackground, 0, 0, 220, 400, this);
         g.drawImage(image, 17, 123, 185, 155, this);
      }
   }  
}
