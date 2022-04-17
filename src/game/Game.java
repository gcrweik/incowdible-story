package game;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Une classe qui permet d'afficher la map,le personnage,les Npc, les
 * elements.Une des classes les plus importantes.
 * 
 * @author mohamed_hanouche
 * @author roman_tyzio
 * @version 1.0.0
 *
 */
public class Game implements java.io.Serializable {

	/**
	 * serialVersionUID pour la sauvegarde.
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Une instance de la classe GUI.
	 */
	public GUI gui;
	/**
	 * Une list d'instances de la classe Zone.
	 */
	private Zone[] zones = new Zone[9];
	/**
	 * Une zone dans laquelle se trouve actuellement le personnage.
	 */
	Zone currentZone;
	/**
	 * Le personnge principal, Marguerite.
	 */
	private MainCharacter mainCharacter = new MainCharacter(563, 139, 32, 64, "MargueriteProjet.png");
	/**
	 * La clé qui ouvre le bureau de directeur du prison.
	 */
	private KeyElement key = new KeyElement(0, 0, 21, 21, "key.png", "Clé");
	/**
	 * Les cigarettes qu'on echange contre le pelle chez Jack.
	 */
	private KeyElement cigs = new KeyElement(0, 0, 12, 17, "cigs.png", "Paquet de cigarettes");
	/**
	 * Un secateur qu'on obtient en reussissant l'enigme de Joe.
	 */
	private KeyElement pliers = new KeyElement(583, 260, 15, 20, "pliers.png", "Sécateur");
	/**
	 * Une pelle qu'on obtient en donnant les cigarettes a Jack.
	 */
	private KeyElement shovel = new KeyElement(517, 368, 37, 45, "shovel.png", "Pelle");
	/**
	 * Le trou n1 devant le mur.
	 */
	private KeyElement holeGround1 = new KeyElement(687, 357, 39, 38, "hole_ground.png", "Trou1");
	/**
	 * Le trou n2 deavnt le mur.
	 */
	private KeyElement holeGround2 = new KeyElement(740, 357, 39, 38, "hole_ground.png", "Trou2");
	/**
	 * Le trou n3 devant le mur.
	 */
	private KeyElement holeGround3 = new KeyElement(795, 357, 39, 38, "hole_ground.png", "Trou3");
	/**
	 * Le trou n4 deavnt le mur.
	 */
	private KeyElement holeGround4 = new KeyElement(849, 357, 39, 38, "hole_ground.png", "Trou4");
	/**
	 * Le trou dans la Cours Interieur.
	 */
	private KeyElement holeFloor = new KeyElement(884, 144, 76, 37, "hole_floor.png", "Trou5");
	/**
	 * La trappe dans le bureau de directeur.
	 */
	private KeyElement trapDoor = new KeyElement(991, 232, 36, 36, "trapdoor.png", "Trapdoor");
	/**
	 * Le Npc "Matou".
	 */
	private Npc matou = new Npc(755, 503, 32, 64, "MatouProjet.png", "Matou");
	/**
	 * Le Npc "Joe" ou "Old Joe".
	 */
	private Npc joe = new Npc(609, 219, 32, 64, "OldJoeProjet.png", "Joe");
	/**
	 * Le Npc "Billy".
	 */
	private Npc billy = new Npc(795, 193, 32, 64, "BillyProjet.png", "Billy");
	/**
	 * Le Npc "Jack".
	 */
	private Npc jack = new Npc(595, 337, 32, 64, "JackProjet.png", "Jack");
	/**
	 * Indique si il existe une sortie dans la direction indique.
	 */
	private boolean possibleExit;
	/**
	 * Compteur pour les deux Timer.
	 */
	private int timerCounter;
	/**
	 * Indique si on doit afficher les resultats de la fonction showLocation();
	 */
	private boolean noShowLocation;

	/**
	 * Une instance de la classe Backpack.
	 */
	private Backpack backpack = new Backpack();

	/**
	 * Un fichier soluce pour la fin normale si la variable aleatoire est egale a 1.
	 */
	private File file1 = new File("solutions/solution1.txt");
	/**
	 * Un fichier soluce pour la fin normale si la variable aleatoire est egale a 2.
	 */
	private File file2 = new File("solutions/solution2.txt");

	/**
	 * Un fichier soluce pour la fin secrete si la variable aleatoire est egale a 1.
	 */
	private File secretFile1 = new File("solutions/secret_solution1.txt");
	/**
	 * Un fichier soluce pour la fin secrete si la variable aleatoire est egale a 2.
	 */
	private File secretFile2 = new File("solutions/secret_solution2.txt");

	/**
	 * Une methode qui permet de lancer l'affichage de la carte.
	 */
	public Game() {
		createMap();
		gui = null;
	}

	/**
	 * Une methode qui permet de de definir le GUI du jeu.
	 * 
	 * @param g Le gui pour le jeu.
	 */
	public void setGUI(GUI g) {
		gui = g;
		showWelcomeMessage();

	}

	/**
	 * Une methode qui permet d'ajouter les zones avec leurs sorties et actions.
	 * Definie aussi la zone dans lequel le personnage apparait.
	 */
	private void createMap() {

		zones[0] = new Zone("La Cellule", "Cellule.png", 712, 497);
		zones[1] = new Zone("Couloir", "Couloir.png", 734, 23);
		zones[2] = new Zone("Cours Interieur", "CoursInterieur.png", 693, 139);
		zones[3] = new Zone("Cafétéria", "Cafétéria.png", 588, 42);
		zones[4] = new Zone("Cours Exterieur", "CoursExterieur.png", 1111, 218);
		zones[5] = new Zone("Cours de Sport", "Sport.png", 771, 36);
		zones[6] = new Zone("Mur", "Mur.png", 765, 301);
		zones[7] = new Zone("Escalier", "Escalier.png", 734, 394);
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
		zones[5].addAction(Action.JACK);
		zones[5].addAction(Action.GRILLAGE);

		// Les sorties de Mur
		zones[6].addAction(Action.T1);
		zones[6].addAction(Action.T2);
		zones[6].addAction(Action.T3);
		zones[6].addAction(Action.T4);

		// Les sorties de Bureau de Directeur
		zones[8].addExit(Exit.OUEST, zones[7]);

		currentZone = zones[0];
	}

	/**
	 * Une methode qui permet de voir les sorties,actions,reponses disponibles en ce
	 * moment.
	 */
	private void showLocation() {
		if (!currentZone.exits.isEmpty()) {
			gui.show(currentZone.longDescription());
			gui.show();
		}
		if (!currentZone.actions.isEmpty()) {
			gui.show(currentZone.longDescriptionAction());
			gui.show();
		}
		if (!currentZone.answers.isEmpty()) {
			gui.show(currentZone.longDescriptionAnswer());
			gui.show();
		}
	}

	/**
	 * Une methode qui permet d'afficher le message de bienvenu au lancement du jeu.
	 */
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

