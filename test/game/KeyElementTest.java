package game;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Une classe test pour la classe KeyElement.
 * 
 * @author gerard_rweik
 * @version 1.0.0
 */
class KeyElementTest {

	/**
	 * Une instance de classe KeyElement.
	 */
	KeyElement ke;

	/**
	 * Une methode setUp pour KeyElementTest.
	 * 
	 * @throws Exception En cas d'erreur.
	 */
	@BeforeEach
	void setUp() throws Exception {
		ke = new KeyElement(0, 0, 0, 0, "img.png", "Person1");
	}

	/**
	 * Une methode qui execute des tests de KeyElementTest.
	 */
	@Test
	@DisplayName("KeyElement test")
	void test() {
		assertEquals(ke.toString(), "Person1");
		assertEquals(ke.imageHeight, 0);
		assertEquals(ke.imageWidth, 0);

		ke.setCoordinates(100, 100);

		assertEquals(ke.x, 100);
		assertEquals(ke.y, 100);
	}

}
