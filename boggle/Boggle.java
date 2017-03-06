package boggle;
import java.util.ArrayList;

/**
 * Classe qui contient les éléments nécessaires pour faire une partie de Boggle.
 * @author Kilian CHOLLET, Antoine AUDRIN
 * @version 1
 */

public class Boggle {
	// Nombre de joueurs de la partie de boggle
	private static int nbJoueurs;
	
	// Liste des joueurs
	private static ArrayList<Joueur> joueurs;
	
	// Arraylist qui contient tous les mots entrés par les joueurs qui ne sont pas valides
	private static ArrayList<String> motsInvalides;
	
	/**
	 * Constructeur statique : initialise les tableaux déclarés ci-dessus
	 */
	static {
		joueurs = new ArrayList<Joueur>();
		motsInvalides = new ArrayList<String>();
	}

	/**
	 * Retourne le nombre de joueurs de la partie
	 * @return nbJoueurs
	 */
	public static int getNbJoueurs() {
		return nbJoueurs;
	}

	/**
	 * Setter du nombre de joueurs
	 * @param nbJoueurs (nombre de joueurs
	 */
	public static void setNbJoueurs(int nbJoueurs) {
		Boggle.nbJoueurs = nbJoueurs;
	}

	/**
	 * Retourne les joueurs de la partie
	 * @return joueurs (ArrayList contenant tous les joueurs)
	 */
	public static ArrayList<Joueur> getJoueurs() {
		return joueurs;
	}
	
	
	/**
	 * Ajoute un joueur à la partie
	 * @param nom (nom du joueur à ajouter)
	 */
	public static void ajouterJoueur(String nom) {
		Joueur joueur = new Joueur(nom);
		joueurs.add(joueur);
	}

	/**
	 * Retourne les mots invalides de la partie
	 * @return motsInvalides
	 */
	public static ArrayList<String> getMotsInvalides() {
		return motsInvalides;
	}
	
	/**
	 * Ajouter un mot à la liste des mots invalides
	 * @param mot
	 */
	public static void ajouterMotInvalide(String mot) {
		motsInvalides.add(mot);
	}
}
