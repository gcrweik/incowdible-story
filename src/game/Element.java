package game;

import java.util.ArrayList;
import java.util.List;

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
	public List<String> lastZones = new ArrayList<String>();

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
