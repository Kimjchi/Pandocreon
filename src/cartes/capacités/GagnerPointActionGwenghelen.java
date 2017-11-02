package cartes.capacités;

import joueurs.Joueur;

//Croyant :

//Gwenghelen


public class GagnerPointActionGwenghelen implements Capacité {

	public void sacrifier(Joueur j) {

		if (j.isPeutGagnerPointAction()) {
			System.out.println("Grace aux fabuleux pouvoirs de Gwenghelen, vous gagnez " + j.getNbGuidesSprituels()
					+ "points d'action de type Néant.");
			j.setPointsNeant(j.getNbGuidesSprituels());
		} else {
			System.out.println(
					"Le joueur est sous l'effet d'un sort l'empechant de gagner des points d'action jusqu'à la fin du tour. L'effet de Gorpa est gaspillé...");
		}

	}

}
