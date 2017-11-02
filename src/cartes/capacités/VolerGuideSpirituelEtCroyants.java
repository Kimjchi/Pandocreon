package cartes.capacit�s;

import joueurs.Joueur;
import partie.Partie;

public class VolerGuideSpirituelEtCroyants implements Capacit� {
	// Ordre c�leste
	public void sacrifier(Joueur j) {

		int adversaire = j.choisirAdversaireAvecGuideSprituels();
		int guide = j.choisirGuideAdversaire(adversaire);
		if (adversaire != -1 && guide != -1) {
			System.out.println("Par l'Ordre C�l�ste, le croyant "
					+ Partie.getInstance().getJoueurs().get(adversaire).getCarteDevant().get(guide).getNom()
					+ " appartient maintenant � " + j.getNom() + " !!");
			j.getCarteDevant()
					.add(Partie.getInstance().getJoueurs().get(adversaire).getCarteDevant().remove(guide));
		}
	}

}
