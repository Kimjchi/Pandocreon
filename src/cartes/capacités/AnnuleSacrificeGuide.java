package cartes.capacités;

import joueurs.Joueur;
import partie.Partie;

public class AnnuleSacrificeGuide implements Capacité {
	public void sacrifier(Joueur j) {
		if (j.isEstRéel()) {
			if (Partie.getInstance().getCarteActivee().getType() == cartes.Type.GuideSpirituel) {
				j.setCapaciteAnnulee(true);
			} else {
				System.out.println("Vous ne pouvez annuler que le sacrifice d'un guide");
			}
		}
	}
}
