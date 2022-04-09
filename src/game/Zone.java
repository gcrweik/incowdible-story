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
	HashMap<String, Zone> actions;
	public int xSpawn;
	public int ySpawn;

	public Zone(String description, String image, int xSpawn, int ySpawn) {
		this.description = description;
		nameImage = image;
		exits = new HashMap<>();
		actions = new HashMap<>();
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
		return "Actions : " +actions();
	}
	private String exits() {
		return exits.keySet().toString();
	}
	
	private String actions() {
		return actions.keySet().toString();
	}
	public Zone getExit(String direction) {
		return exits.get(direction);
	}
}
