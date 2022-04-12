package game;

public class MainCharacter extends Element {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public boolean billyMet; // Bloque la porte de la Cellule.
	public boolean joeMet; // Bloque la porte de la Cours Exterieur.
	public boolean jackMet; // Bloque la possibilit� de ramasser les cigarettes.

	public boolean matouRiddle; // Reponse donn�e: JOLIE
	public boolean jackRiddle; // Les cigarettes trouv�es
	public boolean joeRiddle; // Reponse correctes au deux enigmes.

	public MainCharacter(int x, int y, int imageWidth, int imageHeight, String imageName) {
		super(x, y, imageWidth, imageHeight, imageName);
		billyMet = false;
		joeMet = false;
		jackMet = false;

		matouRiddle = true;
		jackRiddle = true;
		joeRiddle = true;
	}

}
