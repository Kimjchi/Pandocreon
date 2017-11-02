package cartes.capacités;

import joueurs.Joueur;
import partie.Partie;

//Sorcier
public class EchangeGuidesSpirituels implements Capacité {

	public void sacrifier(Joueur j) {
		int guideAllié = j.choisirUnDeSesGuides();
		int adversaire = j.choisirAdversaireAvecGuideSprituels();
		int guideAdverse = j.choisirGuideAdversaire(adversaire);
		if (guideAllié != -1 && adversaire != -1 && guideAdverse != -1) {
			System.out.println("Le sorcier a échangé les croyants "
					+ j.getCarteDevant().get(guideAllié).getNom() + " et "
					+ Partie.getInstance().getJoueurs().get(adversaire).getCarteDevant().get(guideAdverse).getNom()
					+ ".");
			j.getCarteDevant()
					.add(Partie.getInstance().getJoueurs().get(adversaire).getCarteDevant().get(guideAdverse));
			Partie.getInstance().getJoueurs().get(adversaire).getCarteDevant()
					.add(j.getCarteDevant().get(guideAllié));
			j.getCarteDevant().remove(guideAllié);
			Partie.getInstance().getJoueurs().get(adversaire).getCarteDevant().remove(guideAdverse);
		} else {
			System.out.println("La capacité ne peut être activée correctement.");
		}
	}
}
