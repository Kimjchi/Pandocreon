package cartes.capacités;

import joueurs.Joueur;
import partie.Partie;

//Pour croyant  Revenant, Diplomates et Deus Ex Bouleversement
public class RelancerDé implements Capacité {

	public void sacrifier(Joueur j) {
		System.out.println("Le dé est relancé.");
		Partie.getInstance().DébuterTour();
	}

}
