package game;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class IntroductionTest {

	Introduction intro1;

	@BeforeEach
	void setUp() throws Exception {
		intro1 = new Introduction();
	}

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
