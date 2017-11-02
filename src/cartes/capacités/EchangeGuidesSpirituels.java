package cartes.capacit�s;

import joueurs.Joueur;
import partie.Partie;

//Sorcier
public class EchangeGuidesSpirituels implements Capacit� {

	public void sacrifier(Joueur j) {
		int guideAlli� = j.choisirUnDeSesGuides();
		int adversaire = j.choisirAdversaireAvecGuideSprituels();
		int guideAdverse = j.choisirGuideAdversaire(adversaire);
		if (guideAlli� != -1 && adversaire != -1 && guideAdverse != -1) {
			System.out.println("Le sorcier a �chang� les croyants "
					+ j.getCarteDevant().get(guideAlli�).getNom() + " et "
					+ Partie.getInstance().getJoueurs().get(adversaire).getCarteDevant().get(guideAdverse).getNom()
					+ ".");
			j.getCarteDevant()
					.add(Partie.getInstance().getJoueurs().get(adversaire).getCarteDevant().get(guideAdverse));
			Partie.getInstance().getJoueurs().get(adversaire).getCarteDevant()
					.add(j.getCarteDevant().get(guideAlli�));
			j.getCarteDevant().remove(guideAlli�);
			Partie.getInstance().getJoueurs().get(adversaire).getCarteDevant().remove(guideAdverse);
		} else {
			System.out.println("La capacit� ne peut �tre activ�e correctement.");
		}
	}
}
