package cartes.capacit�s;

import joueurs.Joueur;
import partie.Partie;

public class AnnuleApocalypse implements Capacit� {
	public void sacrifier(Joueur j) {
		if (j.isEstR�el()) {
			if (Partie.getInstance().getCarteActivee().getType() == cartes.Type.Apocalypse) {
				j.setCapaciteAnnulee(true);
			} else {
				System.out.println("Vous ne pouvez annuler que les cartes Apocalypse");
			}
		}
	}
}
