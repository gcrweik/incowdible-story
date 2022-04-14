package game;

import java.util.ArrayList;
import java.util.List;

public enum Command {
	NORD("N", "N (Aller � la sortie Nord)\n"), NORD_EST("NE", "NE (Aller � la sortie Nord-EST)\n"),
	SUD("S", "S (Aller � la sortie Sud)\n"), EST("E", "E (Aller � la sortie Est)\n"),
	OUEST("O", "O (Aller � la sortie Ouest)\n"), AIDE("?", "? (Aide)\n"), QUITTER("Q", "Q (Quitter)\n"),
	RETOUR("R", "R (Retourner en arriere)\n"), TERMINER("T", "T (Soluce automatique)\n"),
	SAC("SA", "SA (Voir les elements dans le sac � dos)\n"), BILLY("BILLY", "BILLY (Parler � Billy)\n"),
	MATOU("MATOU", "MATOU (Parler � Matou)\n"), JOE("JOE", "JOE (Parler � Joe)\n"),
	JACK("JACK", "JACK (Parler � Jack)\n"), P1("P1", "P1 (Chercher dans le premier pot)\n"),
	P2("P2", "P2 (Chercher dans le deuxieme pot)\n"), PARLER("PARLER", "PARLER (Parler au personnage)\n"),
	PRENDRE("PRENDRE", "PRENDRE (Prendre un objet)\n"),
	GRILLAGE("GRILLAGE", "GRILLAGE(Permet de s'approcher du grillage)"),
	COUPER("COUPER", "COUPER (Couper le grillage)\n"), NEUVE("NEUVE", "NEUVE (Repondre que la matraque est neuve)\n"),
	REPUGNANTE("REPUGNANTE", "REPUGNANTE (Repondre que la matraque est repugnante)\n"),
	JOLIE("JOLIE", "JOLIE (Repondre que la matraque est jolie)\n"), UN("UN", "UN (Permet de repondre 'UN' � Joe)\n"),
	DEUX("DEUX", "DEUX (Permet de repondre 'DEUX' � Joe)\n"),
	TROIS("TROIS", "TROIS (Permet de repondre 'TROIS' � Joe)\n"),
	QUATRE("QUATRE", "QUATRE (Permet de repondre 'QUATRE' � Joe)\n"),
	CINQ("CINQ", "CINQ (Permet de repondre 'CINQ' � Joe)\n"), T1("T1", "T1 (Permet de creuser le premier trou)\n"),
	T2("T2", "T2 (Permet de creuser le deuxieme trou)\n"), T3("T3", "T3 (Permet de creuser le troisieme trou)\n"),
	T4("T4", "T4 (Permet de creuser le quatrieme trou)\n"),
	SAUTER("SAUTER", "SAUTER (Permet de sauter dans un trou ou autre)\n"), FUIR("FUIR", "FUIR (Fuir la prison)");

	private String abreviation;
	private String description;

	private Command(String c, String d) {
		abreviation = c;
		description = d;
	}

	@Override
	public String toString() {
		return name();
	}

	public static List<String> allDescriptions() {
		ArrayList<String> result = new ArrayList<String>();
		for (Command c : values()) {
			result.add(c.description);
		}
		return result;
	}

	public static List<String> allAbreviations() {
		ArrayList<String> result = new ArrayList<String>();
		for (Command c : values()) {
			result.add(c.abreviation);
		}
		return result;
	}

	public static List<String> allNames() {
		ArrayList<String> result = new ArrayList<String>();
		for (Command c : values()) {
			result.add(c.name());
		}
		return result;
	}

}
