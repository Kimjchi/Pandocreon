package cartes.capacités;

import java.util.Iterator;



//Cartes associées
//Guide Martyr

import joueurs.Joueur;
import partie.Partie;

public class CapacitéApocalypse implements Capacité {

	public void sacrifier(Joueur j) {
		// Apocalypse apocalypse = new Apocalypse(0, null, null, null);
		// apocalypse.activerApocalypse();
		Joueur j1 = Partie.getInstance().getJoueurs().get(0);
		int nbPrièresMax = 0;
		int nbPrièresMin = 100;
		// AJOUTER A LA DEFAUSSE
		
		// Cas où il y a 2 ou 3 joueurs.
		if (Partie.getInstance().getNombreJoueurs() <= 3) {

			// On trouve le joueur qui a le plus de prières.
			for (Iterator<Joueur> it = Partie.getInstance().getJoueurs().iterator(); it.hasNext();) {
				Joueur jEnCours = it.next();
				if (jEnCours.getnbPrières() > nbPrièresMax) {
					j1 = jEnCours;
					nbPrièresMax = jEnCours.getnbPrières();
				}
			}

			// On vérifie qu'il n'y a pas deux joueurs qui ont le maximum de
			// prières. Si non, la partie est finie, si oui, la carte est
			// défaussée.
			boolean ilYAUnGagnant = true;
			for (Iterator<Joueur> it = Partie.getInstance().getJoueurs().iterator(); it.hasNext();) {
				Joueur jEnCours = it.next();
				if ((jEnCours.getnbPrières() == nbPrièresMax) && (jEnCours != j1)) {
					ilYAUnGagnant = false;
				}
			}

			if (ilYAUnGagnant) {
				System.out.println(j1.getNom() + " a gagné la partie en " + Partie.getInstance().getNumTour() + " tours. \nBravo !");
				Partie.getInstance().terminerPartie();
			}

			else {
				System.out.println("L'effet de la carte est nul car deux joueurs ont le plus de prières");
			}
		}

		// cas où il y a 4 joueurs ou plus.
		else {

			// On trouve le joueur qui a le moins de prières.
			for (Iterator<Joueur> it = Partie.getInstance().getJoueurs().iterator(); it.hasNext();) {
				Joueur jEnCours = it.next();
				if (jEnCours.getnbPrières() < nbPrièresMin) {
					j1 = jEnCours;
					nbPrièresMin = jEnCours.getnbPrières();
				}
			}

			// On vérifie qu'il n'y a pas deux joueurs qui ont le minimum de
			// prières. Si non, le joueur est éliminé, si oui, la carte est
			// défaussée.
			boolean ilYAUnEliminé = true;
			for (Iterator<Joueur> it = Partie.getInstance().getJoueurs().iterator(); it.hasNext();) {
				Joueur jEnCours = it.next();
				if ((jEnCours.getnbPrières() == nbPrièresMin) && (jEnCours != j1)) {
					ilYAUnEliminé = false;
				}
			}

			if (ilYAUnEliminé) {
				System.out.println(j1.getNom() + " est éliminé !");
				for (int k = 0; k < j1.getNbGuidesSprituels(); k++) {
					j1.defausserGuide(k);
				}
				Partie.getInstance().getJoueurs().remove(j1);
				Partie.getInstance().setNombreJoueurs();
				Partie.getInstance().getJoueurs().add(1, j);
				Partie.getInstance().getJoueurs().remove(j);
			}

			else {
				System.out.println("L'effet de la carte est nul car deux joueurs ont le plus de prières");
			}
		}
	}
}
