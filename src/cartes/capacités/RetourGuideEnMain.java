package cartes.capacit�s;

import cartes.MilieuDeTable;
import joueurs.Joueur;
import partie.Partie;

//Guerrier Saint
public class RetourGuideEnMain implements Capacit� {

	public void sacrifier(Joueur j) {
		int adversaire = j.choisirAdversaireAvecGuideSprituels();
		int guideAdverse = j.choisirGuideAdversaire(adversaire);
		if (adversaire != -1 && guideAdverse != -1) {
			System.out.println("Le croyant "
					+ Partie.getInstance().getJoueurs().get(adversaire).getCarteDevant().get(guideAdverse).getNom()
					+ " a �t� renvoy� dans la main de son propri�taire, les croyants attach�s sont revenus au centre.");
			MilieuDeTable.getInstance().getListeCroyants().addAll(Partie.getInstance().getJoueurs().get(adversaire)
					.getCarteDevant().get(guideAdverse).getCroyantAttache());
			Partie.getInstance().getJoueurs().get(adversaire).getMainJoueur().getContenuMain()
					.add(Partie.getInstance().getJoueurs().get(adversaire).getCarteDevant().get(guideAdverse));

		}
	}

}
