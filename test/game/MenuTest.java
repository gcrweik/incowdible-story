package game;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Une classe test pour la classe Introduction.
 * 
 * @author roman_tyzio
 * @version 1.0.0
 *
 */
class MenuTest {
	/**
	 * Une instance de classe Menu.
	 */
	Menu menu1;

	/**
	 * Une methode setUp pour MenuTest.
	 * 
	 * @throws Exception En cas d'erreur.
	 */
	@BeforeEach
	void setUp() throws Exception {
		menu1 = new Menu();
	}

	/**
	 * Une methode qui execute des tests MenuTest.
	 */
	@Test
	@DisplayName("Menu constructor test")
	void testMenu() {
		assertEquals(menu1.getMusic(), new Music("MenuMusic"));
		assertNotEquals(menu1.getMusic(), new Music("IntroMusic"));

		assertEquals(menu1, new Menu());
		assertNotEquals(menu1, new Introduction());
	}

}
