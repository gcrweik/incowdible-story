package jeu;
public class Jeu {
	
	private GUI gui; 
	private Zone zoneCourante;
	
	public Jeu() {
		creerCarte();
		gui = null;
	}

	public void setGUI( GUI g) { gui = g; afficherMessageDeBienvenue(); }
    
	private void creerCarte() {
		Zone[] zones = new Zone[9];
		zones[0] = new Zone("La Cellule", "Cellule.png" );
		zones[1] = new Zone("Couloir", "Couloir.png" );
		zones[2] = new Zone("Cours Interieur", "CoursInterieur.png" );
		zones[3] = new Zone("Cafétéria", "Cafétéria.png" );
		zones[4] = new Zone("Cours Exterieur", "CoursExterieur.png" );
		zones[5] = new Zone("Cours de Sport", "Sport.png" );
		zones[6] = new Zone("Mur", "Mur.png" );
		zones[7] = new Zone("Escalier", "Escalier.png");
		zones[8] = new Zone("Bureau de Directeur", "Bureau.png");
		
		// La sortie de Cellule
		zones[0].ajouteSortie(Sortie.SUD, zones[1]);
		
		// Les sorties de Couloir
		zones[1].ajouteSortie(Sortie.NORD, zones[0]);
		zones[1].ajouteSortie(Sortie.SUD, zones[2]);
		
		// Les sorties de Cours Interieur
		zones[2].ajouteSortie(Sortie.NORD, zones[1]);
		zones[2].ajouteSortie(Sortie.NORD_EST, zones[7]);
		zones[2].ajouteSortie(Sortie.EST, zones[3]);
		zones[2].ajouteSortie(Sortie.OUEST, zones[4]);
		
		// Les sorties d'Escalier
		zones[7].ajouteSortie(Sortie.OUEST, zones[2]);
		zones[7].ajouteSortie(Sortie.NORD, zones[8]);
		
		// Les sorties de Cafétéria
		zones[3].ajouteSortie(Sortie.OUEST, zones[2]);
		
		// Les sorties de Cours Exterieur
		zones[4].ajouteSortie(Sortie.EST, zones[2]);
		zones[4].ajouteSortie(Sortie.SUD, zones[5]);
		
		// Les sorties de Cours de Sport
		zones[5].ajouteSortie(Sortie.NORD, zones[4]);
		zones[5].ajouteSortie(Sortie.SUD, zones[6]);
		
		// Les sorties de Mur
		zones[6].ajouteSortie(Sortie.NORD, zones[5]);
		
		// Les sorties de Bureau de Directeur
		zones[8].ajouteSortie(Sortie.OUEST, zones[7]);
		
		zoneCourante = zones[0]; 
	}

	private void afficherLocalisation() {
		gui.afficher( zoneCourante.descriptionLongue());
		gui.afficher();
	}

	private void afficherMessageDeBienvenue() {
		gui.afficher("Bienvenue !");
		gui.afficher();
		gui.afficher("Tapez '?' pour obtenir de l'aide.");
		gui.afficher();
		afficherLocalisation();
		gui.afficheImage(zoneCourante.nomImage());
	}
    
	public void traiterCommande(String commandeLue) {
		gui.afficher( "> " + commandeLue + "\n");
		switch (commandeLue.toUpperCase()) {
			case "?" : case "AIDE" : 
				afficherAide(); 
				break;
			case "N" : case "NORD" :
				allerEn( "NORD"); 
				break;
			case "NE" : case "NORD_EST" :
				allerEn( "NORD_EST"); 
				break;
			case "S" : case "SUD" :
				allerEn( "SUD"); 
				break;
			case "E" : case "EST" :
				allerEn( "EST"); 
				break;
			case "O" : case "OUEST" :
				allerEn( "OUEST"); 
				break;
			case "Q" : case "QUITTER" :
				terminer();
				break;
			default : 
				gui.afficher("Commande inconnue");
				break;
        	}
    	}

	private void afficherAide() {
		gui.afficher("Etes-vous perdu ?");
		gui.afficher();
		gui.afficher("Les commandes autorisées sont :");
		gui.afficher();
		gui.afficher(Commande.toutesLesDescriptions().toString());
		gui.afficher();
	}

	private void allerEn(String direction) {
		Zone nouvelle = zoneCourante.obtientSortie( direction);
		if ( nouvelle == null ) {
			gui.afficher( "Pas de sortie " + direction);
			gui.afficher();
		}
		else {
			zoneCourante = nouvelle;
			gui.afficher(zoneCourante.descriptionLongue());
			gui.afficher();
			gui.afficheImage(zoneCourante.nomImage());
		}
	}
    
	private void terminer() {
		gui.afficher( "Au revoir...");
		gui.enable( false);
	}
}
