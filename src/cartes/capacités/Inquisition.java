package cartes.capacités;

import cartes.Croyant;
import joueurs.Joueur;

import cartes.MilieuDeTable;

import partie.Partie;

//Inquisition
public class Inquisition implements Capacité {

	public void sacrifier(Joueur j) {

		int adversaire = j.choisirAdversaireAvecGuideSprituels();
		int guideAdverse = j.choisirGuideAdversaire(adversaire);
		int guideAllié = j.choisirUnDeSesGuides();// sélectionnerDivinitéAvecGuideSpirituels()
		if (adversaire != -1 && guideAdverse != -1 && guideAllié != -1) {
			int résultat = Partie.getInstance().lancerDe();
			switch (résultat) {
			case 1:
				System.out.println("Le résultat est jour. L'inquisition est un succès ! "
						+ Partie.getInstance().getJoueurs().get(adversaire).getCarteDevant().get(guideAdverse).getNom()
						+ " a été supprimé.");

				for (Croyant elem : Partie.getInstance().getJoueurs().get(adversaire).getCarteDevant().get(guideAdverse)
						.getCroyantAttache()) {
					MilieuDeTable.getInstance().poserCroyantMilieu(elem);
				}
				Partie.getInstance().getJoueurs().get(adversaire).getCarteDevant()
						.remove(Partie.getInstance().getJoueurs().get(adversaire).getCarteDevant().get(guideAdverse));
				break;

			case 2:
				System.out.println("Le résultat est jour. L'inquisition est un échec. "
						+ j.getCarteDevant().get(guideAllié).getNom()
						+ " a été supprimé.");
				for (Croyant elem : j.getCarteDevant().get(guideAllié)
						.getCroyantAttache()) {
					MilieuDeTable.getInstance().poserCroyantMilieu(elem);
				}
				j.getCarteDevant().remove(guideAllié);
				break;

			case 3:
				System.out.println("Le résultat est Néant, l'inquisition n'a eu aucun résultat.");
			}

		}
	}

}
