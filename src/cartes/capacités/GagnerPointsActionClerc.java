package cartes.capacit�s;

import joueurs.Joueur;

// Pour le clerc
public class GagnerPointsActionClerc implements Capacit� {

	public void sacrifier(Joueur j) {
		int i = j.getNbCroyants();
		if (j.isPeutGagnerPointAction()) {
			if (i == 0) {
				System.out.println("Sans croyants, pas de points d'action.");
			} else {
				int cosmo = j.s�l�ctionnerCosmogonie();
				switch (cosmo) {
				case 1:
					j.setPointsJour(i);
					break;
				case 2:
					j.setPointsNuit(i);
					break;
				case 3:
					j.setPointsNeant(i);
					break;
				}

			}

		} else {
			System.out.println(
					"Le joueur est sous l'effet d'un sort l'empechant de gagner des points d'action jusqu'� la fin du tour.");

		}

	}

}
