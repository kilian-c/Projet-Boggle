package boggle;
import java.util.ArrayList;

import dictionnaire.Dictionnaire;

/**
 * Composantes d'un plateau
 * @author Kilian CHOLLET, Antoine AUDRIN
 * @version 1
 */
public class Plateau {
	// Taille du plateau
	private static final int TAILLE_PLATEAU = 16;
	
	// Nombre de lignes du plateau (il s'agit d'un carré)
	private static final int NB_LIGNES = (int) Math.sqrt(TAILLE_PLATEAU);
	
	// Le plateau contient toutes les faces visibles de chaque dé
	private static char[][] plateau;
	
	// ArrayList contenant tous les mots possible à trouver dans le plateau
	private static ArrayList<String> mots;
	
	// Tableau contenant tous les dés du plateau
	private static Dé dés[];
	
	static {
		dés = new Dé[TAILLE_PLATEAU];
	}
	
	/**
	 * Setter des dés qui vont constituer le plateau, en fonction d'une longue chaine de caractères de la forme :
	 * "ABCDEF GHIJKL MNOPQR STUVWX YZABCD .........." contenant les faces de chaque dé.
	 * @param lettres
	 */
	public static void setDés(String lettres) {
		for(int i = 0; i < TAILLE_PLATEAU; ++i) {
			dés[i] = new Dé(lettres.split(" ")[i]);
			dés[i].mélanger();
		}
		créerPlateau(dés);
	}

	/**
	 * Retourne le tableau contenant tous les dés de la partie
	 * @return dés
	 */
	public static Dé[] getDés() {
		return dés;
	}

	/**
	 * Retourne les mots du plateau
	 * @return mots
	 * @see mots
	 */
	public static ArrayList<String> getMots() {
		return mots;
	}
	
	/**
	 * Retourne le nombre de lignes du plateau (et par conséquent le nombre de colonnes)
	 * @return NB_LIGNES
	 */
	public static int getNbLignes() {
		return NB_LIGNES;
	}

	/**
	 * Crée un plateau à partir d'un tableau de dés
	 * @param dés
	 */
	public static void créerPlateau(Dé[] dés) {
		plateau = new char[NB_LIGNES][NB_LIGNES];
		int cpt = 0;
		for(int i = 0; i < NB_LIGNES; ++i) {
			for(int j = 0; j < NB_LIGNES; j++) {
				plateau[i][j] = dés[cpt].getFaceVisible();
				cpt++;
			}
		}
		trouverMots();
	}
	
	/**
	 * Crée un plateau à partir de lettres (pas besoin de dés)
	 * @param lettres
	 */
	public static void créerPlateauPersonnalisé(String lettres) {
		plateau = new char[NB_LIGNES][NB_LIGNES];
		int cpt = 0;
		for(int i = 0; i < NB_LIGNES; ++i) {
			for(int j = 0; j < NB_LIGNES; ++j) {
				plateau[i][j] = lettres.charAt(cpt);
				cpt++;
			}
			cpt++;
		}
		trouverMots();
	}
	
	/**
	 * Retourne le plateau
	 * @return plateau
	 */
	public static char[][] getPlateau() {
		return plateau;
	}
	
	/**
	 * Recherche si un mot est dans le plateau
	 * @param mot
	 * @return true si le mot est dans le plateau
	 */
	private static boolean recherche(String mot) {
		boolean[][] visitée = new boolean[NB_LIGNES][NB_LIGNES];
		for(int i = 0; i < NB_LIGNES; ++i) {
			for(int j = 0; j < NB_LIGNES; ++j) {
				visitée[i][j] = false;
			}
		}
		for(int i = 0; i < NB_LIGNES; ++i) {
			for(int j = 0; j < NB_LIGNES; ++j) {
				if(sousRecherche(mot, 0, i, j, visitée)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Méthode qui réalise une sous-recherche pour la case du plateau donnée
	 * @param mot (mot en cours de traitement)
	 * @param pos (position dans le mot)
	 * @param x (ligne de la case)
	 * @param y (colonne de la case)
	 * @parap visitée (tableau de booléens pour savoir quelles cases ont été visitées)
	 * @return true si la sous-recherche est un succès, false sinon
	 */
	private static boolean sousRecherche(String mot, int pos, int x, int y, boolean[][] visitée) {
		if(pos >= mot.length()) {
			return true;
		}
		if((x > 3) || (x < 0) || (y > 3) || (y < 0)) {
			return false;
		}
		if(plateau[x][y] != mot.charAt(pos)) {
			return false;
		}
		if(visitée[x][y]) {
			return false;
		}
		visitée[x][y] = true;
		
		if(sousRecherche(mot, pos + 1, x+1, y, visitée)) {
			return true;
		}
		if(sousRecherche(mot, pos + 1, x, y+1, visitée)) {
			return true;
		}
		if(sousRecherche(mot, pos + 1, x-1, y, visitée)) {
			return true;
		}
		if(sousRecherche(mot, pos + 1, x, y-1, visitée)) {
			return true;
		}
		if(sousRecherche(mot, pos + 1, x+1, y+1, visitée)) {
			return true;
		}
		if(sousRecherche(mot, pos + 1, x-1, y-1, visitée)) {
			return true;
		}
		if(sousRecherche(mot, pos + 1, x+1, y-1, visitée)) {
			return true;
		}
		if(sousRecherche(mot, pos + 1, x-1, y+1, visitée)) {
			return true;
		}
		
		visitée[x][y] = false;
		return false;
	}

	/**
	 * Trouve tous les mots valides du plateau
	 */
	private static void trouverMots() {
		Dictionnaire.setMots("src/dictionnaire/dico.txt");
		mots = new ArrayList<String>();
		mots = Dictionnaire.getMots();
		for(int i = 0; i < mots.size()-1; ++i) {
			if((!recherche(mots.get(i))) || (mots.get(i).length() < 3)) {
				mots.remove(i);
				--i;
			}
		}
		mots.remove(mots.size()-1); // (null)
	}
	
	/**
	 * Indique si un mot est contenu dans le plateau
	 * @param mot
	 * @return true si le plateau contient le mot
	 */
	public static boolean contient(String mot) {
		return mots.contains(mot.toUpperCase());
	}
}
