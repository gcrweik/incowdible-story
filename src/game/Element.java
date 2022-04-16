package game;

import java.util.ArrayList;
import java.util.List;

/**
 * Une classe qui permet de creer un element qui peut etre stocker dans le sac a
 * dos ou etre afficher.
 * 
 * @author mohamed_hanouche
 * @version 1.0.0
 */
public class Element implements java.io.Serializable {
	/**
	 * serialVersionUID pour la sauvegarde.
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * La cordonnee x de l'element pour son affichage.
	 */
	int x;
	/**
	 * La cordonnee y de l'element pour son affichage.
	 */
	int y;
	/**
	 * La hauteur de l'element.
	 */
	int imageHeight;
	/**
	 * La largeur de l'element.
	 */
	int imageWidth;
	/**
	 * Le nom du fichier image de l'element.
	 */
	String imageName;
	/**
	 * Le nom de l'element.
	 */
	String name;

	/**
	 * Une liste des dernieres zones par lesquelles est passee le personnage
	 * principal.
	 */
	public List<String> lastZones = new ArrayList<String>();

	/**
	 * Un constructeur qui permet de creer element dans un certain endroit en
	 * utilisant les coordonnees et un nom de fichier d'image.
	 * 
	 * @param x           Une coordonnee x de l'element.
	 * @param y           Une coordonnee y de l'element.
	 * @param imageWidth  Une largeur de l'image.
	 * @param imageHeight Une hauteur de l'image.
	 * @param imageName   Un nom de fichier d'image.
	 */
	public Element(int x, int y, int imageWidth, int imageHeight, String imageName) {
		this.x = x;
		this.y = y;
		this.imageWidth = imageWidth;
		this.imageHeight = imageHeight;
		this.imageName = imageName;
		this.name = "";
	}

	/**
	 * Un constructeur qui permet de creer element dans un certain endroit en
	 * utilisant les coordonnees,un nom de fichier d'image et le nom de l'element.
	 * 
	 * @param x           Une coordonnee x de l'element.
	 * @param y           Une coordonnee y de l'element.
	 * @param imageWidth  Une largeur de l'image.
	 * @param imageHeight Une hauteur de l'image.
	 * @param imageName   Un nom de fichier d'image.
	 * @param n           Un nom de l'element.
	 */
	public Element(int x, int y, int imageWidth, int imageHeight, String imageName, String n) {
		this(x, y, imageWidth, imageHeight, imageName);
		this.name = n;

	}

	/**
	 * Une methode qui permet de redefinir les coordonnees de l'element, en
	 * occurence pour le deplacer.
	 * 
	 * @param x Une coordonnee x.
	 * @param y Une coordonnee y.
	 */
	public void setCoordinates(int x, int y) {
		this.x = x;
		this.y = y;
	}

}
