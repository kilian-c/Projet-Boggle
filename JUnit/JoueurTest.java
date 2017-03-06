package JUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import boggle.Joueur;
import boggle.Plateau;

public class JoueurTest {

	@Test
	public void testEntrerMot() {
		Joueur joueur = new Joueur("René");
		joueur.entrerMot("Lapin");
		joueur.entrerMot("Tortue");
		joueur.entrerMot("Carotte");
		assertTrue(joueur.getMotsEntrés().get(0).equals("Lapin"));
		assertTrue(joueur.getMotsEntrés().get(2).equals("Carotte"));
	}

	@Test
	public void testSupprimerMot() {
		Joueur joueur = new Joueur("René");
		joueur.entrerMot("Lapin");
		joueur.entrerMot("Tortue");
		joueur.entrerMot("Carotte");
		assertTrue(joueur.getMotsEntrés().get(1).equals("Tortue"));
		joueur.supprimerMot(1);
		assertTrue(joueur.getMotsEntrés().get(1).equals("Carotte"));
	}

	@Test
	public void testCalculerPoints() {
		Plateau.créerPlateauPersonnalisé("FCET AIOC LERA IMNE");
		Joueur joueur1 = new Joueur("Joe");
		Joueur joueur2 = new Joueur("Alice");
		joueur1.entrerMot("face");
		joueur1.entrerMot("ail");
		joueur1.entrerMot("carotte");
		joueur1.entrerMot("faire");
		joueur1.entrerMot("ane");
		joueur1.entrerMot("roter");
		joueur1.entrerMot("roter");
		
		joueur2.entrerMot("mine");
		joueur2.entrerMot("rote");
		joueur2.entrerMot("cora");
		joueur2.entrerMot("lafire");
		joueur2.entrerMot("lice");
		joueur2.entrerMot("roti");
		joueur2.entrerMot("carmen");
		joueur2.entrerMot("lime");
		
		joueur1.calculerPoints();
		joueur2.calculerPoints();
		
		assertEquals(joueur1.getPoints(), 5);
		assertEquals(joueur2.getPoints(), 3);
	}

}
