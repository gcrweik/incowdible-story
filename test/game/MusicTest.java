package game;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Une classe test pour la classe Music.
 * 
 * @author roman_tyzio
 * @version 1.0.0
 *
 */
class MusicTest {

	/**
	 * Les instances de classe Music.
	 */
	Music music1, music2, music3, music4;

	/**
	 * Une methode setUp pour MusicTest.
	 * 
	 * @throws Exception En cas d'erreur.
	 */
	@BeforeEach
	void setUp() throws Exception {
		music1 = new Music("IntroMusic");
		music2 = new Music("MenuMusic");
		music3 = new Music("EndMusic");
		music4 = new Music("FailMusic");
	}

	/**
	 * Une methode qui execute des tests de MusicTest.
	 */
	@Test
	@DisplayName("Music constructor test")
	void testMusic() {

		// Verification de nom de l'instance.
		assertEquals(music1.toString(), "sounds/IntroMusic.sav");
		assertEquals(music2.toString(), "sounds/MenuMusic.sav");
		assertEquals(music3.toString(), "sounds/EndMusic.sav");
		assertEquals(music4.toString(), "sounds/FailMusic.sav");

		// Egalite des objets
		assertEquals(music1, new Music("IntroMusic"));
		assertNotEquals(music3, new Music("MenuMusic"));
		assertNotEquals(music3, new Music("MenuMusic"));
	}
}
