package cartes.capacit�s;

import joueurs.Joueur;
import partie.Partie;

//Pour croyant  Revenant, Diplomates et Deus Ex Bouleversement
public class RelancerD� implements Capacit� {

	public void sacrifier(Joueur j) {
		System.out.println("Le d� est relanc�.");
		Partie.getInstance().D�buterTour();
	}

}
