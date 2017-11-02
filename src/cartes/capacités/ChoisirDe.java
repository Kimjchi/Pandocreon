package cartes.capacit�s;

import partie.Partie;
import java.util.*;

import joueurs.Joueur;

//Messie
public class ChoisirDe implements Capacit� {
	public void sacrifier(Joueur j) {
		int choix = j.s�l�ctionnerCosmogonie();
		for (Iterator<Joueur> it = Partie.getInstance().getJoueurs().iterator(); it.hasNext();) {
			Joueur joueur = it.next();
			joueur.gagnerPointsAction(choix);
		}
		Partie.getInstance().setNumTour(Partie.getInstance().getNumTour() + 1);
	}
}
