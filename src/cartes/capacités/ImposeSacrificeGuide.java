package cartes.capacités;

//Carte :
//Intégriste,anarchiste, devin,

import partie.Partie;
import cartes.Dogme;
import joueurs.Joueur;

public class ImposeSacrificeGuide implements Capacité {
	private Dogme dogme1;
	private Dogme dogme2;

	public ImposeSacrificeGuide(Dogme dogme1, Dogme dogme2) {
		this.dogme1 = dogme1;
		this.dogme2 = dogme2;
	}

	public void sacrifier(Joueur j) {
		int adversaire = j.choisirAdversaireAvecGuideSprituels();
		int guide = j.choisirGuideAdversaire(adversaire);
		if (adversaire != -1 && guide != -1) {
			if (Partie.getInstance().getJoueurs().get(adversaire).getCarteDevant().get(guide).isProtege()) {
				System.out.println("Le guide est protégé");
			} else {
				if (Partie.getInstance().getJoueurs().get(adversaire).getCarteDevant().get(guide).getDogme(0) == dogme1
						|| Partie.getInstance().getJoueurs().get(adversaire).getCarteDevant().get(guide)
								.getDogme(0) == dogme2
						|| Partie.getInstance().getJoueurs().get(adversaire).getCarteDevant().get(guide)
								.getDogme(1) == dogme1
						|| Partie.getInstance().getJoueurs().get(adversaire).getCarteDevant().get(guide)
								.getDogme(1) == dogme2) {
					System.out.println("Le Guide Spirituel "
							+ Partie.getInstance().getJoueurs().get(adversaire).getCarteDevant().get(guide).getNom()
							+ " a été sacrfifié. L'effet s'active.");
					Partie.getInstance().getJoueurs().get(adversaire).getCarteDevant().get(guide).getCapacité()
							.sacrifier(Partie.getInstance().getJoueurs().get(adversaire));
					Partie.getInstance().getJoueurs().get(adversaire).defausserGuide(guide);
				} else {
					System.out.println("Vous n'avez pas les bons dogmes");
				}
			}
		}

	}
}
