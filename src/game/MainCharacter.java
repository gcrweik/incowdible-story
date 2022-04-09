package game;

public class MainCharacter extends Element {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public boolean matouRiddle;
	public boolean joeRiddle;
	public boolean cigs;
	
	public MainCharacter(int x, int y, int imageWidth, int imageHeight, String imageName) {
		super(x, y, imageWidth, imageHeight, imageName);
		matouRiddle = true;
		cigs = false;
	}

}
