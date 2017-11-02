package cartes.capacit�s;

import joueurs.Joueur;
import partie.Partie;

public class Miroir implements Capacit� {
	public void sacrifier(Joueur j) {
		if (j.isEstR�el()) {
			j.setCapaciteAnnulee(true);
			Partie.getInstance().setjEnCours(Partie.getInstance().getjIntervenant());
			Partie.getInstance().getCarteActivee().getCapacit�().sacrifier(j);
		}
	}
}
