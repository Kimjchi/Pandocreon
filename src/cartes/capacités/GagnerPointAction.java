// Pour D�mons, Moines et Esprit

package cartes.capacit�s;

import cartes.Origine;
import joueurs.Joueur;

public class GagnerPointAction implements Capacit� {

	private Origine origine;

	public GagnerPointAction(Origine origine) {
		this.origine = origine;
	}

	// Octrois un point d'action de l'origine concern�e.
	public void sacrifier(Joueur j) {
		if (j.isPeutGagnerPointAction()) {
			if (j.isEstR�el()) {
				System.out.println("Les pouvoirs de votre croyant vous octroient un point d'action.");
			}
			switch (this.origine) {
			case Jour:
				j.setPointsJour(1);
				break;
			case Nuit:
				j.setPointsNuit(1);
				break;
			case N�ant:
				j.setPointsNeant(1);
				break;
			default:
				break;
			}
			if (!j.isEstR�el()) {
				System.out.println(j.getNom() + " gagne un point d'action " + this.origine + ".");
			}
			System.out.println();

		} else {
			System.out.println(
					"Le joueur est sous l'effet d'un sort l'empechant de gagner des points d'action jusqu'� la fin du tour.");
		}

	}

}
