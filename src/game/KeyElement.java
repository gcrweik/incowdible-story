package game;

import java.util.Random;

/**
 * Une classe qui permet de creer le elements cles du jeu.
 * 
 * @author mohamed_haouche
 * @version 1.0.0
 *
 */
public class KeyElement extends Element {

	/**
	 * serialVersionUID pour la sauvegarde.
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Une instance de la classe Random.
	 */
	Random rand = new Random();
	/**
	 * Un chiffre aleatoire, soit 1 ou 2, qui sert a ajouter un element d'hasard
	 * dans le jeu. !IMPORTANT! Tout le projet utilise l'instance "cigs" de la
	 * classe KeyElement pour recuperer la valeur aleatoire !!!
	 */
	int randomNumber = rand.nextInt(2 - 1 + 1) + 1;

	/**
	 * Un constructeur de la classe KeyElement.
	 * 
	 * @param x           Une coordonnee x de l'element cle.
	 * @param y           Une coordonnee y de l'element cle.
	 * @param imageWidth  Une largeur de l'image.
	 * @param imageHeight Une hauteur de l'image.
	 * @param imageName   Un nom de fichier d'image.
	 * @param n           Un nom d'element cle.
	 */
	public KeyElement(int x, int y, int imageWidth, int imageHeight, String imageName, String n) {
		super(x, y, imageWidth, imageHeight, imageName, n);
	}

	/**
	 * Une methode qui permet d'obtenir le nom d'element cle.
	 */
	public String toString() {
		return this.name;
	}

	/**
	 * Une methode qui permet d'obtenir le nombre aleatoire.
	 * 
	 * @return Le nombre aleatoire.
	 */
	public int getRandomInt() {
		return this.randomNumber;
	}

	/**
	 * Une methode qui defini les coordonnees d'element cle en fonction du nombre
	 * aleatoire de la partie.
	 */
	public void randomSetCoordinates() {
		switch (this.randomNumber) {
		case 1:
			this.setCoordinates(1116, 100);
			break;
		case 2:
			this.setCoordinates(562, 314);
			break;
		}
	}

}
