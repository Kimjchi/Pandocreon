package cartes.capacit�s;

import cartes.Origine;
import joueurs.Joueur;
import partie.Partie;

public class AnnulerCapacit� implements Capacit� {

	private Origine origine1;
	private Origine origine2;

	public AnnulerCapacit�(Origine origine1, Origine origine2) {
		this.origine1 = origine1;
		this.origine2 = origine2;
	}

	public void sacrifier(Joueur j) {
		if (j.isEstR�el()) {

			if (Partie.getInstance().getCarteActivee().getOrigine() == this.origine1
					|| Partie.getInstance().getCarteActivee().getOrigine() == this.origine2) {
				j.setCapaciteAnnulee(true);
				System.out.println("La capacit� de la carte est annul�e");
			} else if (this.origine1 == cartes.Origine.Nul && this.origine2 == cartes.Origine.Nul) {
				j.setCapaciteAnnulee(true);
				System.out.println("La capacit� de la carte est annul�e");
			} else {
				System.out.println("Vous ne pouvez pas annuler cette carte");
			}
		}
	}
}
