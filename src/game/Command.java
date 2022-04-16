package game;

import java.util.ArrayList;
import java.util.List;

public enum Command {
	NORD("N", "N (Aller à la sortie Nord)\n"), NORD_EST("NE", "NE (Aller à la sortie Nord-EST)\n"),
	SUD("S", "S (Aller à la sortie Sud)\n"), EST("E", "E (Aller à la sortie Est)\n"),
	OUEST("O", "O (Aller à la sortie Ouest)\n"), AIDE("?", "? (Aide)\n"), QUITTER("Q", "Q (Quitter)\n"),
	RETOUR("R", "R (Retourner en arriere)\n"), TERMINER("T", "T (Soluce automatique)\n"),
	TERMINER_SEC("TSEC", "TSEC (Soluce automatique de fin secrete)"),
	SAC("SA", "SA (Voir les elements dans le sac à dos)\n"), BILLY("BILLY", "BILLY (Parler à Billy)\n"),
	MATOU("MATOU", "MATOU (Parler à Matou)\n"), JOE("JOE", "JOE (Parler à Joe)\n"),
	JACK("JACK", "JACK (Parler à Jack)\n"), P1("P1", "P1 (Chercher dans le premier pot)\n"),
	P2("P2", "P2 (Chercher dans le deuxieme pot)\n"), PARLER("PARLER", "PARLER (Parler au personnage)\n"),
	PRENDRE("PRENDRE", "PRENDRE (Prendre un objet)\n"),
	GRILLAGE("GRILLAGE", "GRILLAGE(Permet de s'approcher du grillage)"),
	COUPER("COUPER", "COUPER (Couper le grillage)\n"), NEUVE("NEUVE", "NEUVE (Repondre que la matraque est neuve)\n"),
	REPUGNANTE("REPUGNANTE", "REPUGNANTE (Repondre que la matraque est repugnante)\n"),
	JOLIE("JOLIE", "JOLIE (Repondre que la matraque est jolie)\n"), UN("UN", "UN (Repondre 'UN' à Joe)\n"),
	DEUX("DEUX", "DEUX (Repondre 'DEUX' à Joe)\n"), TROIS("TROIS", "TROIS (Repondre 'TROIS' à Joe)\n"),
	QUATRE("QUATRE", "QUATRE (Repondre 'QUATRE' à Joe)\n"), CINQ("CINQ", "CINQ (Repondre 'CINQ' à Joe)\n"),
	T1("T1", "T1 (Creuser le premier trou)\n"), T2("T2", "T2 (Creuser le deuxieme trou)\n"),
	T3("T3", "T3 (Creuser le troisieme trou)\n"), T4("T4", "T4 (Creuser le quatrieme trou)\n"),
	SAUTER("SAUTER", "SAUTER (Sauter dans un trou ou autre)\n"), FUIR("FUIR", "FUIR (Fuir la prison)");

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
