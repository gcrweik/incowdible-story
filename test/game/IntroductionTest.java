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
 */
class IntroductionTest {

	/**
	 * Une instance de classe Introduction.
	 */
	Introduction intro1;

	/**
	 * Une methode setUp pour IntroductionTest.
	 * 
	 * @throws Exception En cas d'erreur.
	 */
	@BeforeEach
	void setUp() throws Exception {
		intro1 = new Introduction();
	}

	/**
	 * Une methode qui execute des tests de IntroductionTest.
	 */
	@Test
	@DisplayName("Introduction constructor test")
	void testIntroduction() {
		assertEquals(intro1.getMusic(), new Music("IntroMusic"));
		assertNotEquals(intro1.getMusic(), new Music("FailMusic"));

		assertEquals(intro1, new Introduction());
		assertNotEquals(intro1, new Menu());

		assertEquals(intro1.getCounter(), 0);
		assertNotEquals(intro1.getCounter(), 2);

	}

}
