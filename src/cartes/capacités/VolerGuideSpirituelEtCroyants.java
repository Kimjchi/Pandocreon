package cartes.capacités;

import joueurs.Joueur;
import partie.Partie;

public class VolerGuideSpirituelEtCroyants implements Capacité {
	// Ordre céleste
	public void sacrifier(Joueur j) {

		int adversaire = j.choisirAdversaireAvecGuideSprituels();
		int guide = j.choisirGuideAdversaire(adversaire);
		if (adversaire != -1 && guide != -1) {
			System.out.println("Par l'Ordre Célèste, le croyant "
					+ Partie.getInstance().getJoueurs().get(adversaire).getCarteDevant().get(guide).getNom()
					+ " appartient maintenant à " + j.getNom() + " !!");
			j.getCarteDevant()
					.add(Partie.getInstance().getJoueurs().get(adversaire).getCarteDevant().remove(guide));
		}
	}

}
