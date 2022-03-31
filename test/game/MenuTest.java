package game;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MenuTest {
	Menu menu1;

	@BeforeEach
	void setUp() throws Exception {
		menu1 = new Menu();
	}

	@Test
	@DisplayName("Menu constructor test")
	void testMenu() {
		assertEquals(menu1.getMusic(), new Music("MenuMusic"));
		assertNotEquals(menu1.getMusic(), new Music("IntroMusic"));

		assertEquals(menu1, new Menu());
		assertNotEquals(menu1, new Introduction());
	}

}

