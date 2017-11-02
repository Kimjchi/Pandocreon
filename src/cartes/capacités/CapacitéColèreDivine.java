package cartes.capacit�s;

import cartes.GuideSpirituel;
import cartes.Origine;
import partie.Partie;
import joueurs.Joueur;

//Cartes :
//Col�re Divine
public class Capacit�Col�reDivine implements Capacit� {

	private Origine origine;

	public Capacit�Col�reDivine(Origine origine) {
		this.origine = origine;
	}

	public void sacrifier(Joueur j) {
		boolean supprim� = false;
		int i = 0;
		while (!supprim� && i < 10) {
			i += 1;
			int indiceAdversaire = j.choisirAdversaireAvecGuideSprituels();
			int indiceGuide = j.choisirGuideAdversaire(indiceAdversaire);
			if (indiceGuide != -1 && indiceAdversaire != -1) {
				GuideSpirituel G = (GuideSpirituel) Partie.getInstance().getJoueurs().get(indiceAdversaire)
						.getCarteDevant().get(indiceGuide);
				if ((G.getOrigine() == this.origine) || (G.getOrigine() == cartes.Origine.N�ant)) {
					System.out.println("La col�re divine s'abbat sur "
							+ Partie.getInstance().getJoueurs().get(indiceAdversaire).getCarteDevant().get(indiceGuide)
									.getNom()
							+ " a �t� sacrifi�. Les croyants qui lui �taient attach� reviennent au centre.");
					Partie.getInstance().getJoueurs().get(indiceAdversaire).defausserGuide(indiceGuide);
					supprim� = true;
				} else {
					System.out.println("Il faut choisir un guide spirituel d'origine N�ant.");
				}
			}

		}
		if (i == 10) {
			System.out.println("Nombre d'essais d�pass�, carte sans effet.");
		}

	}

}
