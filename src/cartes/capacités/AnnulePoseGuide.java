package cartes.capacités;

import joueurs.Joueur;
import partie.Partie;

//Romtec
public class AnnulePoseGuide implements Capacité {
	public void sacrifier(Joueur j) {
		if (j.isEstRéel()) {
			if (Partie.getInstance().getCarteActivee().getType() == cartes.Type.GuideSpirituel) {
				j.setPeutPoserGuide(false);
			} else {
				System.out.println("Vous ne pouvez empêcher que la pose d'un guide");
			}
		}
	}
}
