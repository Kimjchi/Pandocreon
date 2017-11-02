package cartes.capacités;

import joueurs.Joueur;
import partie.Partie;

public class ImposeSacrificeCroyant implements Capacité {

	public void sacrifier(Joueur j) {
		int adversaire = j.choisirAdversaireAvecGuideSprituels();
		int guide = j.choisirGuideAdversaire(adversaire);
		int croyant = j.sélectionnerCroyant(adversaire, guide);
		if (adversaire != -1 && guide != -1 && croyant != -1 && Partie.getInstance().getJoueurs().get(adversaire) != j) {
			if (Partie.getInstance().getJoueurs().get(adversaire).getCarteDevant().get(guide).isProtege()) {
				System.out.println("Le guide est protégé et ses croyants aussi");
			} else {
				System.out.println("Le Guide Spirituel "
						+ Partie.getInstance().getJoueurs().get(adversaire).getCarteDevant().get(guide).getNom()
						+ " a été sacrifié. L'effet s'active.");
				Partie.getInstance().getJoueurs().get(adversaire).getCarteDevant().get(guide).getCroyantAttache()
						.get(croyant).getCapacité().sacrifier(Partie.getInstance().getJoueurs().get(adversaire));
				Partie.getInstance().getJoueurs().get(adversaire).defausserCroyant(guide, croyant);
			}
		}
	}
}
