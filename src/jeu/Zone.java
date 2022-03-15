package jeu;

import java.util.HashMap;

public class Zone implements java.io.Serializable {
	private String description;
	private String nomImage;
	private HashMap<String, Zone> sorties;
	public int xSpawn;
	public int ySpawn;

	public Zone(String description, String image, int xSpawn, int ySpawn) {
		this.description = description;
		nomImage = image;
		sorties = new HashMap<>();

		// Pose les coordonnées d'arrivé du personnage dans une salle.
		this.xSpawn = xSpawn;
		this.ySpawn = ySpawn;

	}

	public void ajouteSortie(Sortie sortie, Zone zoneVoisine) {
		sorties.put(sortie.name(), zoneVoisine);
	}

	public String nomImage() {
		return nomImage;
	}

	public String toString() {
		return description;
	}

	public String descriptionLongue() {
		return "Vous etes dans la zone : " + description + "\nSorties : " + sorties();
	}

	private String sorties() {
		return sorties.keySet().toString();
	}

	public Zone obtientSortie(String direction) {
		return sorties.get(direction);
	}
}
