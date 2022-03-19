package game;

public class Game implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private GUI gui;
	private Zone currentZone;
	private Element mainCharacter = new Element(563, 139, 32, 64, "MargueriteProjet.png");

	public Game() {
		createMap();
		gui = null;
	}

	public void setGUI(GUI g) {
		gui = g;
		showWelcomeMessage();
	}

	private void createMap() {
		Zone[] zones = new Zone[9];
		zones[0] = new Zone("La Cellule", "Cellule.png", 712, 497);
		zones[1] = new Zone("Couloir", "Couloir.png", 734, 23);
		zones[2] = new Zone("Cours Interieur", "CoursInterieur.png", 693, 139);
		/*
		 * zones[3] = new Zone("Cafétéria", "Cafétéria.png" ); zones[4] = new
		 * Zone("Cours Exterieur", "CoursExterieur.png" ); zones[5] = new
		 * Zone("Cours de Sport", "Sport.png" ); zones[6] = new Zone("Mur", "Mur.png" );
		 * zones[7] = new Zone("Escalier", "Escalier.png"); zones[8] = new
		 * Zone("Bureau de Directeur", "Bureau.png");
		 */
		
		// La sortie de Cellule
		zones[0].addExit(Exit.SUD, zones[1]);

		// Les sorties de Couloir
		zones[1].addExit(Exit.NORD, zones[0]);
		zones[1].addExit(Exit.SUD, zones[2]);

		// Les sorties de Cours Interieur
		zones[2].addExit(Exit.NORD, zones[1]);
		zones[2].addExit(Exit.NORD_EST, zones[7]);
		zones[2].addExit(Exit.EST, zones[3]);
		zones[2].addExit(Exit.OUEST, zones[4]);

		/*
		 * // Les sorties d'Escalier zones[7].ajouteSortie(Sortie.OUEST, zones[2]);
		 * zones[7].ajouteSortie(Sortie.NORD, zones[8]);
		 * 
		 * // Les sorties de Cafétéria zones[3].ajouteSortie(Sortie.OUEST, zones[2]);
		 * 
		 * // Les sorties de Cours Exterieur zones[4].ajouteSortie(Sortie.EST,
		 * zones[2]); zones[4].ajouteSortie(Sortie.SUD, zones[5]);
		 * 
		 * // Les sorties de Cours de Sport zones[5].ajouteSortie(Sortie.NORD,
		 * zones[4]); zones[5].ajouteSortie(Sortie.SUD, zones[6]);
		 * 
		 * // Les sorties de Mur zones[6].ajouteSortie(Sortie.NORD, zones[5]);
		 * 
		 * // Les sorties de Bureau de Directeur zones[8].ajouteSortie(Sortie.OUEST,
		 * zones[7]);
		 */
		currentZone = zones[0];
	}

	private void showLocation() {
		gui.show(currentZone.longDescription());
		gui.show();
	}

	private void showWelcomeMessage() {
		gui.show("Bienvenue !");
		gui.show();
		gui.show("Tapez '?' pour obtenir de l'aide.");
		gui.show();
		gui.show("Appuyez sur la touche 'Escape' pour ouvrir le menu. ");
		gui.show();
		showLocation();
		gui.showElement(mainCharacter);
		gui.showImage(currentZone.nameImage());
	}

	public void executeCommand(String readingCommand) {
		gui.show("> " + readingCommand + "\n");
		switch (readingCommand.toUpperCase()) {
		case "?":
		case "AIDE":
			showHelp();
			break;
		case "N":
		case "NORD":
			goTo("NORD");
			break;
		case "NE":
		case "NORD_EST":
			goTo("NORD_EST");
			break;
		case "S":
		case "SUD":
			goTo("SUD");
			break;
		case "E":
		case "EST":
			goTo("EST");
			break;
		case "O":
		case "OUEST":
			goTo("OUEST");
			break;
		case "Q":
		case "QUITTER":
			finish();
			break;
		default:
			gui.show("Commande inconnue");
			break;
		}
	}

	private void showHelp() {
		gui.show("Etes-vous perdu ?");
		gui.show();
		gui.show("Les commandes autorisées sont :");
		gui.show();
		gui.show(Command.allDescriptions().toString());
		gui.show();
	}

	private void goTo(String direction) {
		Zone newZone = currentZone.getExit(direction);
		if (newZone == null) {
			gui.show("Pas de sortie " + direction);
			gui.show();
		} else {
			// Supprime l'ancien personnage
			gui.image.removeAll();
			currentZone = newZone;
			gui.show(currentZone.longDescription());
			gui.show();
			// Crée et affiche le personnage à la zone de spawn de la salle.
			mainCharacter.setCoordinates(currentZone.xSpawn, currentZone.ySpawn);
			gui.showElement(mainCharacter);
			gui.showImage(currentZone.nameImage());
		}
	}

	private void finish() {
		gui.show("Au revoir...");
		gui.enable(false);
	}
}
