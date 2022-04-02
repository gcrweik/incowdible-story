package game;

import java.util.Random;

public class KeyElement extends Element {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Random rand = new Random();
	int randomNumber = rand.nextInt(2 - 1 + 1) + 1;
	
	public KeyElement(int x, int y, int imageWidth, int imageHeight, String imageName, String n) {
		super(x, y, imageWidth, imageHeight, imageName, n);
		randomSetCoordinates();
	}
	
	public String toString() {
		return this.name;
	}
	
	public void randomSetCoordinates() {
		switch (randomNumber) {
		case 1:
			this.setCoordinates(1116, 100);
			break;
		case 2:
			this.setCoordinates(562, 314);
			break;
		}
	}
	
}
