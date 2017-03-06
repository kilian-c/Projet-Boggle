package JUnit;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import boggle.Boggle;

public class BoggleTest {

	@Test
	public void testAjouterJoueur() {
		Boggle.setNbJoueurs(2);
		Boggle.ajouterJoueur("Benjamin");
		Boggle.ajouterJoueur("Charlotte");
		assertEquals(Boggle.getNbJoueurs(), 2);
		assertEquals(Boggle.getJoueurs().get(1).getNom(), "Charlotte");
	}
	
	@Test
	public void testAjouterMotInvalide() {
		Boggle.ajouterMotInvalide("mot1");
		Boggle.ajouterMotInvalide("mot2");
		assertEquals(Boggle.getMotsInvalides().size(), 2);
		assertEquals(Boggle.getMotsInvalides().get(0), "mot1");
	}

}
