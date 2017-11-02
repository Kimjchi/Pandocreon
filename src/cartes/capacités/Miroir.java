package cartes.capacités;

import joueurs.Joueur;
import partie.Partie;

public class Miroir implements Capacité {
	public void sacrifier(Joueur j) {
		if (j.isEstRéel()) {
			j.setCapaciteAnnulee(true);
			Partie.getInstance().setjEnCours(Partie.getInstance().getjIntervenant());
			Partie.getInstance().getCarteActivee().getCapacité().sacrifier(j);
		}
	}
}
