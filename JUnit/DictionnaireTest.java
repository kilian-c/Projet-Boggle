package JUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import dictionnaire.Dictionnaire;

public class DictionnaireTest {

	@Test
	public void testSetMots() {
		assertFalse(Dictionnaire.setMots("bidon/dico.txt"));
		assertTrue(Dictionnaire.setMots("src/dictionnaire/dico.txt"));
		
		assertEquals(Dictionnaire.getMots().get(0), "AA");
		assertEquals(Dictionnaire.getMots().get(581), "ABJURERIONS");
		assertEquals(Dictionnaire.getMots().get(369084), "ZYTHUMS");
	}

	@Test
	public void testExiste() {
		Dictionnaire.setMots("src/dictionnaire/dico.txt");
		assertFalse(Dictionnaire.existe("zorg"));
		assertTrue(Dictionnaire.existe("ZYGOTES"));
		assertTrue(Dictionnaire.existe("Girafe"));
		assertTrue(Dictionnaire.existe("SpAghettI"));
		assertTrue(Dictionnaire.existe("pOUDreS"));
	}

}
