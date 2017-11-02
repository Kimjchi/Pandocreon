package cartes.capacit�s;

import joueurs.Joueur;
import partie.Partie;

//Illusioniste
public class VolerCapacit�Adversaire implements Capacit� {

	public void sacrifier(Joueur j) {
		System.out
				.println("L'illusioniste copie la capacit� d'un croyant d'une divinit� adverse. La capacit� s'active");
		int adversaire = j.choisirAdversaireAvecGuideSprituels();
		int guide = j.choisirGuideAdversaire(adversaire);
		int croyant = j.s�lectionnerCroyant(adversaire, guide);
		if (adversaire != -1 && guide != -1 && croyant != -1) {
			Partie.getInstance().getJoueurs().get(adversaire).getCarteDevant().get(guide).getCroyantAttache()
					.get(croyant).getCapacit�().sacrifier(Partie.getInstance().getJoueurs().get(adversaire));
		}

	}
}
