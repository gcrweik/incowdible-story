package game;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Une classe test pour la classe Element.
 * 
 * @author gerard_rweik
 * @version 1.0.0
 */

class ElementTest {

	/**
	 * Une instance de classe Element.
	 */
	Element e;

	/**
	 * Une methode setUp pour ElementTest.
	 * 
	 * @throws Exception En cas d'erreur.
	 */
	@BeforeEach
	void setUp() throws Exception {
		e = new Element(0, 0, 100, 100, "img_name.png");
	}

	/**
	 * Une methode qui execute des tests de ElementTest.
	 */
	@Test
	@DisplayName("Element test")
	void test() {

		assertEquals(e.x, 0);
		assertEquals(e.y, 0);

		e.setCoordinates(550, 520);

		assertNotEquals(e.x, 0);
		assertNotEquals(e.y, 0);

		assertEquals(e.x, 550);
		assertEquals(e.y, 520);

	}

}