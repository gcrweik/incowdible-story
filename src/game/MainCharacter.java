package game;

public class MainCharacter extends Element {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public boolean billyMet;
	public boolean matouRiddle;
	public boolean joeRiddle;
	public boolean jackRiddle;

	public MainCharacter(int x, int y, int imageWidth, int imageHeight, String imageName) {
		super(x, y, imageWidth, imageHeight, imageName);
		billyMet = false; // A jamais rencontrer;
		matouRiddle = true; // True - reste à resoudre;
		joeRiddle = true;
		jackRiddle = true;
	}

}
