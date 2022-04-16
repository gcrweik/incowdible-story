package game;

public class MainCharacter extends Element {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public boolean billyMet; // Bloque la porte de la Cellule.
	public boolean joeMet; // Bloque la porte de la Cours Exterieur.
	public boolean jackMet; // Bloque la possibilite de ramasser les cigarettes.

	public boolean jackBlocked; // Bloque la sortie pendant la rencontre avec Jack
	public boolean joeBlocked; // Bloque la sortie pendant la deuxieme rencontre avec Joe.
	public boolean billyBlocked; // Bloque la sortie pendant le dialogue ed Billy
	public boolean matouBlocked; // Bloque les sorites pendant les

	public boolean matouRiddle; // Reponse donnee: JOLIE.
	public boolean jackRiddle; // Les cigarettes trouvees.
	public boolean joeRiddle; // Reponse correctes au deux enigmes.

	public boolean isInDialog; // Le personnage est en discussion.

	public boolean firstStageJoe; // Premiere de deux question de Joe a ete pose
	public boolean secondStageJoe; // Deuxieme question de Joe a ete pose
	public int joePenalty; // Nombre de fautes en repondant a Joe

	public boolean alarmUp; // L'alarme qui bloque certaines portes

	public boolean holeOneDone; // Le premier trou en partant de gauche est creuse
	public boolean holeTwoDone; // Le deuxieme trou en partant de gauche est creuse
	public boolean holeThreeDone; // Le troisieme trou en partant de gauche est creuse
	public boolean holeFourDone; // Le quatrieme trou en partant de gauche est creuse
	
	public int holeLimit; // Nombre de trous que le joueur a creuse

	public MainCharacter(int x, int y, int imageWidth, int imageHeight, String imageName) {
		super(x, y, imageWidth, imageHeight, imageName);

		billyMet = false;
		joeMet = false;
		jackMet = false;

		matouRiddle = true;
		jackRiddle = true;
		joeRiddle = true;

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
