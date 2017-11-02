package cartes.capacit�s;

import joueurs.Joueur;
import partie.Partie;

//Divinit� Gorpa
public class VolerPointActionEmpecherGagner implements Capacit� {

	public void sacrifier(Joueur j) {
		int adversaire = j.choisirAdversaire();
		if (adversaire != -1) {
			System.out.println("Gorpa d�cide de se jouer de "
					+ Partie.getInstance().getJoueurs().get(adversaire).getNom() + " et vole ses Points d'Action. "
					+ Partie.getInstance().getJoueurs().get(adversaire).getNom()
					+ " n'en gagnera plus jusqu'� la fin du tour.");
			if (j.isPeutGagnerPointAction()) {
				j.setPointsJour(Partie.getInstance().getJoueurs().get(adversaire).getPointsJour());
				j.setPointsNuit(Partie.getInstance().getJoueurs().get(adversaire).getPointsNuit());
				j.setPointsNeant(Partie.getInstance().getJoueurs().get(adversaire).getPointsNeant());
				Partie.getInstance().getJoueurs().get(adversaire).setPointsJour(0);
				Partie.getInstance().getJoueurs().get(adversaire).setPointsNeant(0);
				Partie.getInstance().getJoueurs().get(adversaire).setPointsNuit(0);
			} else {
				System.out.println(
						"Le joueur est sous l'effet d'un sort l'empechant de gagner des points d'action jusqu'� la fin du tour.");

			}
			Partie.getInstance().getJoueurs().get(adversaire).setPeutGagnerPointAction(false);
		}
	}
}
