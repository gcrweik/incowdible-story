package jeu;

import java.util.ArrayList;
import java.util.List;

public enum Commande {
	NORD("N", "N (Aller � la sortie Nord)"), NORD_EST("NE", "NE (Aller � la sortie Nord-EST)"),
	SUD("S", "S (Aller � la sortie Sud)"), EST("E", "E (Aller � la sortie Est)"),
	OUEST("O", "O (Aller � la sortie Ouest)"), AIDE("?", "? (Aide)"), QUITTER("Q", "Q (Quitter)");

	private String abreviation;
	private String description;

	private Commande(String c, String d) {
		abreviation = c;
		description = d;
	}

	@Override
	public String toString() {
		return name();
	}

	public static List<String> toutesLesDescriptions() {
		ArrayList<String> resultat = new ArrayList<String>();
		for (Commande c : values()) {
			resultat.add(c.description);
		}
		return resultat;
	}

	public static List<String> toutesLesAbreviations() {
		ArrayList<String> resultat = new ArrayList<String>();
		for (Commande c : values()) {
			resultat.add(c.abreviation);
		}
		return resultat;
	}

	public static List<String> tousLesNoms() {
		ArrayList<String> resultat = new ArrayList<String>();
		for (Commande c : values()) {
			resultat.add(c.name());
		}
		return resultat;
	}

}
