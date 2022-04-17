package game;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Une classe test pour la classe Zone.
 * 
 * @author gerard_rweik
 * @version 1.0.0
 */

class ZoneTest {

	/**
	 * Les instances de classe Zone.
	 */
	Zone zone0, zone1, zone2, zone3, zone4, zone5;

	/**
	 * Une methode setUp pour ZoneTest.
	 * 
	 * @throws Exception En cas d'erreur.
	 */
	@BeforeEach
	void setUp() throws Exception {
		zone0 = new Zone("La Cellule", "Cellule.png", 712, 497);
		zone1 = new Zone("Couloir", "Couloir.png", 734, 23);
		zone2 = new Zone("Cours Interieur", "CoursInterieur.png", 693, 139);
		zone3 = new Zone("Cafétéria", "Cafétéria.png", 588, 42);
		zone4 = new Zone("Cours Exterieur", "CoursExterieur.png", 1111, 218);
		zone5 = new Zone("Cours de Sport", "Sport.png", 771, 36);

		// La sortie de Cellule
		zone0.addExit(Exit.SUD, zone1);
		zone0.addAction(Action.BILLY);

		// Les sorties de Couloir
		zone1.addExit(Exit.NORD, zone0);
		zone1.addExit(Exit.SUD, zone2);

		// Les sorties de Cours Interieur
		zone2.addExit(Exit.NORD, zone1);
		zone2.addExit(Exit.EST, zone3);
		zone2.addExit(Exit.OUEST, zone4);

		// Les sorties de Caf�t�ria
		zone3.addExit(Exit.OUEST, zone2);
		zone3.addAction(Action.JOE);

		// Les sorties de Cours Exterieur
		zone4.addExit(Exit.EST, zone2);
		zone4.addExit(Exit.SUD, zone5);
		zone4.addAction(Action.MATOU);

		// Les sorties de Cours de Sport
		zone5.addExit(Exit.NORD, zone4);
		zone5.addAction(Action.JACK);
		zone5.addAction(Action.GRILLAGE);
	}

	/**
	 * Une methode qui execute des tests de ZoneTest.
	 */
	@Test
	@DisplayName("Zone test")
	void test() {

		assertEquals(zone0.toString(), "La Cellule");
		assertEquals(zone1.toString(), "Couloir");
		assertEquals(zone2.toString(), "Cours Interieur");
		assertEquals(zone3.toString(), "Cafétéria");
		assertEquals(zone4.toString(), "Cours Exterieur");
		assertEquals(zone5.toString(), "Cours de Sport");

		assertNotEquals(zone0.longDescriptionAction(), "La Cellule");
		assertNotEquals(zone1.longDescriptionAction(), "Couloir");
		assertNotEquals(zone2.longDescriptionAction(), "Cafétéria");

		assertNotEquals(zone0.longDescriptionAction(), "Vous etes dans la zone : La Cellule\nSorties : [SUD]");
		assertNotEquals(zone1.longDescriptionAction(), "Vous etes dans la zone : Couloir\nSorties : [SUD, NORD]");
		assertNotEquals(zone2.longDescriptionAction(),
				"Vous etes dans la zone : Cours Interieur\nSorties : [NORD, EST, OUEST]");

	}

}
