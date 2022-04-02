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

	//Constructeur permettant de créer un élément ayant des coordonnées.
	public Element(int x, int y, int imageWidth, int imageHeight, String imageName) {
		this.x = x;
		this.y = y;
		this.imageWidth = imageWidth;
		this.imageHeight = imageHeight;
		this.imageName = imageName;
		this.name = "";
	}
	//Constructeur permettant de créer un élément ayant des coordonnées et un nom.
	public Element(int x, int y, int imageWidth, int imageHeight, String imageName, String n) {
		this(x, y, imageWidth, imageHeight, imageName);
		this.name = n;

	}
	//Méthode permettant de changer les coordonnées d'un élément.
	public void setCoordinates(int x, int y) {
		this.x = x;
		this.y = y;
	}

	


}
