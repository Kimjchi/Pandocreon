package cartes.capacités;

import cartes.Dogme;
import joueurs.Joueur;
import partie.Partie;

//alchimistes, alliénés, travailleurs
public class EmpêcheSacrificeGuide implements Capacité {

	private Dogme dogme1;
	private Dogme dogme2;

	public EmpêcheSacrificeGuide(Dogme dogme1, Dogme dogme2) {
		this.dogme1 = dogme1;
		this.dogme2 = dogme2;
	}

	public void sacrifier(Joueur j) {

		boolean valide = false;
		int adversaire = 0;
		int i = 0;
		while (!valide && i < 10) {
			i += 1;
			adversaire = j.choisirAdversaire();
			if (adversaire != -1) {
				if (Partie.getInstance().getJoueurs().get(adversaire).getDivinité().verifierDogme(dogme1)
						|| Partie.getInstance().getJoueurs().get(adversaire).getDivinité().verifierDogme(dogme2)) {
					valide = true;
				}
			}

		}
		if (i >= 10) {
			System.out.println("Nombre d'essais dépassé, carte sans effet.");
		} else if (valide) {
			System.out.println("Les croyants vont s'infiltrer dans les rangs de "
					+ Partie.getInstance().getJoueurs().get(adversaire).getNom()
					+ ", instorant un climat de resistance face et sa tyrannie, l'empechant ainsi de sacrifier ses pauvres guides spirituels au prochain tour");
			Partie.getInstance().getJoueurs().get(adversaire).setPeutSacrifierGuide(false);
		}

	}
}
