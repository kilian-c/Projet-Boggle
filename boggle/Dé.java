package boggle;

import java.util.Random;

/**
 * Composantes d'un dé
 * @author Kilian CHOLLET, Antoine AUDRIN
 * @version 1
 */

public class Dé {
	// Nombre de faces
	private static final int NB_FACES = 6;
	
	// Chaque face du dé est un char (une lettre)
	private char[] faces;
	private char faceVisible;
	
	/**
	 * Constructeur de dé.
	 * @param lettres sous le format "ABCDEF" (un caractère correspond à une face)
	 */
	public Dé (String lettres) {
		this.faces = new char[NB_FACES];
		for(int i = 0; i < NB_FACES; ++i) {
			 this.faces[i] = lettres.charAt(i);
		}
		this.setFaceVisible(0);
	}
	
	/**
	 * Retourne le nombre de faces
	 * @return
	 */
	public int getNbFaces() {
		return NB_FACES;
	}

	/**
	 * Retourne les faces dans un tableau de char
	 * @return faces
	 */
	public char[] getFaces() {
		return faces;
	}
	
	/**
	 * Retourne la face visible d'un dé
	 * @return faceVisible
	 */
	public char getFaceVisible() {
		return faceVisible;
	}

	/**
	 * Définit la face spécifiée comme face visible
	 * @param i, indice de la face à définir comme visible
	 */
	private void setFaceVisible(int i) {
		this.faceVisible = this.faces[i];
	}
	
	/**
	 * Mélange le dé (définit une nouvelle face visible aléatoirement
	 */
	public void mélanger() {
		Random rand = new Random();
		int faceAlea = rand.nextInt(NB_FACES); //Entre 0 et NB_FACES-1
		this.setFaceVisible(faceAlea);
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		StringBuilder str = new StringBuilder();
		for(int i = 0; i < NB_FACES; ++i) {
			str.append(faces[i]);
		}
		return str.toString();
	}
}
