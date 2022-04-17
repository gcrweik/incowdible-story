package game;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Une classe test pour la classe Npc.
 * 
 * @author gerard_rweik
 * @version 1.0.0
 */
class NpcTest {

	/**
	 * Les instances de classe Npc.
	 */
	Npc npc1, npc2, npc3, npc4;

	/**
	 * Une methode setUp pour NpcTest.
	 * 
	 * @throws Exception En cas d'erreur.
	 */
	@BeforeEach
	void setUp() throws Exception {
		npc1 = new Npc(0, 0, 100, 100, "npc1.jpg", "Billy");
		npc2 = new Npc(0, 0, 100, 100, "npc2.jpg", "Joe");
		npc3 = new Npc(0, 0, 100, 100, "npc3.jpg", "Jack");
		npc4 = new Npc(0, 0, 100, 100, "npc4.jpg", "Matou");
	}

	/**
	 * Une methode qui execute des tests de NpcTest.
	 */
	@Test
	@DisplayName("Npc test")
	void test() {
		assertEquals(npc1.getName(), "Billy");
		assertEquals(npc2.getName(), "Joe");
		assertEquals(npc3.getName(), "Jack");
		assertEquals(npc4.getName(), "Matou");

		assertEquals(npc1.dialogCounter, 0);

		assertNotEquals(npc1.dialogBilly(), null);
		assertNotEquals(npc2.dialogJoe(), null);
		assertNotEquals(npc3.dialogJack(), null);
		assertNotEquals(npc4.dialogMatou(), null);
	}

}
