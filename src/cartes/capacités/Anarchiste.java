package cartes.capacités;

import joueurs.Joueur;
import partie.Partie;

public class Anarchiste implements Capacité {
	public void sacrifier(Joueur j) {
		int adversaire = j.choisirAdversaireAvecGuideSprituels();
		int guide = j.choisirGuideAdversaire(adversaire);
		if (adversaire != -1 && guide != -1) {
			if (Partie.getInstance().getJoueurs().get(adversaire).getCarteDevant().get(guide).isProtege()) {
				System.out.println("Le guide est protégé");
			} else {
				if ((Partie.getInstance().getJoueurs().get(adversaire).getDivinité().getDogme(0) != cartes.Dogme.Chaos
						&& Partie.getInstance().getJoueurs().get(adversaire).getDivinité()
								.getDogme(1) != cartes.Dogme.Chaos
						&& Partie.getInstance().getJoueurs().get(adversaire).getDivinité()
								.getDogme(2) != cartes.Dogme.Chaos)
						|| (Partie.getInstance().getJoueurs().get(adversaire).getCarteDevant().get(guide)
								.getDogme(0) != cartes.Dogme.Chaos
								&& Partie.getInstance().getJoueurs().get(adversaire).getCarteDevant().get(guide)
										.getDogme(1) != cartes.Dogme.Chaos)) {

					System.out.println("Le Guide Spirituel "
							+ Partie.getInstance().getJoueurs().get(adversaire).getCarteDevant().get(guide).getNom()
							+ " a été sacrfifié. L'effet s'active.");
					Partie.getInstance().getJoueurs().get(adversaire).getCarteDevant().get(guide).getCapacité()
							.sacrifier(Partie.getInstance().getJoueurs().get(adversaire));
					Partie.getInstance().getJoueurs().get(adversaire).defausserGuide(guide);
				}

				else {
					System.out.println("Pas les bons dogmes pour l'activation de la carte.");
				}

			}
		}

	}
}
