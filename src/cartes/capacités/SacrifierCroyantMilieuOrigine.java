package cartes.capacit�s;

import java.util.*;

import cartes.Croyant;
import cartes.MilieuDeTable;
import cartes.Origine;
import joueurs.Joueur;

//Pour Pui-Tara, Yarstur
public class SacrifierCroyantMilieuOrigine implements Capacit� {
	// origine est soit Jour soit Nuit
	private Origine origine;

	public SacrifierCroyantMilieuOrigine(Origine origine) {
		this.origine = origine;
	}

	public void sacrifier(Joueur j) {
		Iterator<Croyant> it = MilieuDeTable.getInstance().getListeCroyants().iterator();
		while (it.hasNext()) {

			if (MilieuDeTable.getInstance().getListeCroyants().iterator().next().getOrigine() == this.origine) {
				it.remove();
			}

		}
		if (origine == Origine.Jour) {
			System.out.println(
					"Pui-Tara �tend l'influence de la Nuit en d�truisant tous les croyants de type Jour au centre de la table.");

		} else if (origine == Origine.N�ant) {
			System.out.println(
					"Yarstur, dans sa haine du N�ant a d�truit tous les croyants de type N�ant situ�s au centre de la table.");

		}

	}

}
