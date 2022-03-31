package game;

public class MainCharacter extends Element {

	public boolean matouRiddle;
	public boolean cigs;
	
	public MainCharacter(int x, int y, int imageWidth, int imageHeight, String imageName) {
		super(x, y, imageWidth, imageHeight, imageName);
		matouRiddle = true;
		cigs = false;
	}

}
