package cartes.capacit�s;

import partie.Partie;
import cartes.MilieuDeTable;
import joueurs.Joueur;

//carte : Lycanthrope
public class DefausseGuideCroyantAuCentre implements Capacit� {

	public void sacrifier(Joueur j) {
		int adversaire = j.choisirAdversaireAvecGuideSprituels();
		int guide = j.choisirGuideAdversaire(adversaire);
		if (adversaire != -1 && guide != -1) {
			System.out.println("Le lycanthrope a tu� le guide "
					+ Partie.getInstance().getJoueurs().get(adversaire).getCarteDevant().get(guide).getNom()
					+ ", les croyants sont revenus au centre de la table.");
			MilieuDeTable.getInstance().getListeCroyants().addAll(
					Partie.getInstance().getJoueurs().get(adversaire).getCarteDevant().get(guide).getCroyantAttache());
			Partie.getInstance().getJoueurs().get(adversaire).getCarteDevant().remove(guide);

		}
		if (guide == -1) {
			System.out.println("La capacit� ne peut avoir d'effet.");
		}

	}
}
