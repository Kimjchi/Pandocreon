package cartes.capacités;

import java.util.*;

import cartes.Croyant;
import cartes.MilieuDeTable;
import cartes.Origine;
import joueurs.Joueur;

//Pour Pui-Tara, Yarstur
public class SacrifierCroyantMilieuOrigine implements Capacité {
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
					"Pui-Tara étend l'influence de la Nuit en détruisant tous les croyants de type Jour au centre de la table.");

		} else if (origine == Origine.Néant) {
			System.out.println(
					"Yarstur, dans sa haine du Néant a détruit tous les croyants de type Néant situés au centre de la table.");

		}

	}

}
