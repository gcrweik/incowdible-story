package jeu;

public class Element implements java.io.Serializable {
	int x;
	int y;
	int imageHeight;
	int imageWidth;
	String imageName;

	public Element(int x, int y, int imageWidth, int imageHeight, String imageName) {
		this.x = x;
		this.y = y;
		this.imageWidth = imageWidth;
		this.imageHeight = imageHeight;
		this.imageName = imageName;
	}

	public void setCoordinates(int x, int y) {
		this.x = x;
		this.y = y;
	}

}
