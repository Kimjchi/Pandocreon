package cartes.capacités;

import cartes.Origine;
import joueurs.Joueur;
import partie.Partie;

public class AnnulerCapacité implements Capacité {

	private Origine origine1;
	private Origine origine2;

	public AnnulerCapacité(Origine origine1, Origine origine2) {
		this.origine1 = origine1;
		this.origine2 = origine2;
	}

	public void sacrifier(Joueur j) {
		if (j.isEstRéel()) {

			if (Partie.getInstance().getCarteActivee().getOrigine() == this.origine1
					|| Partie.getInstance().getCarteActivee().getOrigine() == this.origine2) {
				j.setCapaciteAnnulee(true);
				System.out.println("La capacité de la carte est annulée");
			} else if (this.origine1 == cartes.Origine.Nul && this.origine2 == cartes.Origine.Nul) {
				j.setCapaciteAnnulee(true);
				System.out.println("La capacité de la carte est annulée");
			} else {
				System.out.println("Vous ne pouvez pas annuler cette carte");
			}
		}
	}
}
