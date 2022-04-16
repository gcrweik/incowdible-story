package game;

import java.util.ArrayList;
import java.util.List;

/**
 * Une enumeration qui contient toutes les commandes du jeu.
 * 
 * @author mohamed_hanouche
 * @author roman_tyzio
 * @version 1.0.0
 *
 */
public enum Command {
	/**
	 * {@link Exit#NORD}
	 */
	NORD("N", "N (Aller a la sortie Nord)\n"),
	/**
	 * {@link Exit#NORD_EST}
	 */
	NORD_EST("NE", "NE (Aller a la sortie Nord-EST)\n"),
	/**
	 * {@link Exit#SUD}
	 */
	SUD("S", "S (Aller a la sortie Sud)\n"),
	/**
	 * {@link Exit#EST}
	 */
	EST("E", "E (Aller a la sortie Est)\n"),
	/**
	 * {@link Exit#OUEST}
	 */
	OUEST("O", "O (Aller a la sortie Ouest)\n"),
	/**
	 * Pour voir toutes les commandes.
	 */
	AIDE("?", "? (Aide)\n"),
	/**
	 * Pour quitter le jeu.
	 */
	QUITTER("Q", "Q (Quitter)\n"),
	/**
	 * Pour revenir sur ses pas.
	 */
	RETOUR("R", "R (Retourner en arriere)\n"),
	/**
	 * Permet d'executer le fichier de solution pour la fin normale.
	 */
	TERMINER("T", "T (Soluce automatique)\n"),
	/**
	 * Permet d'executer le fichier de solution pour la fin secrete.
	 */
	TERMINER_SEC("TSEC", "TSEC (Soluce automatique de fin secrete)"),
	/**
	 * Permet de voir le contenu du sac a dos.
	 */
	SAC("SA", "SA (Voir les elements dans le sac a dos)\n"),
	/**
	 * {@link Action#BILLY}
	 */
	BILLY("BILLY", "BILLY (Parler a Billy)\n"),
	/**
	 * {@link Action#MATOU}
	 */
	MATOU("MATOU", "MATOU (Parler a Matou)\n"),
	/**
	 * {@link Action#JOE}
	 */
	JOE("JOE", "JOE (Parler a Joe)\n"),
	/**
	 * {@link Action#JACK}
	 */
	JACK("JACK", "JACK (Parler a Jack)\n"),
	/**
	 * {@link Action#P1}
	 */
	P1("P1", "P1 (Chercher dans le premier pot)\n"),
	/**
	 * {@link Action#P2}
	 */
	P2("P2", "P2 (Chercher dans le deuxieme pot)\n"),
	/**
	 * {@link Action#PARLER}
	 */
	PARLER("PARLER", "PARLER (Parler au personnage)\n"),
	/**
	 * {@link Action#PRENDRE}
	 */
	PRENDRE("PRENDRE", "PRENDRE (Prendre un objet)\n"),
	/**
	 * {@link Action#GRILLAGE}
	 */
	GRILLAGE("GRILLAGE", "GRILLAGE (Permet de s'approcher du grillage)"),
	/**
	 * {@link Action#COUPER}
	 */
	COUPER("COUPER", "COUPER (Couper le grillage)\n"),
	/**
	 * {@link Answer#NEUVE}
	 */
	NEUVE("NEUVE", "NEUVE (Repondre que la matraque est neuve)\n"),
	/**
	 * {@link Answer#REPUGNANTE}s
	 */
	REPUGNANTE("REPUGNANTE", "REPUGNANTE (Repondre que la matraque est repugnante)\n"),
	/**
	 * {@link Answer#JOLIE}
	 */
	JOLIE("JOLIE", "JOLIE (Repondre que la matraque est jolie)\n"),
	/**
	 * {@link Answer#UN}
	 */
	UN("UN", "UN (Repondre 'UN' a Joe)\n"),
	/**
	 * {@link Answer#DEUX}
	 */
	DEUX("DEUX", "DEUX (Repondre 'DEUX' a Joe)\n"),
	/**
	 * {@link Answer#TROIS}
	 */
	TROIS("TROIS", "TROIS (Repondre 'TROIS' a Joe)\n"),
	/**
	 * {@link Answer#QUATRE}
	 */
	QUATRE("QUATRE", "QUATRE (Repondre 'QUATRE' a Joe)\n"),
	/**
	 * {@link Answer#CINQ}
	 */
	CINQ("CINQ", "CINQ (Repondre 'CINQ' a Joe)\n"),
	/**
	 * {@link Action#T1}
	 */
	T1("T1", "T1 (Csreuser le premier trou)\n"),
	/**
	 * {@link Action#T2}
	 */
	T2("T2", "T2 (Creuser le deuxieme trou)\n"),
	/**
	 * {@link Action#T3}
	 */
	T3("T3", "T3 (Creuser le troisieme trou)\n"),
	/**
	 * {@link Action#T4}
	 */
	T4("T4", "T4 (Creuser le quatrieme trou)\n"),
	/**
	 * {@link Action#SAUTER}
	 */
	SAUTER("SAUTER", "SAUTER (Sauter dans un trou ou autre)\n"),
	/**
	 * {@link Action#FUIR}
	 */
	FUIR("FUIR", "FUIR (Fuir la prison)\n"),
	/**
	 * {@link Action#BUREAU}
	 */
	BUREAU("BUREAU", "BUREAU (Aller dans le Bureau)\n");

	/**
	 * L'abreviation de la commande.
	 */
	private String abreviation;
	/**
	 * La description de la commande.
	 */
	private String description;

	/**
	 * Un constructeur qui permet de creer une commande avec sa abreviation et sa
	 * description
	 * 
	 * @param c Une abreviation de la commande.
	 * @param d Une description de la commande.
	 */
	private Command(String c, String d) {
		abreviation = c;
		description = d;
	}

	@Override
	public String toString() {
		return name();
	}

	/**
	 * Une methode qui permet d'obtenir touts les descriptions pour toutes les
	 * commandes.
	 * 
	 * @return Une liste de descriptions.
	 */
	public static List<String> allDescriptions() {
		ArrayList<String> result = new ArrayList<String>();
		for (Command c : values()) {
			result.add(c.description);
		}
		return result;
	}

	/**
	 * Une methode qui permet d'obtenir touts les abreviations pour toutes les
	 * commandes.
	 * 
	 * @return Une liste d'abreviations.
	 */
	public static List<String> allAbreviations() {
		ArrayList<String> result = new ArrayList<String>();
		for (Command c : values()) {
			result.add(c.abreviation);
		}
		return result;
	}

	/**
	 * Une methode qui permet d'obtenir touts les noms pour toutes les commandes.
	 * 
	 * @return Une liste de noms.
	 */
	public static List<String> allNames() {
		ArrayList<String> result = new ArrayList<String>();
		for (Command c : values()) {
			result.add(c.name());
		}
		return result;
	}

}
