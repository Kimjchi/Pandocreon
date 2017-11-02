package cartes.capacit�s;

import java.util.*;

import cartes.Croyant;
import cartes.Dogme;
import cartes.MilieuDeTable;
import joueurs.Joueur;

//Pour Tyran
public class SacrifierCroyantMilieuDogme implements Capacit� {

	public void sacrifier(Joueur j) {
		Iterator<Croyant> it = MilieuDeTable.getInstance().getListeCroyants().iterator();
		while (it.hasNext()) {
			if (it.next().verifierDogme(Dogme.Mystique)) {
				it.remove();
			}

		}
		System.out.println(
				"Dans sa rage, le Tyran a massacr� tous les croyants de dogme Mystique situ�s au centre de la table !");

	}

}