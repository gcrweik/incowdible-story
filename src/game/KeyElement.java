package game;

import java.util.Random;

public class KeyElement extends Element {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Random rand = new Random();
	int randomNumber = rand.nextInt(2 - 1 + 1) + 1;
	// !IMPORTANT! Tout le projet utilise l'instance "cigs" pour recuperer la valeur
	// aleatoire !!!

	public KeyElement(int x, int y, int imageWidth, int imageHeight, String imageName, String n) {
		super(x, y, imageWidth, imageHeight, imageName, n);
	}

	public String toString() {
		return this.name;
	}

	public int getRandomInt() {
		return this.randomNumber;
	}

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
