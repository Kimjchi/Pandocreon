package cartes.capacités;

import joueurs.Joueur;
import partie.Partie;

//Pillards
public class VolerPointAction implements Capacité {

	public void sacrifier(Joueur j) {
		if (j.isPeutGagnerPointAction()) {

			int adversaire = j.choisirAdversaire();
			if (adversaire != -1) {
				System.out.println("Les pillards volent les point d'action de "
						+ Partie.getInstance().getJoueurs().get(adversaire).getNom() + "au profits de " + j.getNom());
				j.setPointsJour(Partie.getInstance().getJoueurs().get(adversaire).getPointsJour());
				j.setPointsNuit(Partie.getInstance().getJoueurs().get(adversaire).getPointsNuit());
				j.setPointsNeant(Partie.getInstance().getJoueurs().get(adversaire).getPointsNeant());
				Partie.getInstance().getJoueurs().get(adversaire).setPointsJour(0);
				Partie.getInstance().getJoueurs().get(adversaire).setPointsNeant(0);
				Partie.getInstance().getJoueurs().get(adversaire).setPointsNuit(0);
			} else {
				System.out.println(
						"Le joueur est sous l'effet d'un sort l'empechant de gagner des points d'action jusqu'à la fin du tour.");

			}
		}
	}
}
