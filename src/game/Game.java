package game;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class Game implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private GUI gui;
	private Zone[] zones = new Zone[9];
	private Zone currentZone;
	private Element mainCharacter = new Element(563, 139, 32, 64, "MargueriteProjet.png");
	private boolean possibleExit;
	private int timerCounter;

	private File f = new File("solutions/solution1.txt");

	public Game() {
		createMap();
		gui = null;
	}

	public void setGUI(GUI g) {
		gui = g;
		showWelcomeMessage();
	}

	private void createMap() {
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
		 * // Les sorties d'Escalier zones[7].addExit(Exit.OUEST, zones[2]);
		 * zones[7].addExit(Exit.NORD, zones[8]);
		 * 
		 * // Les sorties de Cafétéria zones[3].addExit(Exit.OUEST, zones[2]);
		 * 
		 * // Les sorties de Cours Exterieur zones[4].addExit(Exit.EST, zones[2]);
		 * zones[4].addExit(Exit.SUD, zones[5]);
		 * 
		 * // Les sorties de Cours de Sport zones[5].addExit(Exit.NORD, zones[4]);
		 * zones[5].addExit(Exit.SUD, zones[6]);
		 * 
		 * // Les sorties de Mur zones[6].addExit(Exit.NORD, zones[5]);
		 * 
		 * // Les sorties de Bureau de Directeur zones[8].addExit(Exit.OUEST, zones[7]);
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
			reverse(readingCommand);
			break;
		case "NE":
		case "NORD_EST":
			goTo("NORD_EST");
			reverse(readingCommand);
			break;
		case "S":
		case "SUD":
			goTo("SUD");
			reverse(readingCommand);
			break;
		case "E":
		case "EST":
			goTo("EST");
			reverse(readingCommand);
			break;
		case "O":
		case "OUEST":
			goTo("OUEST");
			reverse(readingCommand);
			break;
		case "Q":
		case "QUITTER":
			finish();
			break;
		case "R":
		case "RETOUR":
			goTo(returnTo());
			break;
		case "T":
		case "TERMINER":
			if (currentZone == zones[0]) {
				executeSolution(f);
				break;
			} else {
				gui.show("Pour executer cette commande il faut etre dans la Cellule!");
				break;
			}

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
			possibleExit = false;
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
			possibleExit = true;
		}
	}

	/**
	 * Une methode qui permet d'executer un fichier texte avec la suite des
	 * commander pour terminer la partie.
	 * 
	 * @param solution Le fichier d'execution.
	 */
	private void executeSolution(File solution) {
		Scanner input;
		try {
			input = new Scanner(solution);
			List<String> list = new ArrayList<String>();
			while (input.hasNextLine()) {
				list.add(input.nextLine());
			}

			TimerTask task = new TimerTask() {

				public void run() {
					String currentLine = list.get(timerCounter);
					goTo(currentLine);
					timerCounter++;
					if (timerCounter >= list.size()) {
						timerCounter = 0;
						this.cancel();
						gui.show("La solution a fini de s'executer !");
					}

				}
			};
			Timer timer = new Timer("Timer");
			timer.schedule(task, 1000, 1000);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Une methode qui permet d'ajouter l'inverse de la sortie prise par la personne
	 * dans une liste.
	 * 
	 * @param direction La sortie à inverser.
	 * @return String vide.
	 */
	private String reverse(String direction) {

		char d = direction.toUpperCase().charAt(0);
		switch (d) {
		case 'N':
			direction = "SUD";
			break;
		case 'S':
			direction = "NORD";
			break;
		case 'O':
			direction = "EST";
			break;
		case 'E':
			direction = "OUEST";
			break;

		}
		if (possibleExit) {
			mainCharacter.lastZones.add(direction);
		}
		return "";
	}

	/**
	 * Une methode qui permet de recuperer la derniere salle visité par le joueur.
	 * 
	 * @return Le nom de la derniere salle.
	 */
	private String returnTo() {
		if (mainCharacter.lastZones.size() > 0) {
			String lastCommand = mainCharacter.lastZones.get(mainCharacter.lastZones.size() - 1);
			mainCharacter.lastZones.remove(mainCharacter.lastZones.size() - 1);

			return lastCommand;
		}
		return "\n Il n'y a plus de retour possible.";

	}

	private void finish() {
		gui.show("Au revoir...");
		gui.enable(false);
	}
}
