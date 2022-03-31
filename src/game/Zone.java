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
	public int xSpawn;
	public int ySpawn;

	public Zone(String description, String image, int xSpawn, int ySpawn) {
		this.description = description;
		nameImage = image;
		exits = new HashMap<>();

		// Pose les coordonnées d'arrivé du personnage dans une salle.
		this.xSpawn = xSpawn;
		this.ySpawn = ySpawn;

	}

	public void addExit(Exit exit, Zone neighboringArea) {
		exits.put(exit.name(), neighboringArea);
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

	private String exits() {
		return exits.keySet().toString();
	}

	public Zone getExit(String direction) {
		return exits.get(direction);
	}
}
