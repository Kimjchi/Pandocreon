package cartes.capacit�s;

import java.util.Iterator;



//Cartes associ�es
//Guide Martyr

import joueurs.Joueur;
import partie.Partie;

public class Capacit�Apocalypse implements Capacit� {

	public void sacrifier(Joueur j) {
		// Apocalypse apocalypse = new Apocalypse(0, null, null, null);
		// apocalypse.activerApocalypse();
		Joueur j1 = Partie.getInstance().getJoueurs().get(0);
		int nbPri�resMax = 0;
		int nbPri�resMin = 100;
		// AJOUTER A LA DEFAUSSE
		
		// Cas o� il y a 2 ou 3 joueurs.
		if (Partie.getInstance().getNombreJoueurs() <= 3) {

			// On trouve le joueur qui a le plus de pri�res.
			for (Iterator<Joueur> it = Partie.getInstance().getJoueurs().iterator(); it.hasNext();) {
				Joueur jEnCours = it.next();
				if (jEnCours.getnbPri�res() > nbPri�resMax) {
					j1 = jEnCours;
					nbPri�resMax = jEnCours.getnbPri�res();
				}
			}

			// On v�rifie qu'il n'y a pas deux joueurs qui ont le maximum de
			// pri�res. Si non, la partie est finie, si oui, la carte est
			// d�fauss�e.
			boolean ilYAUnGagnant = true;
			for (Iterator<Joueur> it = Partie.getInstance().getJoueurs().iterator(); it.hasNext();) {
				Joueur jEnCours = it.next();
				if ((jEnCours.getnbPri�res() == nbPri�resMax) && (jEnCours != j1)) {
					ilYAUnGagnant = false;
				}
			}

			if (ilYAUnGagnant) {
				System.out.println(j1.getNom() + " a gagn� la partie en " + Partie.getInstance().getNumTour() + " tours. \nBravo !");
				Partie.getInstance().terminerPartie();
			}

			else {
				System.out.println("L'effet de la carte est nul car deux joueurs ont le plus de pri�res");
			}
		}

		// cas o� il y a 4 joueurs ou plus.
		else {

			// On trouve le joueur qui a le moins de pri�res.
			for (Iterator<Joueur> it = Partie.getInstance().getJoueurs().iterator(); it.hasNext();) {
				Joueur jEnCours = it.next();
				if (jEnCours.getnbPri�res() < nbPri�resMin) {
					j1 = jEnCours;
					nbPri�resMin = jEnCours.getnbPri�res();
				}
			}

			// On v�rifie qu'il n'y a pas deux joueurs qui ont le minimum de
			// pri�res. Si non, le joueur est �limin�, si oui, la carte est
			// d�fauss�e.
			boolean ilYAUnElimin� = true;
			for (Iterator<Joueur> it = Partie.getInstance().getJoueurs().iterator(); it.hasNext();) {
				Joueur jEnCours = it.next();
				if ((jEnCours.getnbPri�res() == nbPri�resMin) && (jEnCours != j1)) {
					ilYAUnElimin� = false;
				}
			}

			if (ilYAUnElimin�) {
				System.out.println(j1.getNom() + " est �limin� !");
				for (int k = 0; k < j1.getNbGuidesSprituels(); k++) {
					j1.defausserGuide(k);
				}
				Partie.getInstance().getJoueurs().remove(j1);
				Partie.getInstance().setNombreJoueurs();
				Partie.getInstance().getJoueurs().add(1, j);
				Partie.getInstance().getJoueurs().remove(j);
			}

			else {
				System.out.println("L'effet de la carte est nul car deux joueurs ont le plus de pri�res");
			}
		}
	}
}
