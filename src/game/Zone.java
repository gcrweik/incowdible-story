package game;

import java.util.HashMap;

/**
 * Une classe Zone qui permet d'ajouter d'ajouter et d'enlever ainsi que de
 * "stocker" les sorties, les actions, les reponses, etc.
 * 
 * @author mohamed_hanouche
 * @author roman_tyzio
 * @version 1.0.0
 */
public class Zone implements java.io.Serializable {
	/**
	 * serialVersionUID pour la sauvegarde.
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * La description de la zone.
	 */
	public String description;
	/**
	 * Le nom du fichier image pour la zone.
	 */
	private String nameImage;
	/**
	 * Une HashMap pour les sorties.
	 */
	HashMap<String, Zone> exits;
	/**
	 * Une HashMap pour les reponses.
	 */
	HashMap<String, Zone> answers;
	/**
	 * Une HashMap pour les actions.
	 */
	HashMap<String, Zone> actions;

	/**
	 * Une coordonnee x pour l'apparition de du personnage principal dans une zone.
	 */
	public int xSpawn;
	/**
	 * Une coordonnee y pour l'apparition de du personnage principal dans une zone.
	 */
	public int ySpawn;

	/**
	 * Un constructeur de la classe Zone.
	 * 
	 * @param description Une description de la zone.
	 * @param image       Un nom du fichier d'image.
	 * @param xSpawn      Une coordonnee x pour l'apparition de du personnage
	 *                    principal dans une zone.
	 * @param ySpawn      Une coordonnee y pour l'apparition de du personnage
	 *                    principal dans une zone.
	 */
	public Zone(String description, String image, int xSpawn, int ySpawn) {
		this.description = description;
		nameImage = image;
		exits = new HashMap<>();
		actions = new HashMap<>();
		answers = new HashMap<>();
		// Pose les coordonnees d'arrive du personnage dans une salle.
		this.xSpawn = xSpawn;
		this.ySpawn = ySpawn;

	}

	/**
	 * Une methode qui permet d'ajouter une sortie.
	 * 
	 * @param exit            Une sortie qu'on souhaite ajouter.
	 * @param neighboringArea Une zone voisine.
	 */
	public void addExit(Exit exit, Zone neighboringArea) {
		exits.put(exit.name(), neighboringArea);
	}

	/**
	 * Une methode qui permet d'ajouter une action.
	 * 
	 * @param action Une action qu'on souhaite ajouter.
	 */
	public void addAction(Action action) {
		actions.put(action.name(), this);
	}

	/**
	 * Une methode qui permet d'ajouter une reponse.
	 * 
	 * @param answer Une reponse qu'on souhaite ajouter.
	 */
	public void addAnswer(Answer answer) {
		answers.put(answer.name(), this);
	}

	/**
	 * Une methode qui permet d'enlever une action.
	 * 
	 * @param action Une action qu'on souhaite d'enlever.
	 */
	public void removeAction(Action action) {
		actions.remove(action.name());
	}

	/**
	 * Une methode qui permet d'enlever une reponse.
	 * 
	 * @param answer Une reponse qu'on souhaite d'enlever.
	 */
	public void removeAnswer(Answer answer) {
		answers.remove(answer.name());
	}

	/**
	 * Une methode qui permet de verifier si la zone actuel contient une action.
	 * 
	 * @param action Une action dont on souhaite verifier la presence.
	 * @return Si oui ou non l'action est presente.
	 */
	public boolean containsActions(Action action) {
		return actions.containsKey(action.name()) && this.equals(actions.get(action.name()));
	}

	/**
	 * Une methode qui permet de verifier si la zone contient un reponse.
	 * 
	 * @param answer Une reponse dont on souhaite verifier la presence.
	 * @return Si oui ou non la reponse est presente.
	 */
	public boolean containsAnswers(Answer answer) {
		return answers.containsKey(answer.name()) && this.equals(answers.get(answer.name()));
	}

	/**
	 * Une methode qui retourne le nom de l'image.
	 * 
	 * @return Le nom de l'image.
	 */
	public String nameImage() {
		return nameImage;
	}

	/**
	 * Une methode qui retourne al description de la zone.
	 */
	public String toString() {
		return description;
	}

	/**
	 * Une methode qui retourne la zone actuelle ainsi que les sorties disponibles.
	 * 
	 * @return La phrase qui contient la description de la zone actuelle et les
	 *         sorties possibles.
	 */
	public String longDescription() {
		return "Vous etes dans la zone : " + description + "\nSorties : " + exits();
	}

	/**
	 * Une methode qui permet de voir les actions disponibles.
	 * 
	 * @return Les actions disponibles.
	 */
	public String longDescriptionAction() {
		return "Actions : " + actions();
	}

	/**
	 * Une methode qui permet de voir les actions disponibles.
	 * 
	 * @return Les actions disponibles.
	 */
	public String longDescriptionAnswer() {
		return "Reponses : " + answers();
	}

	/**
	 * Une methode qui retourne les descriptions courtes des sorties.
	 * 
	 * @return Les descriptions courtes des sorties.
	 */
	private String exits() {
		return exits.keySet().toString();
	}

	/**
	 * Une methode qui retourne les descriptions courtes des actions.
	 * 
	 * @return Les descriptions courtes des actions.
	 */
	private String actions() {
		return actions.keySet().toString();
	}

	/**
	 * Une methode qui retourne les descriptions courtes des reponses.
	 * 
	 * @return Les descriptions courtes des reponses.
	 */
	private String answers() {
		return answers.keySet().toString();
	}

	/**
	 * Une methode qui retourne une sortie dans la direction choisie.
	 * 
	 * @param direction Direction choisie.
	 * @return La sortie obtenu.
	 */
	public Zone getExit(String direction) {
		return exits.get(direction);
	}
}
