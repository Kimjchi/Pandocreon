package cartes.capacités;

import cartes.GuideSpirituel;
import cartes.Origine;
import partie.Partie;
import joueurs.Joueur;

//Cartes :
//Colère Divine
public class CapacitéColèreDivine implements Capacité {

	private Origine origine;

	public CapacitéColèreDivine(Origine origine) {
		this.origine = origine;
	}

	public void sacrifier(Joueur j) {
		boolean supprimé = false;
		int i = 0;
		while (!supprimé && i < 10) {
			i += 1;
			int indiceAdversaire = j.choisirAdversaireAvecGuideSprituels();
			int indiceGuide = j.choisirGuideAdversaire(indiceAdversaire);
			if (indiceGuide != -1 && indiceAdversaire != -1) {
				GuideSpirituel G = (GuideSpirituel) Partie.getInstance().getJoueurs().get(indiceAdversaire)
						.getCarteDevant().get(indiceGuide);
				if ((G.getOrigine() == this.origine) || (G.getOrigine() == cartes.Origine.Néant)) {
					System.out.println("La colère divine s'abbat sur "
							+ Partie.getInstance().getJoueurs().get(indiceAdversaire).getCarteDevant().get(indiceGuide)
									.getNom()
							+ " a été sacrifié. Les croyants qui lui étaient attaché reviennent au centre.");
					Partie.getInstance().getJoueurs().get(indiceAdversaire).defausserGuide(indiceGuide);
					supprimé = true;
				} else {
					System.out.println("Il faut choisir un guide spirituel d'origine Néant.");
				}
			}

		}
		if (i == 10) {
			System.out.println("Nombre d'essais dépassé, carte sans effet.");
		}

	}

}
