// Pour Démons, Moines et Esprit

package cartes.capacités;

import cartes.Origine;
import joueurs.Joueur;

public class GagnerPointAction implements Capacité {

	private Origine origine;

	public GagnerPointAction(Origine origine) {
		this.origine = origine;
	}

	// Octrois un point d'action de l'origine concernée.
	public void sacrifier(Joueur j) {
		if (j.isPeutGagnerPointAction()) {
			if (j.isEstRéel()) {
				System.out.println("Les pouvoirs de votre croyant vous octroient un point d'action.");
			}
			switch (this.origine) {
			case Jour:
				j.setPointsJour(1);
				break;
			case Nuit:
				j.setPointsNuit(1);
				break;
			case Néant:
				j.setPointsNeant(1);
				break;
			default:
				break;
			}
			if (!j.isEstRéel()) {
				System.out.println(j.getNom() + " gagne un point d'action " + this.origine + ".");
			}
			System.out.println();

		} else {
			System.out.println(
					"Le joueur est sous l'effet d'un sort l'empechant de gagner des points d'action jusqu'à la fin du tour.");
		}

	}

}
