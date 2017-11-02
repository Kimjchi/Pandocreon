package cartes.capacités;

import cartes.MilieuDeTable;
import joueurs.Joueur;
import partie.Partie;

//Guerrier Saint
public class RetourGuideEnMain implements Capacité {

	public void sacrifier(Joueur j) {
		int adversaire = j.choisirAdversaireAvecGuideSprituels();
		int guideAdverse = j.choisirGuideAdversaire(adversaire);
		if (adversaire != -1 && guideAdverse != -1) {
			System.out.println("Le croyant "
					+ Partie.getInstance().getJoueurs().get(adversaire).getCarteDevant().get(guideAdverse).getNom()
					+ " a été renvoyé dans la main de son propriétaire, les croyants attachés sont revenus au centre.");
			MilieuDeTable.getInstance().getListeCroyants().addAll(Partie.getInstance().getJoueurs().get(adversaire)
					.getCarteDevant().get(guideAdverse).getCroyantAttache());
			Partie.getInstance().getJoueurs().get(adversaire).getMainJoueur().getContenuMain()
					.add(Partie.getInstance().getJoueurs().get(adversaire).getCarteDevant().get(guideAdverse));

		}
	}

}
