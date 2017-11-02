package cartes.capacités;

import joueurs.Joueur;
import partie.Partie;

//Illusioniste
public class VolerCapacitéAdversaire implements Capacité {

	public void sacrifier(Joueur j) {
		System.out
				.println("L'illusioniste copie la capacité d'un croyant d'une divinité adverse. La capacité s'active");
		int adversaire = j.choisirAdversaireAvecGuideSprituels();
		int guide = j.choisirGuideAdversaire(adversaire);
		int croyant = j.sélectionnerCroyant(adversaire, guide);
		if (adversaire != -1 && guide != -1 && croyant != -1) {
			Partie.getInstance().getJoueurs().get(adversaire).getCarteDevant().get(guide).getCroyantAttache()
					.get(croyant).getCapacité().sacrifier(Partie.getInstance().getJoueurs().get(adversaire));
		}

	}
}
