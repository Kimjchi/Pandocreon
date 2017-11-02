package cartes.capacit�s;

import joueurs.Joueur;

//Phoenix

public class ActiverEffetSansDetruire implements Capacit� {

	public void sacrifier(Joueur j) {
		int guide = -1;
		System.out.println(
				"Le Phoenix permet de d'activer l'effet d'un croyant ou d'un guide spirituel puis de le ramener � la vie !");
		int choix = j.choisirGuideOuCroyant();
		switch (choix) {
		case 0:
			guide = j.choisirUnDeSesGuides();
			if (guide != -1) {
				j.getCarteDevant().get(guide).getCapacit�().sacrifier(j);
			}
			break;
		case 1:
			guide = j.choisirUnDeSesGuides();
			if (guide != -1) {
				int divinit� = j.getNumArray();
				int croyant = j.s�lectionnerCroyant(divinit�, guide);
				j.getCarteDevant().get(guide).getCroyantAttache().get(croyant)
						.getCapacit�().sacrifier(j);
			}

		}
		if (guide == -1) {
			System.out.println("Il ne se passe rien...");
		}

	}

}
