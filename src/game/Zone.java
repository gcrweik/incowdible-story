package game;

import java.util.HashMap;

public class Zone implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String description;
	private String nameImage;
	private HashMap<String, Zone> exits;
	HashMap<String, Zone> answers;
	HashMap<String, Zone> actions;
	public int xSpawn;
	public int ySpawn;

	public Zone(String description, String image, int xSpawn, int ySpawn) {
		this.description = description;
		nameImage = image;
		exits = new HashMap<>();
		actions = new HashMap<>();
		answers = new HashMap<>();
		// Pose les coordonnées d'arrivé du personnage dans une salle.
		this.xSpawn = xSpawn;
		this.ySpawn = ySpawn;

	}

	public void addExit(Exit exit, Zone neighboringArea) {
		exits.put(exit.name(), neighboringArea);
	}

	public void addAction(Action action) {
		actions.put(action.name(), this);
	}

	public void addAnswer(Answer answer) {
		answers.put(answer.name(), this);
	}

	public void removeAction(Action action) {
		actions.remove(action.name());
	}

	public void removeAnswer(Answer answer) {
		answers.remove(answer.name());
	}

	public boolean containsActions(Action action) {
		return actions.containsKey(action.name()) && this.equals(actions.get(action.name()));
	}

	public String nameImage() {
		return nameImage;
	}

	public String toString() {
		return description;
	}

	public String longDescription() {
		return "Vous etes dans la zone : " + description + "\nSorties : " + exits();
	}

	public String longDescriptionAction() {
		return "Actions : " + actions();
	}

	public String longDescriptionAnswer() {
		return "Reponses : " + answers();
	}

	private String exits() {
		return exits.keySet().toString();
	}

	private String actions() {
		return actions.keySet().toString();
	}

	private String answers() {
		return answers.keySet().toString();
	}

	public Zone getExit(String direction) {
		return exits.get(direction);
	}
}
