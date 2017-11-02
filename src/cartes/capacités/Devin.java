package cartes.capacités;

import joueurs.Joueur;
import partie.Partie;

public class Devin implements Capacité {
	public void sacrifier(Joueur j) {
		int adversaire = j.choisirAdversaireAvecGuideSprituels();
		int i = 0;
		while (i < 10) {
			if (adversaire != -1 && adversaire != Partie.getInstance().getJoueurs().indexOf(j)) {
				if (Partie.getInstance().getJoueurs().get(adversaire).getDivinité().getDogme(0) == cartes.Dogme.Nature
						|| Partie.getInstance().getJoueurs().get(adversaire).getDivinité()
								.getDogme(0) == cartes.Dogme.Mystique
						|| Partie.getInstance().getJoueurs().get(adversaire).getDivinité()
								.getDogme(1) == cartes.Dogme.Nature
						|| Partie.getInstance().getJoueurs().get(adversaire).getDivinité()
								.getDogme(1) == cartes.Dogme.Mystique) {
					System.out.println(Partie.getInstance().getJoueurs().get(adversaire).getNom()
							+ " est obligé de sacrifier un guide");
					Partie.getInstance().getJoueurs().get(adversaire).sacrifierGuide();
				} else {
					System.out.println("La divinité que vous attaquez n'a pas les bons dogmes");
				}
			} else {
				System.out.println("La capacité ne peut avoir d'effet...");
			}
			i++;
		}
		if (i == 10){
			System.out.println("Trop d'echecs concécutifs...");
		}
	}
}