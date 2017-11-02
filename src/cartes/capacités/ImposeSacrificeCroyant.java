package cartes.capacit�s;

import joueurs.Joueur;
import partie.Partie;

public class ImposeSacrificeCroyant implements Capacit� {

	public void sacrifier(Joueur j) {
		int adversaire = j.choisirAdversaireAvecGuideSprituels();
		int guide = j.choisirGuideAdversaire(adversaire);
		int croyant = j.s�lectionnerCroyant(adversaire, guide);
		if (adversaire != -1 && guide != -1 && croyant != -1 && Partie.getInstance().getJoueurs().get(adversaire) != j) {
			if (Partie.getInstance().getJoueurs().get(adversaire).getCarteDevant().get(guide).isProtege()) {
				System.out.println("Le guide est prot�g� et ses croyants aussi");
			} else {
				System.out.println("Le Guide Spirituel "
						+ Partie.getInstance().getJoueurs().get(adversaire).getCarteDevant().get(guide).getNom()
						+ " a �t� sacrifi�. L'effet s'active.");
				Partie.getInstance().getJoueurs().get(adversaire).getCarteDevant().get(guide).getCroyantAttache()
						.get(croyant).getCapacit�().sacrifier(Partie.getInstance().getJoueurs().get(adversaire));
				Partie.getInstance().getJoueurs().get(adversaire).defausserCroyant(guide, croyant);
			}
		}
	}
}
