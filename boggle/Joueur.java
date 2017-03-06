package boggle;

import java.util.ArrayList;

/**
 * Composantes d'un joueur
 * @author Kilian CHOLLET, Antoine AUDRIN
 * @version 1
 */
public class Joueur {
	// Nom du joueur
	private String nom;
	
	// Les mots entrés du joueur sont placés dans une ArrayList
	private ArrayList<String> motsEntrés;
	
	// Mots valides entrés par le joueur
	private ArrayList<String> motsValides;
	
	// Nombre de points du joueur
	private int points;
	
	/**
	 * Constructeur du joueur (instancie les ArrayList et définit le nom)
	 * @param nom
	 */
	public Joueur(String nom) {
		this.nom = nom;
		this.motsValides = new ArrayList<String>();
		this.motsEntrés = new ArrayList<String>();
	}

	/**
	 * Retourne le nom du joueur
	 * @return nom
	 */
	public String getNom() {
		return this.nom;
	}

	/**
	 * Retourne les mots entrés par le joueur dans une ArrayList
	 * @return motsEntrés
	 */
	public ArrayList<String> getMotsEntrés() {
		return this.motsEntrés;
	}
	
	/**
	 * Ajoute un mot à la liste des mots entrés par le joueur
	 * @param mot
	 */
	public void entrerMot(String mot) {
		this.motsEntrés.add(mot);
	}
	
	/**
	 * Supprime un mot de la liste des mots entrés par le joueur
	 * @param i, indice du mot
	 */
	public void supprimerMot(int i) {
		this.motsEntrés.remove(i);
	}
	
	/**
	 * Reourne les mots valides entrés par le joueur
	 * @return motsValides
	 */
	public ArrayList<String> getMotsValides() {
		return this.motsValides;
	}

	/**
	 * Retourne les points du joueur
	 * @return points
	 */
	public int getPoints() {
		return this.points;
	}
	
	/**
	 * Calcule les points du joueur
	 */
	public void calculerPoints() {
		boolean motEntréPlusieursFois = false;
		for(int i = 0; i < this.motsEntrés.size(); ++i) {
			if(motEntréPlusieursFois) {
				--i;
			}
			
			// Suppression des doublons de chaque joueur
			for(int j = 0; j < this.motsEntrés.size(); ++j) {
				if((i != j) && (this.motsEntrés.get(i).equals(this.motsEntrés.get(j)))) {
					this.motsEntrés.remove(j);
				}
			}
			
			// Suppression des mots entrés par plusieurs joueurs
			motEntréPlusieursFois = false;
			for(int j = 0; j < Boggle.getNbJoueurs(); ++j) {
				
				if(!Boggle.getJoueurs().get(j).equals(this)) {
					for(int k = 0; k < Boggle.getJoueurs().get(j).getMotsEntrés().size(); ++k) {
						if(Boggle.getJoueurs().get(j).getMotsEntrés().get(k).equals(this.motsEntrés.get(i))) {
							Boggle.getJoueurs().get(j).supprimerMot(k);
							Boggle.ajouterMotInvalide(this.motsEntrés.get(i)+" (mot entré par 2 joueurs)");
							motEntréPlusieursFois = true;
						}
					}
				}
			}

			
			if(motEntréPlusieursFois) {
				this.supprimerMot(i);
			}
		}
			
		// Définition des mots valides (contenus dans le plateau) et des mots invalides
		for(int i = 0; i < this.motsEntrés.size(); ++i) {
			if(Plateau.contient(this.motsEntrés.get(i))) {
				this.motsValides.add(this.motsEntrés.get(i));
			}
			else {
				Boggle.ajouterMotInvalide(this.motsEntrés.get(i)
						+" (mot de moins de 3 lettres ou non existant"
						+ " dans le dictionnaire ou dans le plateau)");
			}
		}
		
		// Attribution des points
		for(int i = 0; i < this.motsValides.size(); ++i) {
			if((this.motsValides.get(i).length() == 3) || (this.motsValides.get(i).length() == 4)) {
				++this.points;
			}
			else if (this.motsValides.get(i).length() == 5) {
				this.points += 2;
			}
			else if (this.motsValides.get(i).length() == 6) {
				this.points += 3;
			}
			else if (this.motsValides.get(i).length() == 7) {
				this.points += 5;
			}
			else if (this.motsValides.get(i).length() > 7) {
				this.points += 11;
			}
		}
		
	}

}
