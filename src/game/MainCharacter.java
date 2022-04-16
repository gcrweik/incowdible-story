package game;

/**
 * Une classe qui permet de creer le personnage principal du jeu.
 * 
 * @author mohamed_haouche
 * @author roman_tyzio
 * @version 1.0.0
 *
 */
public class MainCharacter extends Element {

	/**
	 * serialVersionUID pour la sauvegarde.
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Bloque la porte de la Cellule.
	 */
	public boolean billyMet;
	/**
	 * Bloque la porte de la Cours Exterieur.
	 */
	public boolean joeMet;
	/**
	 * Bloque la possibilite de ramasser les cigarettes.
	 */
	public boolean jackMet;

	/**
	 * Bloque la sortie pendant la rencontre avec Jack.
	 */
	public boolean jackBlocked;
	/**
	 * Bloque la sortie pendant la deuxieme rencontre avec Joe.
	 */
	public boolean joeBlocked;
	/**
	 * Bloque la sortie pendant le dialogue ed Billy.
	 */
	public boolean billyBlocked;
	/**
	 * Bloque les sorites pendant l'enigme de Matou.
	 */
	public boolean matouBlocked;

	/**
	 * Reponse donnee: JOLIE.
	 */
	public boolean matouRiddle;
	/**
	 * Les cigarettes trouvees.
	 */
	public boolean jackRiddle;
	/**
	 * Reponse correctes au deux enigmes.
	 */
	public boolean joeRiddle;

	/**
	 * Le personnage est en discussion.
	 */
	public boolean isInDialog;

	/**
	 * Premiere de deux question de Joe a ete posee.
	 */
	public boolean firstStageJoe;
	/**
	 * Deuxieme question de Joe a ete pose
	 */
	public boolean secondStageJoe;
	/**
	 * Nombre de fautes en repondant a l'enigme de Joe.
	 */
	public int joePenalty;

	/**
	 * L'alarme qui bloque certaines portes dans la prison.
	 */
	public boolean alarmUp;

	/**
	 * Le premier trou en partant de gauche est creuse.
	 */
	public boolean holeOneDone;
	/**
	 * // Le deuxieme trou en partant de gauche est creuse.
	 */
	public boolean holeTwoDone;
	/**
	 * Le troisieme trou en partant de gauche est creuse.
	 */
	public boolean holeThreeDone;
	/**
	 * Le quatrieme trou en partant de gauche est creuse
	 */
	public boolean holeFourDone;

	/**
	 * Nombre de trous que le joueur a creuse
	 */
	public int holeLimit;


	/**
	 * Un constructeur de la classe MainCharacter.
	 * 
	 * @param x           Une coordonnee x du personnage principale.
	 * @param y           Une coordonnee y du personnage principale.
	 * @param imageWidth  Une largeur du personnage principale.
	 * @param imageHeight Une hauteur du personnage principale.
	 * @param imageName   Un nom de fichier d'image.
	 */
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
