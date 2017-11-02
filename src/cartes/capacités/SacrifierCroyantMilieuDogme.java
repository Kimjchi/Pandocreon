package cartes.capacités;

import java.util.*;

import cartes.Croyant;
import cartes.Dogme;
import cartes.MilieuDeTable;
import joueurs.Joueur;

//Pour Tyran
public class SacrifierCroyantMilieuDogme implements Capacité {

	public void sacrifier(Joueur j) {
		Iterator<Croyant> it = MilieuDeTable.getInstance().getListeCroyants().iterator();
		while (it.hasNext()) {
			if (it.next().verifierDogme(Dogme.Mystique)) {
				it.remove();
			}

		}
		System.out.println(
				"Dans sa rage, le Tyran a massacré tous les croyants de dogme Mystique situés au centre de la table !");

	}

}