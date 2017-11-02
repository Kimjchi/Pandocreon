package cartes.capacit�s;

import joueurs.Joueur;
import partie.Partie;

public class Anarchiste implements Capacit� {
	public void sacrifier(Joueur j) {
		int adversaire = j.choisirAdversaireAvecGuideSprituels();
		int guide = j.choisirGuideAdversaire(adversaire);
		if (adversaire != -1 && guide != -1) {
			if (Partie.getInstance().getJoueurs().get(adversaire).getCarteDevant().get(guide).isProtege()) {
				System.out.println("Le guide est prot�g�");
			} else {
				if ((Partie.getInstance().getJoueurs().get(adversaire).getDivinit�().getDogme(0) != cartes.Dogme.Chaos
						&& Partie.getInstance().getJoueurs().get(adversaire).getDivinit�()
								.getDogme(1) != cartes.Dogme.Chaos
						&& Partie.getInstance().getJoueurs().get(adversaire).getDivinit�()
								.getDogme(2) != cartes.Dogme.Chaos)
						|| (Partie.getInstance().getJoueurs().get(adversaire).getCarteDevant().get(guide)
								.getDogme(0) != cartes.Dogme.Chaos
								&& Partie.getInstance().getJoueurs().get(adversaire).getCarteDevant().get(guide)
										.getDogme(1) != cartes.Dogme.Chaos)) {

					System.out.println("Le Guide Spirituel "
							+ Partie.getInstance().getJoueurs().get(adversaire).getCarteDevant().get(guide).getNom()
							+ " a �t� sacrfifi�. L'effet s'active.");
					Partie.getInstance().getJoueurs().get(adversaire).getCarteDevant().get(guide).getCapacit�()
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
