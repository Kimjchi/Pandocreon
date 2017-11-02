package cartes.capacit�s;

import joueurs.Joueur;

public class Stase implements Capacit� {
	public void sacrifier(Joueur j) {
		int numGuide = j.choisirUnDeSesGuides();
		if (numGuide != -1) {
			j.getCarteDevant().get(numGuide).setProtege(true);
			j.setGuideProtege(true);
			System.out.println(
					j.getCarteDevant().get(numGuide).getNom() + " est prot�g�e");
		}
	}
}
