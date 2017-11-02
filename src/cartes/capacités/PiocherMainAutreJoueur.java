package cartes.capacités;

import java.util.Collections;

import joueurs.Joueur;
import partie.Partie;

// Pour Croyant Alchimiste, aliénés (nbCartesAPiocher = 2) et Deus Ex Diversion (nbCartesAPiocher = 3)
public class PiocherMainAutreJoueur implements Capacité {

	private int nbCartesAPiocher;

	public PiocherMainAutreJoueur(int nbCartesàPiocher) {
		this.nbCartesAPiocher = nbCartesàPiocher;
	}

	public void sacrifier(Joueur j) {
		int adversaire = j.choisirAdversaire();
		if (adversaire != -1) {
			Collections.shuffle(Partie.getInstance().getJoueurs().get(adversaire).getMainJoueur().getContenuMain());
			while (nbCartesAPiocher != 0 && j.getMainJoueur().getNbCartes() != 7
					&& Partie.getInstance().getJoueurs().get(adversaire).getMainJoueur().getNbCartes() != 0) {
				j.getMainJoueur().getContenuMain().add(
						Partie.getInstance().getJoueurs().get(adversaire).getMainJoueur().getContenuMain().remove(0));
				nbCartesAPiocher -= 1;
			}

			if (nbCartesAPiocher != 0) {
				System.out.println("Vous n'avez pas pu piocher le nombre de cartes prévus car vous aviez plus de "
						+ (7 - nbCartesAPiocher) + " cartes ou le joueur adverse en avais moins de "
						+ nbCartesAPiocher);
			}
			
		}

	}

}
