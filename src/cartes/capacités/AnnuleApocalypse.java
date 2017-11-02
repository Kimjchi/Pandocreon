package cartes.capacités;

import joueurs.Joueur;
import partie.Partie;

public class AnnuleApocalypse implements Capacité {
	public void sacrifier(Joueur j) {
		if (j.isEstRéel()) {
			if (Partie.getInstance().getCarteActivee().getType() == cartes.Type.Apocalypse) {
				j.setCapaciteAnnulee(true);
			} else {
				System.out.println("Vous ne pouvez annuler que les cartes Apocalypse");
			}
		}
	}
}
