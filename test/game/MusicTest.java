package game;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MusicTest {

	Music music1, music2, music3, music4;

	@BeforeEach
	void setUp() throws Exception {
		music1 = new Music("IntroMusic");
		music2 = new Music("MenuMusic");
		music3 = new Music("EndMusic");
		music4 = new Music("FailMusic");
	}

	@Test
	@DisplayName("Music constructor test")
	void testMusic() {

		// Verification de nom de l'instance.
		assertEquals(music1.toString(), "sounds/IntroMusic.sav");
		assertEquals(music2.toString(), "sounds/MenuMusic.sav");
		assertEquals(music3.toString(), "sounds/EndMusic.sav");
		assertEquals(music4.toString(), "sounds/FailMusic.sav");

		// Égalité des objets
		assertEquals(music1, new Music("IntroMusic"));
		assertNotEquals(music3, new Music("MenuMusic"));
		assertNotEquals(music3, new Music("MenuMusic"));
	}
}

