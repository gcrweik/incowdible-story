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
	public GUI gui;
	private Zone[] zones = new Zone[9];
	Zone currentZone;
	private MainCharacter mainCharacter = new MainCharacter(563, 139, 32, 64, "MargueriteProjet.png");
	private KeyElement key = new KeyElement(0, 0, 21, 21, "key.png", "Clé");
	private KeyElement cigs = new KeyElement(0, 0, 12, 17, "cigs.png", "Paquet de cigarettes");
	private KeyElement pliers = new KeyElement(583, 260, 15, 20, "pliers.png", "Sécateur");
	private KeyElement shovel = new KeyElement(517, 368, 37, 45, "shovel.png", "Pelle");
	private Npc matou = new Npc(755, 503, 32, 64, "MatouProjet.png");
	private Npc joe = new Npc(609, 219, 32, 64, "OldJoeProjet.png");
	private Npc billy = new Npc(795, 193, 32, 64, "BillyProjet.png");
	private Npc jack = new Npc(595, 337, 32, 64, "JackProjet.png");
	private boolean possibleExit;
	private int timerCounter;

	private Backpack sac = new Backpack();

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

		zones[3] = new Zone("Cafétéria", "Cafétéria.png", 588, 42);
		zones[4] = new Zone("Cours Exterieur", "CoursExterieur.png", 1111, 218);
		zones[5] = new Zone("Cours de Sport", "Sport.png", 771, 36);
		zones[6] = new Zone("Mur", "Mur.png", 766, 296);
		zones[7] = new Zone("Escalier", "Escalier.png", 706, 428);
		zones[8] = new Zone("Bureau de Directeur", "Bureau.png", 656, 299);

		// La sortie de Cellule
		zones[0].addExit(Exit.SUD, zones[1]);
		zones[0].addAction(Action.BILLY);

		// Les sorties de Couloir
		zones[1].addExit(Exit.NORD, zones[0]);
		zones[1].addExit(Exit.SUD, zones[2]);

		// Les sorties de Cours Interieur
		zones[2].addExit(Exit.NORD, zones[1]);
		zones[2].addExit(Exit.NORD_EST, zones[7]);
		zones[2].addExit(Exit.EST, zones[3]);
		zones[2].addExit(Exit.OUEST, zones[4]);
		zones[2].addAction(Action.P1);
		zones[2].addAction(Action.P2);

		// Les sorties d'Escalier
		zones[7].addExit(Exit.OUEST, zones[2]);
		zones[7].addExit(Exit.NORD, zones[8]);

		// Les sorties de Cafétéria
		zones[3].addExit(Exit.OUEST, zones[2]);
		zones[3].addAction(Action.JOE);

		// Les sorties de Cours Exterieur
		zones[4].addExit(Exit.EST, zones[2]);
		zones[4].addExit(Exit.SUD, zones[5]);
		zones[4].addAction(Action.MATOU);

		// Les sorties de Cours de Sport
		zones[5].addExit(Exit.NORD, zones[4]);
		zones[5].addExit(Exit.SUD, zones[6]);
		zones[5].addAction(Action.JACK);

		// Les sorties de Mur
		zones[6].addExit(Exit.NORD, zones[5]);

		// Les sorties de Bureau de Directeur
		zones[8].addExit(Exit.OUEST, zones[7]);

		currentZone = zones[0];
	}

	private void showLocation() {
		gui.show(currentZone.longDescription());
		gui.show();
		gui.show(currentZone.longDescriptionAction());
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
		initialize();
		gui.showImage(currentZone.nameImage());

		cigs.randomSetCoordinates();

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
				gui.show("\nPour executer cette commande il faut etre dans la Cellule!");
				gui.show();
				break;
			}

		case "SA":
		case "SAC":
			showSac();
			break;
		case "JOE":
			if (currentZone == zones[3] && mainCharacter.x != 643) {
				gui.replaceMainCharacter(mainCharacter, 643, 234);

			} else if (currentZone == zones[3] && mainCharacter.x == 643) {
				gui.show("\nVous êtes déjà entrain de parler avec Joe.");
				gui.show();
			}
			break;
		case "BILLY":
			if (currentZone == zones[0] && mainCharacter.x != 719) {
				gui.replaceMainCharacter(mainCharacter, 719, 193);

			} else if (currentZone == zones[0] && mainCharacter.x == 719) {
				gui.show("\nVous êtes déjà entrain de parler avec Billy.");
				gui.show();
			}
			break;
		case "JACK":
			if (currentZone == zones[5] && mainCharacter.x != 639) {
				gui.replaceMainCharacter(mainCharacter, 639, 345);

			} else if (currentZone == zones[5] && mainCharacter.x == 639) {
				gui.show("\nVous êtes déjà entrain de parler avec Jack.");
				gui.show();
			}
			break;
		case "MATOU":
			if (currentZone == zones[4] && mainCharacter.x != 803) {
				gui.replaceMainCharacter(mainCharacter, 803, 528);

			} else if (currentZone == zones[4] && mainCharacter.x == 803) {
				gui.show("\nVous êtes déjà entrain de parler avec Matou.");
				gui.show();
			}
			break;
		case "P1":
			if (currentZone == zones[2] && KeyElement.randomNumber == 2 && mainCharacter.x != 599) {
				sac.addElement(cigs);
				mainCharacter.cigs = true;
				gui.replaceMainCharacter(mainCharacter, 599, 288);
				gui.show("\nVous venez de récuperer un paquet de cigarettes!");
				gui.show();

			} else if (currentZone == zones[2] && mainCharacter.x == 599) {
				gui.show("\nVous êtes déjà entrain de fouiller ce pot.");
				gui.show();
			} else
				gui.show("\nVous n'avez rien trouvez!");
				gui.show();
				gui.replaceMainCharacter(mainCharacter, 599, 288);
			break;
		case "P2":
			if (currentZone == zones[2] && KeyElement.randomNumber == 1 && mainCharacter.x != 1091) {
				sac.addElement(cigs);
				mainCharacter.cigs = true;
				gui.replaceMainCharacter(mainCharacter, 1091, 72);
				gui.show("\nVous venez de récuperer un paquet de cigarettes!");
				gui.show();
			} else if (currentZone == zones[2] && mainCharacter.x == 1091) {
				gui.show("\nVous êtes déjà entrain de fouiller ce pot.");
				gui.show();
			} else
				gui.show("\nVous n'avez rien trouvez!");
				gui.show();
				gui.replaceMainCharacter(mainCharacter, 1091, 72);
			break;
		default:
			gui.show("Commande inconnue");
			gui.show();
			break;
		}
	}

	private void showHelp() {
		gui.show("Etes-vous perdu ?");
		gui.show();
		gui.show("Les commandes autorisÃ©es sont :");
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
			if(!currentZone.actions.isEmpty()) {
			gui.show(currentZone.longDescriptionAction());
			gui.show();}
			// CrÃ©e et affiche le personnage Ã  la zone de spawn de la salle.
			mainCharacter.setCoordinates(currentZone.xSpawn, currentZone.ySpawn);
			gui.showElement(mainCharacter);
			initialize();// doit etre ici pour ne pas que les éléments soit au dernier plan
			gui.showImage(currentZone.nameImage());

			possibleExit = true;

		}
	}

	// Méthode permettant d'afficher les différents élements sur la carte
	// (KeyElement & Npc).
	public void initialize() {
		if (currentZone == zones[4] && mainCharacter.matouRiddle == true) {
			gui.showElement(matou);
			gui.show("Matou est laaa!");
			gui.show();

		}
		if (currentZone == zones[5]) {
			mainCharacter.matouRiddle = false;
		} else if (currentZone == zones[4] && mainCharacter.matouRiddle == false) {
			matou.setCoordinates(679, 520);
			gui.showElement(matou);
		}
		if (currentZone == zones[2] && mainCharacter.cigs == false) {
			gui.showElement(cigs);
		}
		if (currentZone == zones[0]) {
			gui.showElement(billy);
		}
		if (currentZone == zones[3]) {
			gui.showElement(pliers);
			gui.showElement(joe);

		}
		if (currentZone== zones[5]) {
			gui.showElement(shovel);
			gui.showElement(jack);
		}

	}

	private void showSac() {
		String msg = this.sac.toString();
		gui.show(msg);
		gui.show();
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
					System.out.println(currentLine);
					goTo(currentLine);
					timerCounter++;
					if (timerCounter >= list.size()) {
						timerCounter = 0;
						this.cancel();
						gui.show("La solution a fini de s'executer !\n");
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
	 * @param direction La sortie Ã  inverser.
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
	 * Une methode qui permet de recuperer la derniere salle visitÃ© par le joueur.
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
