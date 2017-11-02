package cartes.capacit�s;

import cartes.Croyant;
import joueurs.Joueur;

import cartes.MilieuDeTable;

import partie.Partie;

//Inquisition
public class Inquisition implements Capacit� {

	public void sacrifier(Joueur j) {

		int adversaire = j.choisirAdversaireAvecGuideSprituels();
		int guideAdverse = j.choisirGuideAdversaire(adversaire);
		int guideAlli� = j.choisirUnDeSesGuides();// s�lectionnerDivinit�AvecGuideSpirituels()
		if (adversaire != -1 && guideAdverse != -1 && guideAlli� != -1) {
			int r�sultat = Partie.getInstance().lancerDe();
			switch (r�sultat) {
			case 1:
				System.out.println("Le r�sultat est jour. L'inquisition est un succ�s ! "
						+ Partie.getInstance().getJoueurs().get(adversaire).getCarteDevant().get(guideAdverse).getNom()
						+ " a �t� supprim�.");

				for (Croyant elem : Partie.getInstance().getJoueurs().get(adversaire).getCarteDevant().get(guideAdverse)
						.getCroyantAttache()) {
					MilieuDeTable.getInstance().poserCroyantMilieu(elem);
				}
				Partie.getInstance().getJoueurs().get(adversaire).getCarteDevant()
						.remove(Partie.getInstance().getJoueurs().get(adversaire).getCarteDevant().get(guideAdverse));
				break;

			case 2:
				System.out.println("Le r�sultat est jour. L'inquisition est un �chec. "
						+ j.getCarteDevant().get(guideAlli�).getNom()
						+ " a �t� supprim�.");
				for (Croyant elem : j.getCarteDevant().get(guideAlli�)
						.getCroyantAttache()) {
					MilieuDeTable.getInstance().poserCroyantMilieu(elem);
				}
				j.getCarteDevant().remove(guideAlli�);
				break;

			case 3:
				System.out.println("Le r�sultat est N�ant, l'inquisition n'a eu aucun r�sultat.");
			}

		}
	}

}
