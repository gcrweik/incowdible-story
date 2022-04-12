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
	private Npc matou = new Npc(755, 503, 32, 64, "MatouProjet.png", "Matou");
	private Npc joe = new Npc(609, 219, 32, 64, "OldJoeProjet.png", "Joe");
	private Npc billy = new Npc(795, 193, 32, 64, "BillyProjet.png", "Billy");
	private Npc jack = new Npc(595, 337, 32, 64, "JackProjet.png", "Jack");
	private boolean possibleExit;
	private int timerCounter;

	private Backpack backpack = new Backpack();

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
		zones[5].addAction(Action.COUPER);

		// Les sorties de Mur
		zones[6].addExit(Exit.NORD, zones[5]);
		zones[6].addAction(Action.I);
		zones[6].addAction(Action.II);
		zones[6].addAction(Action.III);
		zones[6].addAction(Action.IV);
		zones[6].addAction(Action.PRENDRE);

		// Les sorties de Bureau de Directeur
		zones[8].addExit(Exit.OUEST, zones[7]);
		zones[8].addAction(Action.UTILISER);

		currentZone = zones[0];
	}

	private void showLocation() {
		gui.show(currentZone.longDescription());
		gui.show();
		if (!currentZone.actions.isEmpty()) {
			gui.show(currentZone.longDescriptionAction());
			gui.show();
		}
		if (!currentZone.answers.isEmpty()) {
			gui.show(currentZone.longDescriptionAnswer());
			gui.show();
		}
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
			if (currentZone == zones[5]) {
				zones[5].removeAction(Action.PARLER);
				zones[5].removeAction(Action.COUPER);
				zones[5].removeAction(Action.JACK);
				zones[5].removeAction(Action.PRENDRE);
				zones[5].addAction(Action.JACK);
				zones[5].addAction(Action.COUPER);
			}
			if (currentZone == zones[2]) {
				zones[2].removeAction(Action.P1);
				zones[2].removeAction(Action.P2);
				zones[2].removeAction(Action.PRENDRE);
				zones[2].addAction(Action.P1);
				zones[2].addAction(Action.P2);
			}
			if (currentZone == zones[1]) {
				zones[0].addAction(Action.BILLY);
				zones[0].removeAction(Action.PARLER);
			}
			goTo("NORD");
			reverse(readingCommand);
			break;

		case "NE":
		case "NORD_EST":
			if (currentZone == zones[2]) {
				zones[2].removeAction(Action.P1);
				zones[2].removeAction(Action.P2);
				zones[2].removeAction(Action.PRENDRE);
				zones[2].addAction(Action.P1);
				zones[2].addAction(Action.P2);
			}
			goTo("NORD_EST");
			reverse(readingCommand);
			break;

		case "S":
		case "SUD":
			if (currentZone == zones[0] && mainCharacter.billyMet == false) {
				gui.show("J'ai d'abord envie de discuter avec mon voisin.\n");
				break;
			} else if (currentZone == zones[0] && mainCharacter.billyMet == true) {
				goTo("SUD");
				reverse(readingCommand);
				break;
			}
			if (currentZone == zones[4] && mainCharacter.matouRiddle == false) {
				zones[4].removeAnswer(Answer.JOLIE);
				zones[4].removeAnswer(Answer.NEUVE);
				zones[4].removeAnswer(Answer.REPUGNANTE);
				zones[4].removeAction(Action.PARLER);
				zones[4].addAction(Action.MATOU);
				goTo("SUD");
				reverse(readingCommand);
				break;
			}
			if (currentZone == zones[4] && mainCharacter.matouRiddle == true) {
				gui.show("Matou ne semble pas vouloir vous laissez passer!\n");
				break;
			} else if (currentZone == zones[4] && mainCharacter.matouRiddle == false) {
				goTo("SUD");
				reverse(readingCommand);
				break;
			}
			goTo("SUD");
			reverse(readingCommand);
			break;

		case "E":
		case "EST":
			if (currentZone == zones[4]) {
				zones[4].removeAction(Action.PARLER);
				zones[4].removeAnswer(Answer.JOLIE);
				zones[4].removeAnswer(Answer.NEUVE);
				zones[4].removeAnswer(Answer.REPUGNANTE);
				zones[4].addAction(Action.MATOU);
			}
			if (currentZone == zones[2]) {
				zones[2].removeAction(Action.P1);
				zones[2].removeAction(Action.P2);
				zones[2].removeAction(Action.PRENDRE);
				zones[2].addAction(Action.P1);
				zones[2].addAction(Action.P2);
			}
			goTo("EST");
			reverse(readingCommand);
			break;

		case "O":
		case "OUEST":
			if (currentZone == zones[2]) {
				zones[2].removeAction(Action.P1);
				zones[2].removeAction(Action.P2);
				zones[2].removeAction(Action.PRENDRE);
				zones[2].addAction(Action.P1);
				zones[2].addAction(Action.P2);
			}
			if (currentZone == zones[3]) {
				zones[3].removeAction(Action.PARLER);
				zones[3].removeAction(Action.PRENDRE);
				zones[3].addAction(Action.JOE);
			}
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
			showBackpack();
			break;

		case "JOE":
			zones[3].removeAction(Action.JOE);
			if (currentZone == zones[3] && mainCharacter.x != 643) {
				zones[3].addAction(Action.PARLER);
				zones[3].addAction(Action.PRENDRE);
				gui.replaceMainCharacter(mainCharacter, 643, 234);
			}
			break;

		case "BILLY":
			zones[0].removeAction(Action.BILLY);
			if (currentZone == zones[0] && mainCharacter.x != 719) {
				zones[0].addAction(Action.PARLER);
				gui.replaceMainCharacter(mainCharacter, 719, 193);
			}
			break;

		case "JACK":
			zones[5].removeAction(Action.JACK);
			if (currentZone == zones[5] && mainCharacter.x != 639) {
				zones[5].addAction(Action.PARLER);
				zones[5].addAction(Action.PRENDRE);
				gui.replaceMainCharacter(mainCharacter, 639, 345);

			}
			break;

		case "MATOU":
			zones[4].removeAction(Action.MATOU);
			if (currentZone == zones[4] && mainCharacter.matouRiddle == false && mainCharacter.x != 682) {
				zones[4].addAction(Action.PARLER);
				gui.replaceMainCharacter(mainCharacter, 682, 465);

			}
			if (currentZone == zones[4] && mainCharacter.matouRiddle == true && mainCharacter.x != 755) {
				zones[4].addAction(Action.PARLER);
				gui.replaceMainCharacter(mainCharacter, 755, 440);

			}

			break;

		case "P1":
			zones[2].addAction(Action.PRENDRE);
			zones[2].addAction(Action.P2);
			zones[2].removeAction(Action.P1);
			if (currentZone == zones[2] && mainCharacter.x != 599) {
				gui.replaceMainCharacter(mainCharacter, 599, 288);
			}
			break;

		case "P2":
			zones[2].addAction(Action.PRENDRE);
			zones[2].addAction(Action.P1);
			zones[2].removeAction(Action.P2);
			if (currentZone == zones[2] && mainCharacter.x != 1091) {
				gui.replaceMainCharacter(mainCharacter, 1091, 72);
			}
			break;

		case "PARLER":
			// Parler à Billy
			if (currentZone == zones[0] && mainCharacter.x == 719 && currentZone.containsActions(Action.PARLER)) {
				try {
					mainCharacter.billyMet = true;
					executeDialog(billy);
					gui.show();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}
			// Parler à Joe
			if (currentZone == zones[3] && mainCharacter.x == 643 && currentZone.containsActions(Action.PARLER)) {
				try {
					executeDialog(joe);
					gui.show();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}
			// Parler à Matou
			// Position 1

			if (currentZone == zones[4] && mainCharacter.x == 755 && currentZone.containsActions(Action.PARLER)) {
				try {
					executeDialog(matou);
					gui.show();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}
			// Position 2
			if (currentZone == zones[4] && mainCharacter.x == 682 && currentZone.containsActions(Action.PARLER)) {

				try {

					executeDialog(matou);
					gui.show();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}

			break;

		case "PRENDRE":
			if (currentZone == zones[2] && cigs.randomNumber == 1 && mainCharacter.x == 1091
					&& backpack.cigs == false) {
				backpack.addElement(cigs);
				backpack.cigs = true;
				gui.replaceMainCharacter(mainCharacter, 1091, 72);
				gui.show("Vous venez de récuperer un paquet de cigarettes!\n");
			} else if (currentZone == zones[2] && cigs.randomNumber == 2 && mainCharacter.x == 599
					&& backpack.cigs == false) {
				backpack.addElement(cigs);
				backpack.cigs = true;
				gui.replaceMainCharacter(mainCharacter, 599, 288);
				gui.show("Vous venez de récuperer un paquet de cigarettes!\n");
			}
			
			if(currentZone == zones[5] && backpack.shovel == false) {
				backpack.removeElement(cigs);
				jack.setDialogState(1);
				backpack.addElement(shovel);
				backpack.shovel = true;
				initialize();
				gui.show("Vous venez de récuperer une pelle!\n");
			}
			else {
				gui.show("Il n'y a rien à ramasser !\n");
			}
			break;

		case "REPUGNANTE":
		case "NEUVE":
			if (currentZone == zones[4] && mainCharacter.matouRiddle == true && mainCharacter.x == 755) {
				try {
					matou.setDialogState(2);
					executeDialog(matou);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}
			break;

		case "JOLIE":
			if (currentZone == zones[4] && mainCharacter.matouRiddle == true && mainCharacter.x == 755) {
				try {
					mainCharacter.matouRiddle = false;
					matou.setDialogState(3);
					executeDialog(matou);
					initialize();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}
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
		gui.show("Les commandes autorisees sont :");
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
			//gui.image.removeAll(); A supprimer?
			currentZone = newZone;
			gui.show(currentZone.longDescription());
			gui.show();
			if (!currentZone.actions.isEmpty()) {
				gui.show(currentZone.longDescriptionAction());
				gui.show();
			}
			if (!currentZone.answers.isEmpty()) {
				gui.show(currentZone.longDescriptionAnswer());
				gui.show();
			}
			// Créee et affiche le personnage à la zone de spawn de la salle.
			mainCharacter.setCoordinates(currentZone.xSpawn, currentZone.ySpawn);
			//gui.showElement(mainCharacter); A supprimer?
			initialize();// doit etre ici pour ne pas que les éléments soit au dernier plan
			gui.showImage(currentZone.nameImage());

			possibleExit = true;

		}
	}

	// Méthode permettant d'afficher les différents élements sur la carte
	// (KeyElement & Npc).
	public void initialize() {
		gui.image.removeAll();//changement ici
		gui.showElement(mainCharacter);//et ici
		if (currentZone == zones[4] && mainCharacter.matouRiddle == true) {
			gui.showElement(matou);
			gui.show();
		}
		if (currentZone == zones[4] && mainCharacter.matouRiddle == false) {
			matou.setCoordinates(679, 520);
			gui.showElement(matou);

		}
		if (currentZone == zones[2] && backpack.cigs == false) {
			gui.showElement(cigs);
		}
		if (currentZone == zones[0]) {
			gui.showElement(billy);
		}
		if (currentZone == zones[3]) {
			gui.showElement(pliers);
			gui.showElement(joe);

		}
		if (currentZone == zones[5] && backpack.shovel == false) {
			gui.showElement(shovel);
			gui.showElement(jack);
		}
		else if (currentZone == zones[5] && backpack.shovel == true) {
			gui.showElement(jack);
		}

	}

	private void showBackpack() {
		String msg = this.backpack.toString();
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
					switch (currentLine) {
					case "Billy":
						gui.replaceMainCharacter(mainCharacter, 719, 193);
						break;
					case "Joe":
						gui.replaceMainCharacter(mainCharacter, 643, 234);
						break;
					case "Jack":
						gui.replaceMainCharacter(mainCharacter, 639, 345);
						break;
					case "Matou":
						gui.replaceMainCharacter(mainCharacter, 803, 528);
						break;
					case "Parler":
						gui.show("Marguerite a eu un dialog.\n");
						break;
					default:
						goTo(currentLine);
						break;
					}
					timerCounter++;
					if (timerCounter >= list.size()) {
						timerCounter = 0;
						this.cancel();
						gui.show("La solution a fini de s'executer !\n");
					}

				}
			};
			Timer timer = new Timer("Timer");
			timer.schedule(task, 1000, 2000);
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

	/**
	 * Une methode qui permet d'executer un dialogue du npc
	 * 
	 * @param npc Npc qui parle.
	 * @throws FileNotFoundException
	 */
	private void executeDialog(Npc npc) throws FileNotFoundException {

		TimerTask task = new TimerTask() {

			public void run() {

				if (npc.getName().equals("Billy")) {
					gui.show(npc.dialogBilly());
				} else if (npc.getName().equals("Matou")) {
					gui.show(npc.dialogMatou());
				} else if (npc.getName().equals("Joe")) {
					gui.show(npc.dialogJoe());
				} else if (npc.getName().equals("Jack")) {

				}
				timerCounter++;
				npc.dialogCounter++;
				if (timerCounter > npc.getNpcDialogs().size()) {
					timerCounter = 0;
					npc.dialogCounter = 0;
					gui.show();

					if (npc.getName().equals("Billy")) {
						billy.setDialogState(1);
					}
					if (npc.getName().equals("Joe")) {
						joe.setDialogState(1);
					}
					if (npc.getName().equals("Matou")) {
						// Si on choici "Neuve","Repugnante" dans le dialog avec Matou
						if (matou.getDialogState() == 2) {
							GUI.musicGame.stopMusic();
							GUI.disposeGUIFrame();
							@SuppressWarnings("unused")
							End end = new End("BadEnding");
						} else if (matou.getDialogState() == 3) {
							matou.setDialogState(4);
							zones[4].removeAnswer(Answer.JOLIE);
							zones[4].removeAnswer(Answer.NEUVE);
							zones[4].removeAnswer(Answer.REPUGNANTE);
							zones[4].removeAction(Action.PARLER);
							zones[4].addAction(Action.MATOU);

						} else if (matou.getDialogState() == 4) {
							zones[4].removeAnswer(Answer.JOLIE);
							zones[4].removeAnswer(Answer.NEUVE);
							zones[4].removeAnswer(Answer.REPUGNANTE);
						} else {

							matou.setDialogState(1);
							zones[4].addAnswer(Answer.NEUVE);
							zones[4].addAnswer(Answer.REPUGNANTE);
							zones[4].addAnswer(Answer.JOLIE);
						}
					}

					if (npc.getName().equals("Jack")) {
						jack.setDialogState(1);
					}
					showLocation();
					this.cancel();

				}

			}
		};

		Timer timer = new Timer("Timer");
		timer.schedule(task, 1000, 3000);

	}

	private void finish() {
		gui.show("Au revoir...");
		gui.enable(false);
	}
}
