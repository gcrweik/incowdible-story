package game;

public class MainCharacter extends Element {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public boolean billyMet; // Bloque la porte de la Cellule.
	public boolean joeMet; // Bloque la porte de la Cours Exterieur.
	public boolean jackMet; // Bloque la possibilit� de ramasser les cigarettes.

	public boolean matouRiddle; // Reponse donn�e: JOLIE.
	public boolean jackRiddle; // Les cigarettes trouv�es.
	public boolean joeRiddle; // Reponse correctes au deux enigmes.

	public boolean isInDialog; // Le personnage est en discussion.

	public boolean firstStageJoe; // Premiere de deux question de Joe � ete pos�
	public boolean secondStageJoe; // Deuxieme question de Joe � ete pos�
	public int joePenalty; // Nombre de fautes en repondant � Joe

	public boolean alarmUp; // L'alarme qui bloque certaines portes

	public boolean holeOneDone; // Le premier trou en partant de gauche est creus�
	public boolean holeTwoDone; // Le deuxieme trou en partant de gauche est creus�
	public boolean holeThreeDone; // Le troisieme trou en partant de gauche est creus�
	public boolean holeFourDone; // Le quatrieme trou en partant de gauche est creus�

	public int holeLimit; // Nombre de trous que le joueur a creus�

	public MainCharacter(int x, int y, int imageWidth, int imageHeight, String imageName) {
		super(x, y, imageWidth, imageHeight, imageName);

		billyMet = false;
		joeMet = false;
		jackMet = false;

		matouRiddle = false; // A rechanger
		jackRiddle = false; // A rechanger
		joeRiddle = false; // A rechanger

		isInDialog = false;

		firstStageJoe = false;
		secondStageJoe = false;
		joePenalty = 0;

		alarmUp = false;
		holeOneDone = false;
		holeTwoDone = false;
		holeThreeDone = false;
		holeFourDone = false;

		holeLimit = 0;
	}

}
