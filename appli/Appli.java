package appli;

import java.util.Scanner;
import java.util.Stack;

import boggle.Boggle;
import boggle.Plateau;

/**
 * Application (programme principal)
 * @author Kilian CHOLLET, Antoine AUDRIN
 * @version 1
 */
public class Appli {
	
	/**
	 * Main : méthode qui permet de joueur la partie de boggle.
	 * @param arg
	 */
	public static void main(String[] arg) {
		Scanner sc = new Scanner(System.in);
		
		entrerJoueurs(sc);
		
		System.out.println("Vous avez 3 minutes chacun pour trouver les mots de la grille suivante :");
		genererPlateau(sc);
		afficherPlateau();
		
		entrerMots(sc);
		
		for(int i = 0; i < Boggle.getNbJoueurs(); ++i) {
			Boggle.getJoueurs().get(i).calculerPoints();
			System.out.print(System.getProperty("line.separator"));
		}
		
		afficherMotsValides();
		afficherMotsInvalides();
		
		afficherSolution();
		afficherScores();
		
		sc.close();
	}

	private static void entrerJoueurs(Scanner sc) {
		System.out.print("Entrez le nombre de joueurs :");
		Boggle.setNbJoueurs(sc.nextInt());
		
		while(Boggle.getNbJoueurs() < 1) {
			System.out.print("Entrez le nombre de joueurs (au moins un joueur) :");
			Boggle.setNbJoueurs(sc.nextInt());
		}
		
		for(int i = 0; i < Boggle.getNbJoueurs(); ++i) {
			System.out.print("Entrer le nom du joueur " + (i + 1) + " : ");
			String nom = sc.next();
			Boggle.ajouterJoueur(nom);
		}
	}
	
	private static void genererPlateau(Scanner sc) {
		int réponse;
		do {
			System.out.println("Choisissez comment définir le plateau de jeu :");
			System.out.println("1 - Entrer le contenu du plateau");
			System.out.println("2 - Générer un plateau aléatoire");
			réponse = sc.nextInt();
			
		} while((réponse != 1) && (réponse != 2));
		
		switch(réponse) {
		case 1:
			entrerPlateau(sc);
		break;
		case 2:
			genererPlateauAlea();
		break;
		}
	}
	
	/**
	 * Affiche les mots invalides.
	 */
	private static void afficherMotsInvalides() {
		System.out.print(System.getProperty("line.separator"));
		System.out.print("Mots invalidés : ");
		System.out.print(System.getProperty("line.separator"));
		for(int i = 0 ; i < Boggle.getMotsInvalides().size(); ++i) {
			System.out.println(Boggle.getMotsInvalides().get(i));
		}
		System.out.print(System.getProperty("line.separator"));
	}
	
	private static void genererPlateauAlea() {
		System.out.print(System.getProperty("line.separator"));
		System.out.println("Chargement...");
		System.out.print(System.getProperty("line.separator"));
		Plateau.setDés(
				"TUPSEL MASROI GITNEV YUNLEG "
				+ "DECPAM KEOTUN SERLAC LIREWU "
				+ "EAATOD DESTON SIHFEE NEHRIS "
				+ "TIBRAL BOQMAJ ZENVAD FIXROA"
		);
	}
	
	private static void entrerPlateau(Scanner sc) {
		System.out.println("Entrez le contenu du plateau sous la forme : \"ABCD EFGH IJKL MNOP\" "
				+ "(un espace = une ligne) : ");
		String lettres = sc.next()+" "+sc.next()+" "+sc.next()+" "+sc.next();
		
		System.out.print(System.getProperty("line.separator"));
		System.out.println("Chargement...");
		System.out.print(System.getProperty("line.separator"));
		
		Plateau.créerPlateauPersonnalisé(lettres);
	}

	private static void afficherPlateau() {
		for(int i = 0; i < Plateau.getNbLignes(); ++i) {
			for(int j = 0; j < Plateau.getNbLignes(); ++j) {
				System.out.print(Plateau.getPlateau()[i][j]+" ");
			}
			System.out.print(System.getProperty("line.separator"));
		}
		System.out.print(System.getProperty("line.separator"));
	}
	
	private static void entrerMots(Scanner sc) {
		for(int i = 0; i < Boggle.getNbJoueurs(); ++i) {
			System.out.println("Entrer les mots trouvés par "+ Boggle.getJoueurs().get(i).getNom() + 
					" (tapez * quand vous avez terminé): ");
			String mot;
			do {
				mot = sc.next();
				if(!mot.equals("*"))
					Boggle.getJoueurs().get(i).entrerMot(mot);
			}while(!mot.equals("*"));
		}
	}
	
	/**
	 * Affiche tous les mots disponibles dans le plateau
	 */
	private static void afficherSolution() {
		System.out.print(System.getProperty("line.separator"));
		System.out.println("Solution : ");
		for(int i = 0; i < Plateau.getMots().size(); ++i) {
			System.out.print(Plateau.getMots().get(i)+" ");
			if(i % 6 == 0) {
				System.out.print(System.getProperty("line.separator"));
			}
		}
		System.out.print(System.getProperty("line.separator"));
	}
	
	private static void afficherMotsValides() {
		for(int i = 0; i < Boggle.getNbJoueurs(); ++i) {
			System.out.print("Mots validés pour "+ Boggle.getJoueurs().get(i).getNom() + " : ");
			for(int j = 0; j < Boggle.getJoueurs().get(i).getMotsValides().size(); ++j) {
				if(j%6 == 0) {
					System.out.print(System.getProperty("line.separator"));
				}
				System.out.print(Boggle.getJoueurs().get(i).getMotsValides().get(j)+" ");
			}
			System.out.print(System.getProperty("line.separator"));
		}
		
	}
	
	private static void afficherScores() {
		int meilleurScore = 0;
		for(int i = 0; i < Boggle.getNbJoueurs(); ++i) {
			
			System.out.println("Score "+ Boggle.getJoueurs().get(i).getNom() + " : "
					+ Boggle.getJoueurs().get(i).getPoints());
			
			if(Boggle.getJoueurs().get(i).getPoints() > meilleurScore) {
				meilleurScore = Boggle.getJoueurs().get(i).getPoints();
			}
		}
		System.out.print(System.getProperty("line.separator"));
		Stack<String> vainqueurs = new Stack<String>();
		for(int i = 0; i < Boggle.getNbJoueurs(); ++i) {
			if(Boggle.getJoueurs().get(i).getPoints() == meilleurScore) {
				vainqueurs.push(Boggle.getJoueurs().get(i).getNom());
			}
		}
		
		int taille = vainqueurs.size();
		for(int i = 0; i < taille; ++i) {
			System.out.print(vainqueurs.peek());
			if(taille > 1) {
				System.out.print(", ");
			}
			else {
				System.out.print(" ");
			}
			vainqueurs.pop();
		}
		if(taille > 1) {
			System.out.println("ont gagné ex-aequo.");
		}
		else {
			System.out.println("a gagné.");
		}
		
	}
	

}
