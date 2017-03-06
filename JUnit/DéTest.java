package JUnit;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import boggle.Dé;

public class DéTest {

	@Test
	public void testSetFaces() {
		Dé dé = new Dé("ABCDEF");
		System.out.println(dé.toString());
		assertTrue(dé.toString().equals("ABCDEF"));
	}

	@Test
	public void testMélanger() {
		Dé dé = new Dé("ABCDEF");
		boolean mélange = false;
		for(int i = 0; i < 50; ++i) {
			dé.mélanger();
			if(dé.getFaceVisible() == 'C') {
				mélange = true;
			}
		}
		assertTrue(mélange);
	}
}
