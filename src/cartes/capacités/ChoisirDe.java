package cartes.capacités;

import partie.Partie;
import java.util.*;

import joueurs.Joueur;

//Messie
public class ChoisirDe implements Capacité {
	public void sacrifier(Joueur j) {
		int choix = j.séléctionnerCosmogonie();
		for (Iterator<Joueur> it = Partie.getInstance().getJoueurs().iterator(); it.hasNext();) {
			Joueur joueur = it.next();
			joueur.gagnerPointsAction(choix);
		}
		Partie.getInstance().setNumTour(Partie.getInstance().getNumTour() + 1);
	}
}
