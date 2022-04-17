package game;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Une classe test pour la classe Backpack.
 * 
 * @author gerard_rweik
 * @version 1.0.0
 */
class BackpackTest {
	/**
	 * Une instance de classe Backpack.
	 */
	Backpack bp;

	/**
	 * Une methode setUp pour BackpackTest.
	 * 
	 * @throws Exception En cas d'erreur.
	 */
	@BeforeEach
	void setUp() throws Exception {
		bp = new Backpack();
	}

	/**
	 * Une methode qui execute des tests de BackpackTest.
	 */
	@Test
	@DisplayName("Backpack test")
	void test() {
		assertEquals(bp.getContenu().size(), 0);
		assertEquals(bp.cigs, false);
		assertEquals(bp.key, false);
		assertNotEquals(bp.pliers, true);
		assertEquals(bp.shovel, false);

		assertEquals(bp.toString(), "Votre sac a dos est vide!");

		bp.addElement(new Element(0, 0, 0, 0, null));
		bp.addElement(new Element(0, 0, 0, 0, null));
		bp.addElement(new Element(0, 0, 0, 0, null));

		assertNotEquals(bp.getContenu().size(), 0);
		assertEquals(bp.getContenu().size(), 3);

		assertNotEquals(bp.toString(), "Votre sac a dos est vide!");
	}

}
