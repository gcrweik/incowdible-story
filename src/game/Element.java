package game;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Element implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int x;
	int y;
	int imageHeight;
	int imageWidth;
	String imageName;

	String name;

	public List<String> lastZones = new ArrayList<String>();

	//Constructeur permettant de cr�er un �l�ment ayant des coordonn�es.
	public Element(int x, int y, int imageWidth, int imageHeight, String imageName) {
		this.x = x;
		this.y = y;
		this.imageWidth = imageWidth;
		this.imageHeight = imageHeight;
		this.imageName = imageName;
		this.name = "";
	}
	//Constructeur permettant de cr�er un �l�ment ayant des coordonn�es et un nom.
	public Element(int x, int y, int imageWidth, int imageHeight, String imageName, String n) {
		this(x, y, imageWidth, imageHeight, imageName);
		this.name = n;

	}
	//M�thode permettant de changer les coordonn�es d'un �l�ment.
	public void setCoordinates(int x, int y) {
		this.x = x;
		this.y = y;
	}

	
	// Methode permettant de cr�er puis afficher l'elements aux coordon�es donn�.
		public JLabel showElement() {
			JLabel objectLabel = new JLabel();
			objectLabel.setBounds(x, y, imageWidth, imageHeight);

			URL imageURL = this.getClass().getClassLoader().getResource("game/images/" + imageName);
			if (imageURL != null) {
				objectLabel.setIcon(new ImageIcon(imageURL));
				GUI.image.add(objectLabel);
				return objectLabel;
			} else
				System.out.println("Une erreur est arriv�e");
			return null;
		}

}
