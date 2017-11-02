package cartes.capacités;

import joueurs.Joueur;

//Phoenix

public class ActiverEffetSansDetruire implements Capacité {

	public void sacrifier(Joueur j) {
		int guide = -1;
		System.out.println(
				"Le Phoenix permet de d'activer l'effet d'un croyant ou d'un guide spirituel puis de le ramener à la vie !");
		int choix = j.choisirGuideOuCroyant();
		switch (choix) {
		case 0:
			guide = j.choisirUnDeSesGuides();
			if (guide != -1) {
				j.getCarteDevant().get(guide).getCapacité().sacrifier(j);
			}
			break;
		case 1:
			guide = j.choisirUnDeSesGuides();
			if (guide != -1) {
				int divinité = j.getNumArray();
				int croyant = j.sélectionnerCroyant(divinité, guide);
				j.getCarteDevant().get(guide).getCroyantAttache().get(croyant)
						.getCapacité().sacrifier(j);
			}

		}
		if (guide == -1) {
			System.out.println("Il ne se passe rien...");
		}

	}

}
