package dictionnaire;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Composantes du dictionnaire
 * @author Kilian CHOLLET, Antoine AUDRIN
 * @version 1
 */
public class Dictionnaire {
	// Mots du fichier texte dico.txt mémorisés dans la mémoire vive (optimisation)
	private static ArrayList<String> mots;

	/**
	 * @param url (url de fichier contentant les mots du dictionnaire)
	 * @return false si la lecture du fichier est infructueuse
	 */
	public static boolean setMots(String url) {
	      	try {
	      		mots = new ArrayList<String>();
				InputStream ips = new FileInputStream(new File(url)); 
				InputStreamReader ipsr=new InputStreamReader(ips);
				BufferedReader br=new BufferedReader(ipsr);
				String ligne;
				do {
					ligne = br.readLine();
					mots.add(ligne);
				} while (ligne != null);
				br.close();
	       
	      	} catch (FileNotFoundException e) {

	    	  	e.printStackTrace();
	    	  	return false;

	      	} catch (IOException e) {
	    	  
	    	  	e.printStackTrace();
	    	  	return false;

	      	}
	      	
	      	return true;
	   }
	
	/**
	 * Retourne les mots du dictionnaire
	 * @return mots
	 */
	public static ArrayList<String> getMots() {
		return mots;
	}
	
	/**
	 * Indique si un mot existe dans le dictionnaire
	 * @param mot
	 * @return true si le mot existe dans le dictionnaire
	 */
	public static boolean existe(String mot) {
		mot = mot.toUpperCase(); 
		return mots.contains(mot);
	}
}