	/**
	 * Une methode qui permet en fonction de {@code readingCommand} executer une
	 * commande ( une action,une reponse, etc.). C'est ici qu'on change les
	 * variables qui permettent de faire les "quetes". Une des methodes les plus
	 * importantes.
	 * 
	 * @param readingCommand Une commande entrée par le joueur ou par nous meme.
	 */
	public void executeCommand(String readingCommand) {
		gui.show("> " + readingCommand + "\n");
		switch (readingCommand.toUpperCase()) {
		case "?":
		case "AIDE":
			showHelp();
			break;

		// Aller en Nord
		case "N":
		case "NORD":
			// Blocage de la sortie NORD pendant la premiere rencontre avec Jack;
			if (currentZone == zones[5] && mainCharacter.jackBlocked == true) {
				gui.show("Vous etes entrain de discuter avec Jack !\n");
			} else {
				// Blocage de l'action Grillage pendant la deuxieme rencontre avec Jack
				if (currentZone == zones[5] && mainCharacter.jackBlocked == true && jack.getDialogState() == 2) {
					gui.show("Vous etes entrain de discuter avec Jack !\n");

					break;
				} else {
					// Blocage de la sortie NORD par l'alarme
					if (currentZone == zones[2] && mainCharacter.alarmUp == true && backpack.key == true) {
						gui.show("Vous devez vous echappez, vous n'avez pas le temps pour se balader !\n");
						showLocation();
					} else {
						if (currentZone == zones[5]) {
							zones[5].removeAction(Action.PARLER);
							zones[5].removeAction(Action.GRILLAGE);
							zones[5].removeAction(Action.JACK);
							zones[5].removeAction(Action.PRENDRE);
							zones[5].addAction(Action.JACK);
							zones[5].addAction(Action.GRILLAGE);
						}
						if (currentZone == zones[2] && mainCharacter.jackMet == true && mainCharacter.jackRiddle == true
								&& backpack.cigs == false) {
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
						if (currentZone == zones[7] && mainCharacter.alarmUp == true && backpack.key == true) {
							zones[8].addAction(Action.BUREAU);
							gui.show("Vous avez montez les escaliers et la encore, une porte devant vous.\n");
						}
						goTo("NORD");
						reverse(readingCommand);
						break;
					}
				}
			}
			break;

		// Aller en Nord-Est
		case "NE":
		case "NORD_EST":
			// Blocage des Escaliers par la clé
			if (currentZone == zones[2] && backpack.key == true && mainCharacter.alarmUp == true) {
				gui.show("Vous avez ouvert la porte avec la cle ! Il faut se depecher !\n");
				gui.show("La porte se ferme sans possibilité de la ouvrir derriere vous !\n");
				goTo("NORD_EST");
				reverse(readingCommand);
				break;
			} else if (currentZone == zones[2] && backpack.key == false && mainCharacter.alarmUp == false) {
				gui.show("Il semble que cette porte est fermé...\n");
				showLocation();
			}
			break;

		// Aller en Sud
		case "S":
		case "SUD":

			if (currentZone == zones[0] && mainCharacter.billyMet == false) {
				gui.show("J'ai d'abord envie de discuter avec mon voisin.\n");
				break;
			}
			if (currentZone == zones[0] && mainCharacter.billyBlocked == true) {
				gui.show("Vous etes entrain de discuter avec Billy !\n");
				break;
			}
			if (currentZone == zones[4] && mainCharacter.matouRiddle == false) {

				// Blocage de la sortie SUD pendant le dialogue de Matou
				if (currentZone == zones[4] && mainCharacter.matouBlocked == true) {
					gui.show("Vous etes entrain de parler à Matou!\n");
					break;
				} else if (currentZone == zones[4] && mainCharacter.matouBlocked == false) {
					zones[4].removeAnswer(Answer.JOLIE);
					zones[4].removeAnswer(Answer.NEUVE);
					zones[4].removeAnswer(Answer.REPUGNANTE);
					zones[4].removeAction(Action.PARLER);
					zones[4].addAction(Action.MATOU);
					goTo("SUD");
					reverse(readingCommand);
					break;
				}

			}

			// Blocage de la sortie SUD pendant la quete de Matou
			if (currentZone == zones[4] && mainCharacter.matouRiddle == true) {
				gui.show("Matou ne semble pas vouloir vous laissez passer!\n");
				break;

			}

			goTo("SUD");
			reverse(readingCommand);
			break;

		// Aller en Est
		case "E":
		case "EST":
			// Blocage de la sortie EST par l'alarme
			if (currentZone == zones[2] && mainCharacter.alarmUp == true && backpack.key == true) {
				gui.show("Vous devez vous echappez, vous n'avez pas le temps pour se balader !\n");
				showLocation();
			} else {

				// Blocage de la sortie EST pendant la quete de Matou
				if (currentZone == zones[4] && mainCharacter.matouRiddle == true) {
					gui.show("Matou ne semble pas vouloir vous laissez partir!\n");
					break;
				}
				// Blocage de la sortie EST pendant le dialogue de Matou
				if (currentZone == zones[4] && mainCharacter.matouBlocked == true) {
					gui.show("Matou ne semble pas vouloir vous laissez partir!\n");
					break;
				}

				if (currentZone == zones[4]) {
					zones[4].removeAction(Action.PARLER);
					zones[4].removeAnswer(Answer.JOLIE);
					zones[4].removeAnswer(Answer.NEUVE);
					zones[4].removeAnswer(Answer.REPUGNANTE);
					zones[4].addAction(Action.MATOU);
				}
				if (currentZone == zones[4] && mainCharacter.jackMet == true && mainCharacter.jackRiddle == true
						&& backpack.cigs == false) {
					zones[2].removeAction(Action.P1);
					zones[2].removeAction(Action.P2);
					zones[2].removeAction(Action.PRENDRE);
					zones[2].addAction(Action.P1);
					zones[2].addAction(Action.P2);
				}
				if (currentZone == zones[2] && mainCharacter.jackMet == true && mainCharacter.jackRiddle == true
						&& backpack.cigs == false) {
					zones[2].removeAction(Action.P1);
					zones[2].removeAction(Action.P2);
					zones[2].removeAction(Action.PRENDRE);
					zones[2].addAction(Action.P1);
					zones[2].addAction(Action.P2);
				}
				goTo("EST");
				reverse(readingCommand);
				break;

			}
			break;

		// Aller en Ouest
		case "O":
		case "OUEST":
			// Blocage de la sortie OUEST(Cours Interieur) avant la premiere rencontre avec
			// Old Joe
			if (currentZone == zones[2] && mainCharacter.joeMet == false) {
				gui.show("Je devrais d'abord aller voir Old Joe.\n");
				// Blocage de la sortie OUEST(Cafeteria) pendant la premiere et deuxieme
				// rencontres avec Old
				// Joe
			} else if (currentZone == zones[3] && mainCharacter.joeBlocked == true) {
				gui.show("Vous etes entrain de discuter avec Old Joe.\n");

			} else {
				// Blocage de la sortie OUEST par l'alarme
				if (currentZone == zones[2] && mainCharacter.alarmUp == true && backpack.key == true) {
					gui.show("Vous devez vous echappez, vous n'avez pas le temps pour se balader !\n");
					showLocation();
				} else {

					if (currentZone == zones[2] && mainCharacter.jackMet == true && mainCharacter.jackRiddle == true
							&& backpack.cigs == false) {
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
					if (currentZone == zones[7] && mainCharacter.alarmUp == true && backpack.key == true) {
						gui.show("La porte est fermée !\n");
						showLocation();
					} else if (currentZone == zones[8] && mainCharacter.alarmUp == true && backpack.key == false) {
						gui.show("La porte est fermée !\n");
						showLocation();
					} else {
						goTo("OUEST");
						reverse(readingCommand);
						break;
					}

				}
			}
			break;

		// Quitter le jeu
		case "Q":
		case "QUITTER":
			finish();
			break;

		// Revenir en arriere
		case "R":
		case "RETOUR":
			goTo(returnTo());
			break;

		// Executer la solution de base
		case "T":
		case "TERMINER":
			if (currentZone == zones[0]) {
				gui.enable(false);
				if (cigs.getRandomInt() == 1) {
					executeSolution(file1);
					break;
				}
				if (cigs.getRandomInt() == 2) {
					executeSolution(file2);
					break;
				}
				break;
			} else {
				gui.show("\nPour executer cette commande il faut etre dans la Cellule et au debut du jeu!");
				gui.show();
				break;
			}

		case "TSEC":
		case "TERMINER_SEC":
			gui.enable(false);
			if (currentZone == zones[0]) {
				if (cigs.getRandomInt() == 1) {
					executeSolution(secretFile1);
					break;
				}
				if (cigs.getRandomInt() == 2) {
					executeSolution(secretFile2);
					break;
				}
				break;
			} else {
				gui.show("\nPour executer cette commande il faut etre dans la Cellule et au debut du jeu!");
				gui.show();
				break;
			}

			// Regarder le contenu du sac
		case "SA":
		case "SAC":
			showBackpack();
			break;
		// S'approcher de Joe
		case "JOE":
			zones[3].removeAction(Action.JOE);
			if (currentZone == zones[3] && mainCharacter.x != 643) {
				// Si on a pas recuperé le secateur directement
				zones[3].addAction(Action.PARLER);
				if (currentZone == zones[3] && mainCharacter.x != 643 && mainCharacter.matouRiddle == false
						&& mainCharacter.jackRiddle == false && mainCharacter.joeRiddle == false
						&& backpack.shovel == true && mainCharacter.firstStageJoe == true
						&& mainCharacter.secondStageJoe == true && backpack.pliers == false) {
					zones[3].addAction(Action.PRENDRE);
				}
				gui.replaceMainCharacter(mainCharacter, 643, 234);
			}
			break;

		// S'approcher de Billy
		case "BILLY":
			zones[0].removeAction(Action.BILLY);
			if (currentZone == zones[0] && mainCharacter.x != 719) {
				zones[0].addAction(Action.PARLER);
				gui.replaceMainCharacter(mainCharacter, 719, 193);
			}
			break;

		// S'approcher de Jack
		case "JACK":
			zones[5].removeAction(Action.JACK);
			zones[5].removeAction(Action.COUPER);
			if (currentZone == zones[5] && mainCharacter.x != 639) {
				zones[5].addAction(Action.GRILLAGE);
				zones[5].addAction(Action.PARLER);
				if (currentZone == zones[5] && mainCharacter.jackRiddle == false && backpack.shovel == false) {
					zones[5].addAction(Action.PRENDRE);
				}
				gui.replaceMainCharacter(mainCharacter, 639, 345);

			}
			break;

		// S'approcher de Matou
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

		// Pot de fleurs 1
		case "P1":
			if (mainCharacter.jackMet == true && backpack.cigs == false && mainCharacter.jackRiddle == true) {
				zones[2].addAction(Action.PRENDRE);
				zones[2].addAction(Action.P2);
				zones[2].removeAction(Action.P1);
				if (currentZone == zones[2] && mainCharacter.x != 599) {
					gui.replaceMainCharacter(mainCharacter, 599, 288);
				}
				break;
			}
			// Pot de fleurs 2
		case "P2":
			if (mainCharacter.jackMet == true && backpack.cigs == false && mainCharacter.jackRiddle == true) {
				zones[2].addAction(Action.PRENDRE);
				zones[2].addAction(Action.P1);
				zones[2].removeAction(Action.P2);
				if (currentZone == zones[2] && mainCharacter.x != 1091) {
					gui.replaceMainCharacter(mainCharacter, 1091, 72);
				}
			}
			break;

		// Parler au personnages
		case "PARLER":
			// ---Parler à Billy---
			if (currentZone == zones[0] && mainCharacter.x == 719 && currentZone.containsActions(Action.PARLER)) {
				try {
					gui.enable(false);
					mainCharacter.billyBlocked = true;
					if (mainCharacter.joeMet == true) {
						billy.setDialogState(2);
					}
					executeDialog(billy);
					gui.show();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}
			// ---Parler à Joe---
			// Avant la debut de quete de Jack
			if (currentZone == zones[3] && mainCharacter.x == 643 && currentZone.containsActions(Action.PARLER)
					&& mainCharacter.jackRiddle == true && backpack.shovel == false) {
				try {
					gui.enable(false);
					mainCharacter.joeBlocked = true;
					executeDialog(joe);
					gui.show();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}
			// Apres la fin de quete de Jack
			// Premiere question(debut)
			if (currentZone == zones[3] && mainCharacter.x == 643 && currentZone.containsActions(Action.PARLER)
					&& mainCharacter.matouRiddle == false && mainCharacter.jackRiddle == false
					&& backpack.shovel == true && mainCharacter.firstStageJoe == false) {
				try {
					gui.enable(false);
					mainCharacter.joeBlocked = true;
					joe.setDialogState(2);
					executeDialog(joe);
					gui.show();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}

			}
			if (currentZone == zones[3] && mainCharacter.x == 643 && currentZone.containsActions(Action.PARLER)
					&& mainCharacter.matouRiddle == false && mainCharacter.jackRiddle == false
					&& backpack.shovel == false && mainCharacter.firstStageJoe == false) {
				try {
					gui.enable(false);
					mainCharacter.joeBlocked = true;
					executeDialog(joe);
					gui.show();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}
			// Premiere question(boucle sur la question)
			if (currentZone == zones[3] && mainCharacter.x == 643 && currentZone.containsActions(Action.PARLER)
					&& mainCharacter.matouRiddle == false && mainCharacter.jackRiddle == false
					&& backpack.shovel == true && mainCharacter.firstStageJoe == true
					&& mainCharacter.secondStageJoe == false) {
				try {
					gui.enable(false);
					executeDialog(joe);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				gui.show();
			}
			// Deuxieme question
			if (currentZone == zones[3] && mainCharacter.x == 643 && currentZone.containsActions(Action.PARLER)
					&& mainCharacter.matouRiddle == false && mainCharacter.jackRiddle == false
					&& backpack.shovel == true && mainCharacter.firstStageJoe == true
					&& mainCharacter.secondStageJoe == true) {
				try {
					gui.enable(false);
					executeDialog(joe);
					if (joe.getDialogState() == 10) {
						mainCharacter.joeBlocked = true;
					}
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				gui.show();
			}
			// ---Parler à Matou---
			// Quand il bloque le passage

			if (currentZone == zones[4] && mainCharacter.x == 755 && currentZone.containsActions(Action.PARLER)) {
				try {
					gui.enable(false);
					executeDialog(matou);
					gui.show();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}
			// Quand il ne bloque plus le passage
			if (currentZone == zones[4] && mainCharacter.x == 682 && currentZone.containsActions(Action.PARLER)) {
				try {
					gui.enable(false);
					mainCharacter.matouBlocked = true;
					executeDialog(matou);
					gui.show();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}

			// ---Parler à Jack---

			// Premiere fois
			if (currentZone == zones[5] && mainCharacter.x == 639 && currentZone.containsActions(Action.PARLER)
					&& mainCharacter.jackMet == false) {
				try {
					gui.enable(false);
					mainCharacter.jackBlocked = true;
					executeDialog(jack);
					gui.show();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				// Deuxieme fois sans le paquet de cigarettes
			} else if (currentZone == zones[5] && mainCharacter.x == 639 && currentZone.containsActions(Action.PARLER)
					&& mainCharacter.jackMet == true && backpack.cigs == false) {
				try {
					gui.enable(false);
					mainCharacter.jackBlocked = true;
					executeDialog(jack);
					gui.show();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}

			}

			// Obtention de la pelle
			if (currentZone == zones[5] && mainCharacter.x == 639 && currentZone.containsActions(Action.PARLER)
					&& mainCharacter.jackMet == true && backpack.cigs == true) {
				backpack.removeElement(cigs);
				backpack.cigs = false;
				mainCharacter.jackBlocked = true;
				jack.setDialogState(2);
				try {
					gui.enable(false);
					executeDialog(jack);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				gui.show();
			}

			break;

		case "PRENDRE":

			// Prendre les cigarettes

			// Premier spawn possible
			if (currentZone == zones[2] && cigs.randomNumber == 1 && mainCharacter.x == 1091 && backpack.cigs == false
					&& mainCharacter.jackMet == true && currentZone.containsActions(Action.PRENDRE)) {
				zones[2].removeAction(Action.P1);
				zones[2].removeAction(Action.P2);
				zones[2].removeAction(Action.PRENDRE);
				backpack.addElement(cigs);
				backpack.cigs = true;
				gui.replaceMainCharacter(mainCharacter, 1091, 72);
				gui.show("Vous venez de récuperer un paquet de cigarettes!\n");

				break;

				// Deuxieme spawn possible
			} else if (currentZone == zones[2] && cigs.randomNumber == 2 && mainCharacter.x == 599
					&& backpack.cigs == false && mainCharacter.jackMet == true
					&& currentZone.containsActions(Action.PRENDRE)) {
				zones[2].removeAction(Action.P1);
				zones[2].removeAction(Action.P2);
				zones[2].removeAction(Action.PRENDRE);
				backpack.addElement(cigs);
				backpack.cigs = true;
				gui.replaceMainCharacter(mainCharacter, 599, 288);
				gui.show("Vous venez de récuperer un paquet de cigarettes!\n");

				break;

			} else if (currentZone == zones[2] && cigs.randomNumber == 1 && mainCharacter.x == 599
					&& mainCharacter.jackMet == true && currentZone.containsActions(Action.PRENDRE)) {
				gui.show("Il n'y a rien à ramasser !");

				break;
			}

			else if (currentZone == zones[2] && cigs.randomNumber == 2 && mainCharacter.x == 1091
					&& mainCharacter.jackMet == true && currentZone.containsActions(Action.PRENDRE)) {
				gui.show("Il n'y a rien à ramasser !");

				break;
			}

			// Prendre la pelle
			if (currentZone == zones[5] && backpack.shovel == false && mainCharacter.jackRiddle == false
					&& mainCharacter.jackMet == true && currentZone.containsActions(Action.PRENDRE)) {
				zones[5].removeAction(Action.PRENDRE);
				backpack.addElement(shovel);
				backpack.shovel = true;
				initialize();
				gui.show("Vous venez de récuperer une pelle!\n");
				showLocation();
				break;
			}

			// Prendre le secateur
			if (currentZone == zones[3] && backpack.shovel == true && backpack.pliers == false
					&& mainCharacter.joeRiddle == false && currentZone.containsActions(Action.PRENDRE)) {
				zones[3].removeAction(Action.PRENDRE);
				backpack.addElement(pliers);
				backpack.pliers = true;
				initialize();
				gui.show("Vous venez de récuperer un secateur!\n");
				showLocation();
				break;
			}

			// Prendre le clé
			if (currentZone == zones[6] && backpack.shovel == true && backpack.pliers == true
					&& mainCharacter.alarmUp == true && currentZone.containsActions(Action.PRENDRE)) {
				zones[6].removeAction(Action.PRENDRE);
				zones[6].addAction(Action.SAUTER);
				backpack.addElement(key);
				backpack.key = true;
				initialize();
				gui.show("Vous venez de récuperer un clé!\n");
				showLocation();
				break;
			}
			break;

		// Reponses "REPUGNANTE" et "NEUVE" pour Matou
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

		// Reponse "JOLIE" pour Matou
		case "JOLIE":
			if (currentZone == zones[4] && mainCharacter.matouRiddle == true && mainCharacter.x == 755) {
				try {
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

		// Reponses "TROIS" et "QUATRE" pour Joe
		case "TROIS":
		case "QUATRE":
			if (currentZone == zones[3] && mainCharacter.x == 643 && currentZone.containsAnswers(Answer.TROIS)
					&& currentZone.containsAnswers(Answer.QUATRE) && mainCharacter.matouRiddle == false
					&& mainCharacter.jackRiddle == false && backpack.shovel == true
					&& mainCharacter.firstStageJoe == true && mainCharacter.secondStageJoe == false) {
				if (mainCharacter.joePenalty == 0) {
					try {
						noShowLocation = true;
						mainCharacter.joePenalty++;
						joe.setDialogState(4);
						executeDialog(joe);
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
				} else if (mainCharacter.joePenalty == 1) {
					try {
						mainCharacter.joePenalty++;
						joe.setDialogState(5);
						executeDialog(joe);
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
				} else if (mainCharacter.joePenalty == 2) {
					try {
						joe.setDialogState(8);
						executeDialog(joe);
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
				}
			}
			break;

		// Reponse "CINQ" pour Joe
		case "CINQ":
			if (currentZone == zones[3] && mainCharacter.x == 643 && currentZone.containsAnswers(Answer.CINQ)
					&& mainCharacter.matouRiddle == false && mainCharacter.jackRiddle == false
					&& backpack.shovel == true && mainCharacter.firstStageJoe == true
					&& mainCharacter.secondStageJoe == false) {
				try {
					mainCharacter.secondStageJoe = true;
					joe.setDialogState(6);
					executeDialog(joe);
					break;
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}
			break;

		case "UN":
			if (currentZone == zones[3] && mainCharacter.x == 643 && currentZone.containsAnswers(Answer.UN)
					&& mainCharacter.matouRiddle == false && mainCharacter.jackRiddle == false
					&& backpack.shovel == true && mainCharacter.firstStageJoe == true
					&& mainCharacter.secondStageJoe == true) {
				if (mainCharacter.joePenalty == 0) {
					try {
						noShowLocation = true;
						mainCharacter.joePenalty++;
						joe.setDialogState(4);
						executeDialog(joe);
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
				} else if (mainCharacter.joePenalty == 1) {
					try {
						mainCharacter.joePenalty++;
						joe.setDialogState(5);
						executeDialog(joe);
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
				} else if (mainCharacter.joePenalty == 2) {
					try {
						joe.setDialogState(8);
						executeDialog(joe);
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
				}
			}
			break;

		case "DEUX":
			if (currentZone == zones[3] && mainCharacter.x == 643 && currentZone.containsAnswers(Answer.UN)
					&& mainCharacter.matouRiddle == false && mainCharacter.jackRiddle == false
					&& backpack.shovel == true && mainCharacter.firstStageJoe == true
					&& mainCharacter.secondStageJoe == true) {
				try {
					joe.setDialogState(9);
					executeDialog(joe);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}

			break;

		case "GRILLAGE":
			// Blocage de l'action Grillage pendant le dialogue avec Jack
			if (currentZone == zones[5] && mainCharacter.jackBlocked == true) {
				gui.show("Vous etes entrain de discuter avec Jack!\n");

				break;
			}
			zones[5].removeAction(Action.PRENDRE);
			zones[5].removeAction(Action.PARLER);
			zones[5].removeAction(Action.GRILLAGE);
			if (currentZone == zones[5] && mainCharacter.x != 766) {
				zones[5].addAction(Action.COUPER);
				zones[5].addAction(Action.JACK);
				gui.replaceMainCharacter(mainCharacter, 766, 355);
			}

			break;

		case "COUPER":
			if (currentZone == zones[5] && mainCharacter.x == 766 && backpack.pliers == false
					&& currentZone.containsActions(Action.COUPER)) {
				gui.show("Vous n'etes pas encore pret pour realiser votre plan...\n");
			} else if (currentZone == zones[5] && mainCharacter.x == 766 && mainCharacter.matouRiddle == false
					&& mainCharacter.joeRiddle == false && mainCharacter.jackRiddle == false && backpack.shovel == true
					&& backpack.pliers == true && currentZone.containsActions(Action.COUPER)) {
				zones[5].removeAction(Action.JACK);
				zones[5].removeAction(Action.COUPER);
				mainCharacter.alarmUp = true;
				gui.show("Vous coupez le grillage et passez à travers !\n");
				gui.show("🚨Une alarme vient d'etre lancer ! Ne perdez plus de temps !🚨\n");
				gui.show("🚨Vous avez, tout juste assez de temps pour creuser deux trous !🚨\n");
				gui.show("🚨Depechez-vous !!!🚨\n");
				teleport(6);
			}

			break;

		case "T1":
			// La limite de 2 n'est pas depassé
			if (currentZone == zones[6] && mainCharacter.alarmUp == true && mainCharacter.holeOneDone == false
					&& mainCharacter.holeLimit < 2 && currentZone.containsActions(Action.T1)
					&& backpack.shovel == true) {

				// Clé avec le passage
				// Encore un essaie restant
				if (currentZone == zones[6] && mainCharacter.alarmUp == true && cigs.getRandomInt() == 1
						&& mainCharacter.holeOneDone == false && mainCharacter.holeLimit == 0) {
					mainCharacter.holeOneDone = true;
					mainCharacter.holeLimit++;
					zones[6].addAction(Action.PRENDRE);
					zones[6].removeAction(Action.T1);
					key.setCoordinates(664, 367);
					gui.show("Vous avez trouvé une clé! Mais que peut elle donc bien ouvrir ?\n");
					gui.show("*Vous vous rappelez de ce qu'a dit Old Joe...*\n");
					gui.show("Allez vous prendre la clé et sauter dans ce trou ?\n");
					gui.show("Ou allez vous continuer de creuser ?\n");
					gui.replaceMainCharacter(mainCharacter, 692, 311);

				}
				// Plus d'essaie restants
				if (currentZone == zones[6] && mainCharacter.alarmUp == true && cigs.getRandomInt() == 1
						&& mainCharacter.holeOneDone == false && mainCharacter.holeLimit == 1) {
					mainCharacter.holeOneDone = true;
					zones[6].addAction(Action.PRENDRE);
					zones[6].removeAction(Action.T1);
					zones[6].removeAction(Action.T2);
					zones[6].removeAction(Action.T3);
					zones[6].removeAction(Action.T4);
					key.setCoordinates(664, 367);
					gui.show("Vous avez trouvé une clé! Mais que peut elle donc bien ouvrir?\n");
					gui.show("*Vous vous rappelez de ce qu'a dit Old Joe...*\n");
					gui.show("Vous n'avez plus le temps, vous devez sauter dans le trou...\n");
					gui.replaceMainCharacter(mainCharacter, 692, 311);

				}
				// Pas de sortie
				if (currentZone == zones[6] && mainCharacter.alarmUp == true && cigs.getRandomInt() == 2
						&& mainCharacter.holeOneDone == false) {
					mainCharacter.holeOneDone = true;
					mainCharacter.holeLimit++;
					zones[6].removeAction(Action.T1);
					gui.show(
							"Il n'y a rien à cet emplacement, il ne vous reste assez de temps que pour creuser un seul trou!\n");
					gui.replaceMainCharacter(mainCharacter, 692, 311);

				}
				// La limite de 2 est atteinte
				if (currentZone == zones[6] && mainCharacter.alarmUp == true && mainCharacter.holeOneDone == true
						&& mainCharacter.holeLimit == 2) {
					GUI.musicGame.stopMusic();
					GUI.disposeGUIFrame();
					@SuppressWarnings("unused")
					End end = new End("BadEnding");

				}

			}
			break;

		case "T2":

			// La limite de 2 n'est pas depassé
			if (currentZone == zones[6] && mainCharacter.alarmUp == true && mainCharacter.holeTwoDone == false
					&& mainCharacter.holeLimit < 2 && currentZone.containsActions(Action.T2)
					&& backpack.shovel == true) {

				// Sortie directe
				if (currentZone == zones[6] && mainCharacter.alarmUp == true && cigs.getRandomInt() == 1
						&& mainCharacter.holeTwoDone == false) {
					mainCharacter.holeTwoDone = true;
					zones[6].removeAction(Action.T1);
					zones[6].removeAction(Action.T2);
					zones[6].removeAction(Action.T3);
					zones[6].removeAction(Action.T4);
					zones[6].removeAction(Action.PRENDRE);
					zones[6].removeAction(Action.SAUTER);
					zones[6].addAction(Action.FUIR);
					gui.show("Vous êtes enfin libre! Il ne vous reste plus qu'à fuir, votre famille vous attends.\n");
					gui.show(
							"Vous jetez un dernier regards vers la prison en pensant aux rencontre que vous avez faites...\n");
					gui.show("...et continuez votre chemin sans une once de regret.\n");
					gui.replaceMainCharacter(mainCharacter, 767, 531);

				}
				// Pas de sortie
				if (currentZone == zones[6] && mainCharacter.alarmUp == true && cigs.getRandomInt() == 2
						&& mainCharacter.holeTwoDone == false) {
					mainCharacter.holeTwoDone = true;
					mainCharacter.holeLimit++;
					zones[6].removeAction(Action.T2);
					gui.show(
							"Il n'y a rien à cet emplacement, il ne vous reste assez de temps que pour creuser un seul trou!\n");
					gui.replaceMainCharacter(mainCharacter, 744, 311);

				}
				// La limite de 2 est depassé
				if (currentZone == zones[6] && mainCharacter.alarmUp == true && mainCharacter.holeTwoDone == true
						&& mainCharacter.holeLimit == 2) {
					GUI.musicGame.stopMusic();
					GUI.disposeGUIFrame();
					@SuppressWarnings("unused")
					End end = new End("BadEnding");
				}

			}
			break;

		case "T3":
			// La limite de 2 n'est pas depassé
			if (currentZone == zones[6] && mainCharacter.alarmUp == true && mainCharacter.holeThreeDone == false
					&& mainCharacter.holeLimit < 2 && currentZone.containsActions(Action.T3)
					&& backpack.shovel == true) {

				// Clé avec le passage
				// Encore un essaie restant
				if (currentZone == zones[6] && mainCharacter.alarmUp == true && cigs.getRandomInt() == 2
						&& mainCharacter.holeThreeDone == false && mainCharacter.holeLimit == 0) {
					mainCharacter.holeThreeDone = true;
					mainCharacter.holeLimit++;
					zones[6].addAction(Action.PRENDRE);
					zones[6].removeAction(Action.T3);
					key.setCoordinates(840, 356);
					gui.show("Vous avez trouvé une clé! Mais que peut elle donc bien ouvrir ?\n");
					gui.show("*Vous vous rappelez de ce qu'a dit Old Joe...*\n");
					gui.show("Allez vous prendre la clé et sauter dans ce trou?\n");
					gui.show("Ou allez vous continuer de creuser ?\n");
					gui.replaceMainCharacter(mainCharacter, 796, 311);

				}
				// Plus d'essaie restantss
				if (currentZone == zones[6] && mainCharacter.alarmUp == true && cigs.getRandomInt() == 2
						&& mainCharacter.holeThreeDone == false && mainCharacter.holeLimit == 1) {
					mainCharacter.holeThreeDone = true;
					zones[6].addAction(Action.PRENDRE);
					zones[6].removeAction(Action.T1);
					zones[6].removeAction(Action.T2);
					zones[6].removeAction(Action.T3);
					zones[6].removeAction(Action.T4);
					key.setCoordinates(840, 356);
					gui.show("Vous trouvez une clé ! Mais que ouvre-t-elle?\n");
					gui.show("Vous vous rappelez de ce qu'il a dit Old Joe...\n");
					gui.show("Vous n'aves plus de temps, vous devez sauter dans le trou...\n");
					gui.replaceMainCharacter(mainCharacter, 796, 311);

				}
				// Pas de sortie
				if (currentZone == zones[6] && mainCharacter.alarmUp == true && cigs.getRandomInt() == 1

						&& mainCharacter.holeThreeDone == false) {
					mainCharacter.holeThreeDone = true;
					mainCharacter.holeLimit++;
					zones[6].removeAction(Action.T3);
					gui.show(
							"Il n'y a rien à cet emplacement, il ne vous reste assez de temps que pour creuser un seul trou!\n");
					gui.replaceMainCharacter(mainCharacter, 796, 311);

				}
				// La limite de 2 est depassé
				if (currentZone == zones[6] && mainCharacter.alarmUp == true && mainCharacter.holeThreeDone == true
						&& mainCharacter.holeLimit == 2) {
					GUI.musicGame.stopMusic();
					GUI.disposeGUIFrame();
					@SuppressWarnings("unused")
					End end = new End("BadEnding");

				}
				break;

			}

		case "T4":
			// La limite de 2 n'est pas depassé
			if (currentZone == zones[6] && mainCharacter.alarmUp == true && mainCharacter.holeFourDone == false
					&& mainCharacter.holeLimit < 2 && currentZone.containsActions(Action.T4)
					&& backpack.shovel == true) {

				// Sortie directe
				if (currentZone == zones[6] && mainCharacter.alarmUp == true && cigs.getRandomInt() == 2
						&& mainCharacter.holeFourDone == false) {
					mainCharacter.holeFourDone = true;
					zones[6].removeAction(Action.T1);
					zones[6].removeAction(Action.T2);
					zones[6].removeAction(Action.T3);
					zones[6].removeAction(Action.T4);
					zones[6].removeAction(Action.PRENDRE);
					zones[6].removeAction(Action.SAUTER);
					zones[6].addAction(Action.FUIR);
					gui.show("Vous êtes enfin libre! Il ne vous reste plus qu'à fuir, votre famille vous attends.\n");
					gui.show(
							"Vous jetez un dernier regards vers la prison en pensant aux rencontre que vous avez faites...\n");
					gui.show("...et continuez votre chemin sans une once de regret.\n");
					gui.replaceMainCharacter(mainCharacter, 767, 531);
				}

				// Pas de sortie
				if (currentZone == zones[6] && mainCharacter.alarmUp == true && cigs.getRandomInt() == 1
						&& mainCharacter.holeFourDone == false) {
					mainCharacter.holeFourDone = true;
					mainCharacter.holeLimit++;
					zones[6].removeAction(Action.T4);
					gui.show(
							"Il n'y a rien à cet emplacement, il ne vous reste assez de temps que pour creuser un seul trou!\n");
					gui.replaceMainCharacter(mainCharacter, 854, 311);

				}
				// La limite de 2 est depassé
				if (currentZone == zones[6] && mainCharacter.alarmUp == true && mainCharacter.holeFourDone == true
						&& mainCharacter.holeLimit == 2) {
					GUI.musicGame.stopMusic();
					GUI.disposeGUIFrame();
					@SuppressWarnings("unused")
					End end = new End("BadEnding");

				}

			}
			break;

		case "FUIR":
			if (currentZone == zones[6] && mainCharacter.alarmUp == true && currentZone.containsActions(Action.FUIR)) {
				GUI.musicGame.stopMusic();
				GUI.disposeGUIFrame();
				@SuppressWarnings("unused")
				End end = new End("GoodEnding");
			}
			break;

		case "SAUTER":
			if (currentZone == zones[6] && mainCharacter.alarmUp == true && currentZone.containsActions(Action.SAUTER)
					&& backpack.key == true) {
				zones[2].removeAction(Action.SAUTER);
				initialize();
				teleport(2);
				gui.show("Vous sautez dans le tunnel et apres quelques coups de pelles...\n");
				gui.show("Vous vous retrouvez dans la cours interieur !!! Joe vous a trahi..\n");
				gui.show("Paniqué, vous voyez une porte au Nord_Est!!!\n");
				gui.show("L'énigme.. La clé.. La porte.. Vous courez vers la porte avec espoir.\n");
				showLocation();
			}
			if (currentZone == zones[8] && mainCharacter.alarmUp == true && currentZone.containsActions(Action.SAUTER)
					&& backpack.key == false) {
				GUI.musicGame.stopMusic();
				GUI.disposeGUIFrame();
				@SuppressWarnings("unused")
				End end = new End("GoodEnding");
			}
			break;

		case "BUREAU":
			if (currentZone == zones[8] && mainCharacter.alarmUp == true && currentZone.containsActions(Action.BUREAU)
					&& backpack.key == true) {
				zones[8].removeAction(Action.BUREAU);
				zones[8].addAction(Action.SAUTER);
				backpack.removeElement(key);
				backpack.key = false;
				initialize();
				gui.show("En ouvrant la porte, vous cassez la clé !\n");
				gui.show("Vous la jeté sur un banc et vous remarquez.. Une trappe !!!!\n");
				gui.show("Qui sait ce qui vous attends en bas ? Il n'y a qu'un seul moyen de le savoir..\n");
				gui.show("Sans regarder derriere vous, vous ouvrez la trappe et sautez.\n");
				gui.show("Qui sait, peut-etre n'est-ce que le debut d'une nouvelle aventure?\n");
				gui.replaceMainCharacter(mainCharacter, 951, 229);
			}
			break;

		}

	}

	/**
	 * Une methodes qui permet d'afficher toutes les commandes possibles.
	 */
	private void showHelp() {
		gui.show("Etes-vous perdu ?");
		gui.show();
		gui.show("Les commandes autorisees sont :");
		gui.show();
		gui.show(Command.allDescriptions().toString());
		gui.show();
	}

	/**
	 * Une methode qui permet d'aller vers une nouvelle zone en focntion de
	 * {@code direction}.
	 * 
	 * @param direction La direction dans laquelle on vous aller.
	 */
	private void goTo(String direction) {
		Zone newZone = currentZone.getExit(direction);
		if (newZone == null) {
			gui.show("Pas de sortie " + direction);
			gui.show();
			possibleExit = false;
		} else {
			// Supprime l'ancien personnage
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
			initialize();// doit etre ici pour ne pas que les éléments soit au dernier plan
			gui.showImage(currentZone.nameImage());

			possibleExit = true;

		}
	}

	/**
	 * Une methode qui permet de se teleporter a partir de la zone "Mur" vers la
	 * zone "Cours Interieur", necessaire pour la fin secrete. Permet aussi de se
	 * teleporter vers la zone "Mur" a partir de la zone "Cours de Sport". Le tout
	 * en fonction de {@code z}.
	 * 
	 * @param z Un nombre, doit etre soit 2 soit 6 pour permettre la teleportation.
	 */
	private void teleport(int z) {

		// Supprime l'ancien personnage
		currentZone = zones[z];
		if (!currentZone.answers.isEmpty()) {
			gui.show(currentZone.longDescription());
			gui.show();
		}
		if (!currentZone.answers.isEmpty()) {
			gui.show(currentZone.longDescriptionAnswer());
			gui.show();
		}
		if (!currentZone.actions.isEmpty()) {
			gui.show(currentZone.longDescriptionAction());
			gui.show();
		}
		// Créee et affiche le personnage dans la Cours Interieur
		if (currentZone == zones[2]) {
			mainCharacter.setCoordinates(995, 129);
			initialize();// Doit etre ici pour ne pas que les éléments soit au dernier plan
			gui.showImage(currentZone.nameImage());
		} else if (currentZone == zones[6]) {
			mainCharacter.setCoordinates(currentZone.xSpawn, currentZone.ySpawn);
			initialize();
			gui.showImage(currentZone.nameImage());
		}
	}

	/**
	 * Methode permettant d'afficher les differents elements sur la carte
	 * (KeyElement et Npc).
	 */
	public void initialize() {
		gui.image.removeAll();// changement ici
		gui.showElement(mainCharacter);// et ici
		if (currentZone == zones[4] && mainCharacter.matouRiddle == true) {
			gui.showElement(matou);
			gui.show();
		}
		if (currentZone == zones[4] && mainCharacter.matouRiddle == false) {
			matou.setCoordinates(679, 520);
			gui.showElement(matou);

		}
		if (currentZone == zones[2] && backpack.cigs == false && mainCharacter.jackMet == true
				&& mainCharacter.jackRiddle == true) {
			gui.showElement(cigs);
		}
		if (currentZone == zones[0]) {
			gui.showElement(billy);
		}
		if (currentZone == zones[3] && backpack.pliers == false) {
			gui.showElement(pliers);
			gui.showElement(joe);

		} else if (currentZone == zones[3] && backpack.pliers == true) {
			gui.showElement(joe);

		}
		if (currentZone == zones[5] && backpack.shovel == false) {
			gui.showElement(shovel);
			gui.showElement(jack);
		} else if (currentZone == zones[5] && backpack.shovel == true) {
			gui.showElement(jack);
		}
		// Trou 1
		if (currentZone == zones[6] && mainCharacter.alarmUp == true && mainCharacter.holeOneDone == true
				&& cigs.getRandomInt() == 1 && backpack.key == false) {
			gui.showElement(key);
			gui.showElement(holeGround1);
		} else if (currentZone == zones[6] && mainCharacter.alarmUp == true && mainCharacter.holeOneDone == true
				&& cigs.getRandomInt() == 1 && backpack.key == true) {
			gui.showElement(holeGround1);

		} else if (currentZone == zones[6] && mainCharacter.alarmUp == true && mainCharacter.holeOneDone == true
				&& cigs.getRandomInt() == 2) {
			gui.showElement(holeGround1);
		}
		// Trou 2
		if (currentZone == zones[6] && mainCharacter.alarmUp == true && mainCharacter.holeTwoDone == true) {
			gui.showElement(holeGround2);
		}
		// Trou 3
		if (currentZone == zones[6] && mainCharacter.alarmUp == true && mainCharacter.holeThreeDone == true
				&& cigs.getRandomInt() == 2 && backpack.key == false) {
			gui.showElement(key);
			gui.showElement(holeGround3);

		} else if (currentZone == zones[6] && mainCharacter.alarmUp == true && mainCharacter.holeThreeDone == true
				&& cigs.getRandomInt() == 2 && backpack.key == true) {
			gui.showElement(holeGround3);
		} else if (currentZone == zones[6] && mainCharacter.alarmUp == true && mainCharacter.holeThreeDone == true
				&& cigs.getRandomInt() == 1) {
			gui.showElement(holeGround3);
		}

		// Trou 4
		if (currentZone == zones[6] && mainCharacter.alarmUp == true && mainCharacter.holeFourDone == true) {
			gui.showElement(holeGround4);
		}

		// Trou Cours Interieur
		if (currentZone == zones[2] && backpack.key == true && mainCharacter.alarmUp == true) {
			gui.showElement(holeFloor);
		}
		// Trappe Bureau et Clé Cassée
		if (currentZone == zones[8] && mainCharacter.alarmUp == true && backpack.key == false) {
			key.setCoordinates(839, 292);
			gui.showElement(key);
			gui.showElement(trapDoor);
		}

	}

	/**
	 * Une methode qui affiche le contenu du sac.
	 */
	private void showBackpack() {
		String msg = this.backpack.toString();
		gui.show(msg);
		gui.show();
		showLocation();

	}

	/**
	 * Une methode qui permet d'executer un fichier texte avec la suite des
	 * commander pour terminer la partie(fin normale et fin secrete).
	 * 
	 * @param solution Le fichier a executer.
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

					switch (currentLine) {
					case "BILLY":
						executeCommand("BILLY");
						break;
					case "JOE":
						executeCommand("JOE");
						mainCharacter.joeMet = true;
						if (currentZone == zones[3] && backpack.shovel == true) {
							gui.show("Vous avez repondu correctement à Joe !\n");
						}
						break;
					case "JACK":
						executeCommand("JACK");
						mainCharacter.jackMet = true;

						break;
					case "MATOU":
						executeCommand("MATOU");
						break;
					case "P1":
						executeCommand("P1");
						break;
					case "P2":
						executeCommand("P2");
						break;
					case "PRENDRE":
						executeCommand("PRENDRE");
						if (currentZone == zones[5]) {
							mainCharacter.jackRiddle = false;
							backpack.addElement(shovel);
							backpack.shovel = true;
							initialize();
							gui.show("Vous venez de récuperer une pelle !\n");
							break;
						}
						if (currentZone == zones[3]) {
							mainCharacter.joeRiddle = false;
							backpack.addElement(pliers);
							backpack.pliers = true;
							initialize();
							gui.show("Vous venez de récuperer un secateur !\n");
							break;
						}

						break;
					case "PARLER":
						gui.show("Vous venez de discuter avec lui.\n");
						break;
					case "JOLIE":
						gui.show("Vous venez de repondre correctement à Matou.\n");
						mainCharacter.matouRiddle = false;
						initialize();
						break;
					case "GRILLAGE":
						executeCommand("GRILLAGE");
					case "COUPER":
						executeCommand("COUPER");
						break;
					case "T1":
						executeCommand("T1");
						break;
					case "T2":
						executeCommand("T2");
						break;
					case "T3":
						executeCommand("T3");
						break;
					case "T4":
						executeCommand("T4");
						break;
					case "SAUTER":
						executeCommand("SAUTER");
						if (currentZone == zones[8]) {
							GUI.musicGame.stopMusic();
							GUI.disposeGUIFrame();
							@SuppressWarnings("unused")
							End end = new End("GoodEnding");
						}
						break;
					case "FUIR":
						executeCommand("FUIR");
						break;
					case "BUREAU":
						backpack.removeElement(key);
						backpack.key = false;
						initialize();
						gui.replaceMainCharacter(mainCharacter, 951, 229);
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
		if (mainCharacter.lastZones.size() > 0 && mainCharacter.alarmUp == false) {
			String lastCommand = mainCharacter.lastZones.get(mainCharacter.lastZones.size() - 1);
			mainCharacter.lastZones.remove(mainCharacter.lastZones.size() - 1);

			return lastCommand;
		}
		return "\n Il n'y a plus de retour possible.";

	}

	/**
	 * Une methode qui permet d'executer un dialogue du Npc.
	 * 
	 * @param npc Npc qui doit parler.
	 * @throws FileNotFoundException Si le Npc n'existe pas.
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
					gui.show(npc.dialogJack());
				}
				timerCounter++;
				npc.dialogCounter++;
				if (timerCounter > npc.getNpcDialogs().size()) {
					gui.enable(true);
					timerCounter = 0;
					npc.dialogCounter = 0;
					gui.show();

					// ---Si le dialogue de Billy vient d'etre executer---
					if (npc.getName().equals("Billy")) {
						mainCharacter.billyMet = true;
						mainCharacter.billyBlocked = false;
						billy.setDialogState(1);
					}
					// ---Si le dialogue de Joe vient d'etre executer---
					if (npc.getName().equals("Joe")) {
						if (joe.getDialogState() == 2) {
							mainCharacter.firstStageJoe = true;
							joe.setDialogState(3);

							// Poser sa premiere question
						} else if (joe.getDialogState() == 3) {
							noShowLocation = false;
							zones[3].addAnswer(Answer.TROIS);
							zones[3].addAnswer(Answer.QUATRE);
							zones[3].addAnswer(Answer.CINQ);

							// Erreurs pendant la premiere question
						} else if (joe.getDialogState() == 4 && mainCharacter.firstStageJoe == true) {
							noShowLocation = true;
							// Erreur question deux
							if (joe.getDialogState() == 4 && mainCharacter.firstStageJoe == true
									&& mainCharacter.secondStageJoe == true) {
								try {

									joe.setDialogState(7);
									executeDialog(joe);
								} catch (FileNotFoundException e) {
									e.printStackTrace();
								}
								// Erreur question une
							} else {
								try {
									joe.setDialogState(3);
									executeDialog(joe);
								} catch (FileNotFoundException e) {
									e.printStackTrace();
								}

							}
							// Erreurs pendant la premiere et la deuxieme questions
						} else if (joe.getDialogState() == 5 && mainCharacter.firstStageJoe == true) {
							noShowLocation = true;
							// Erreur pendant la deuxieme question
							if (joe.getDialogState() == 5 && mainCharacter.firstStageJoe == true
									&& mainCharacter.secondStageJoe == true) {
								try {

									joe.setDialogState(7);
									executeDialog(joe);
								} catch (FileNotFoundException e) {
									e.printStackTrace();
								}
								// Erreur pendant la premiere question
							} else {
								try {

									joe.setDialogState(3);
									executeDialog(joe);
								} catch (FileNotFoundException e) {
									e.printStackTrace();
								}

							}
							// Felicitation pour la reponse correcte, prochaine question
						} else if (joe.getDialogState() == 6) {

							try {
								zones[3].removeAnswer(Answer.CINQ);
								zones[3].removeAnswer(Answer.TROIS);
								zones[3].removeAnswer(Answer.QUATRE);
								noShowLocation = true;
								joe.setDialogState(7);
								executeDialog(joe);
							} catch (FileNotFoundException e) {
								e.printStackTrace();
							}
							// Deuxieme question
						} else if (joe.getDialogState() == 7) {
							noShowLocation = false;
							zones[3].addAnswer(Answer.UN);
							zones[3].addAnswer(Answer.DEUX);

							// 3 reponses incorrecte
						} else if (joe.getDialogState() == 8) {
							GUI.musicGame.stopMusic();
							GUI.disposeGUIFrame();
							@SuppressWarnings("unused")
							End end = new End("BadEnding");
							// La fin de quete de Joe
						} else if (joe.getDialogState() == 9) {
							mainCharacter.joeRiddle = false;
							mainCharacter.joeBlocked = false;
							zones[3].removeAnswer(Answer.UN);
							zones[3].removeAnswer(Answer.DEUX);
							zones[3].addAction(Action.PRENDRE);
							joe.setDialogState(10);
						} else if (joe.getDialogState() == 0) {
							mainCharacter.joeMet = true;
							mainCharacter.joeBlocked = false;
							joe.setDialogState(1);
						} else if (joe.getDialogState() == 1) {
							mainCharacter.joeBlocked = false;
						} else if (joe.getDialogState() == 10) {
							mainCharacter.joeBlocked = false;
						}

					}
					// ---Si le dialogue de Matou vient d'etre executer---
					if (npc.getName().equals("Matou")) {
						// Si on choici "Neuve","Repugnante" dans le dialog avec Matou
						if (matou.getDialogState() == 2) {
							GUI.musicGame.stopMusic();
							GUI.disposeGUIFrame();
							@SuppressWarnings("unused")
							End end = new End("BadEnding");
						} else if (matou.getDialogState() == 3) {
							matou.setDialogState(4);
							mainCharacter.matouRiddle = false;
							initialize();
							zones[4].removeAnswer(Answer.JOLIE);
							zones[4].removeAnswer(Answer.NEUVE);
							zones[4].removeAnswer(Answer.REPUGNANTE);
							zones[4].removeAction(Action.PARLER);
							zones[4].addAction(Action.MATOU);

						} else if (matou.getDialogState() == 4) {
							mainCharacter.matouBlocked = false;
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
					// ---Si le dialogue de Jack vient d'etre executer---
					if (npc.getName().equals("Jack")) {
						// Fin de quete de Jack
						if (jack.getDialogState() == 2) {
							mainCharacter.jackRiddle = false;
							mainCharacter.jackBlocked = false;
							zones[5].addAction(Action.PRENDRE);
							jack.setDialogState(3);
						} else if (jack.getDialogState() == 0) {
							mainCharacter.jackMet = true;
							mainCharacter.jackBlocked = false;
							jack.setDialogState(1);
						} else if (jack.getDialogState() == 1) {
							mainCharacter.jackBlocked = false;
						} else if (jack.getDialogState() == 3) {
							mainCharacter.jackBlocked = false;
						}

					}
					if (noShowLocation != true) {
						showLocation();

					}
					this.cancel();

				}

			}
		};

		Timer timer = new Timer("Timer");
		timer.schedule(task, 1000, 3000);

	}

	/**
	 * Une methode qui permet de dire au-revoir au joueur et desactiver le GUI.
	 */
	private void finish() {
		gui.show("Au revoir...");
		gui.enable(false);
	}
}
