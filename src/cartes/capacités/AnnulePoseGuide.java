package cartes.capacit�s;

import joueurs.Joueur;
import partie.Partie;

//Romtec
public class AnnulePoseGuide implements Capacit� {
	public void sacrifier(Joueur j) {
		if (j.isEstR�el()) {
			if (Partie.getInstance().getCarteActivee().getType() == cartes.Type.GuideSpirituel) {
				j.setPeutPoserGuide(false);
			} else {
				System.out.println("Vous ne pouvez emp�cher que la pose d'un guide");
			}
		}
	}
}
