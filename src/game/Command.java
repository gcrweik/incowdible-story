package game;

import java.util.ArrayList;
import java.util.List;

public enum Command {
	NORD("N", "N (Aller � la sortie Nord)"), NORD_EST("NE", "NE (Aller � la sortie Nord-EST)"),
	SUD("S", "S (Aller � la sortie Sud)"), EST("E", "E (Aller � la sortie Est)"),
	OUEST("O", "O (Aller � la sortie Ouest)"), AIDE("?", "? (Aide)"), QUITTER("Q", "Q (Quitter)"),
	RETOUR("R", "R (Retourner en arriere)"), TERMINER("T", "T (Soluce automatique)"),
	SAC("SA", "SA (Voir les elements dans le sac � dos)");

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
